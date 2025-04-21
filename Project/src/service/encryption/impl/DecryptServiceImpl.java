package service.encryption.impl;

import service.encryption.DecryptService;
import service.io.ReaderService;
import util.ex.InvalidKeyException;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class DecryptServiceImpl implements DecryptService {
    private final ReaderService readerService;

    public DecryptServiceImpl(ReaderService readerService) {
        this.readerService = readerService;
    }

    @Override
    public String decrypt(int key, Path readPath) {
        if (key <= 0) throw new InvalidKeyException();
        StringBuilder decryptedText = new StringBuilder();
        try {
            String text = readerService.read(readPath);

            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (Character.isUpperCase(c)) {
                    if (c - key < 65) {
                        char resChar = (char) (91 - (65 % (c - key)));
                        decryptedText.append(resChar);
                    } else decryptedText.append((char) (c - key));

                } else if (Character.isLowerCase(c)) {
                    if (c - key < 97) {
                        char resChar = (char) (123 - (97 % (c - key)));
                        decryptedText.append(resChar);
                    } else {
                        decryptedText.append((char) (c - key));
                    }
                } else {
                    decryptedText.append(c);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return decryptedText.toString();
    }
}
