package service.encryption.impl;

import service.encryption.DecryptService;
import service.io.ReaderService;
import service.io.WriterService;
import util.ex.InvalidKeyException;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class DecryptServiceImpl implements DecryptService {
    private final ReaderService readerService;
    private final WriterService writerService;

    public DecryptServiceImpl(ReaderService readerService, WriterService writerService) {
        this.readerService = readerService;
        this.writerService = writerService;
    }

    @Override
    public void decrypt(int key, Path readPath, Path writePath) {
        if(key <= 0) throw new InvalidKeyException();
        try {
            String text = readerService.read(readPath);
            StringBuilder decryptedText = new StringBuilder();
            for (int i = 0; i < text.length(); i++){
                char c = text.charAt(i);
                if(Character.isUpperCase(c)) {
                    if(c - key < 65){
                        char resChar = (char) (91-(65 % (c - key)));
                        decryptedText.append(resChar);
                    } else decryptedText.append((char) (c - key));

                } else if (Character.isLowerCase(c)) {
                    if(c - key < 97) {
                        char resChar = (char) (123 - (97 % (c - key)));
                        decryptedText.append(resChar);
                    }else{
                        decryptedText.append((char) (c- key));
                    }
                } else {
                    decryptedText.append(c);
                }
            }
            writerService.write(decryptedText.toString(), writePath);
        } catch (FileNotFoundException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

    }
}
