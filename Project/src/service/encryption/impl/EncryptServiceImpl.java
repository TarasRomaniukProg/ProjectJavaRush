package service.encryption.impl;

import service.encryption.EncryptService;
import service.io.ReaderService;
import service.io.WriterService;
import util.ex.InvalidKeyException;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class EncryptServiceImpl implements EncryptService {
    private final ReaderService readerService;
    private final WriterService writerService;

    public EncryptServiceImpl(ReaderService readerService, WriterService writerService) {
        this.readerService = readerService;
        this.writerService = writerService;
    }

    @Override
    public void encrypt(int key, Path readPath, Path writePath) {
        if (key <= 0) throw new InvalidKeyException();
        try {
            String text = readerService.read(readPath);
            StringBuilder encryptedText = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (Character.isUpperCase(c)) {
                    if (c + key > 90) {
                        char resChar = (char) ((c + key) % 90 + 64);
                        encryptedText.append(resChar);
                    } else encryptedText.append((char) (c + key));
                } else if (Character.isLowerCase(c)) {
                    if (c + key > 122) {
                        char resChar = (char) ((c + key) % 122 + 96);
                        encryptedText.append(resChar);
                    } else encryptedText.append((char) (c + key));
                } else {
                    encryptedText.append(c);
                }
            }
            writerService.write(encryptedText.toString(), writePath);
        } catch(FileNotFoundException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
