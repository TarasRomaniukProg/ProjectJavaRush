package service.encryption.impl;

import service.encryption.EncryptService;
import service.io.ReaderService;
import util.ex.InvalidKeyException;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class EncryptServiceImpl implements EncryptService {
    private final ReaderService readerService;

    public EncryptServiceImpl(ReaderService readerService) {
        this.readerService = readerService;
    }

    @Override
    public String encrypt(int key, Path readPath) {
        if (key <= 0) throw new InvalidKeyException();
        StringBuilder encryptedText = new StringBuilder();
        try {
            String text = readerService.read(readPath);
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
        } catch (FileNotFoundException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return encryptedText.toString();
    }
}
