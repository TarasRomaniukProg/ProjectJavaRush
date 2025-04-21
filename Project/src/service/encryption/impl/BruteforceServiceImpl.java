package service.encryption.impl;

import service.encryption.BruteforceService;
import service.encryption.DecryptService;
import service.io.ReaderService;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class BruteforceServiceImpl implements BruteforceService {
    private final ReaderService readerService;
    private final DecryptService decryptService;

    public BruteforceServiceImpl(ReaderService readerService, DecryptService decryptService) {
        this.readerService = readerService;
        this.decryptService = decryptService;
    }

    @Override
    public int bruteForce(Path sourcePath, Path encryptedPath) {
        int key = 0;
        try {
            key++;
            String sourceText = readerService.read(sourcePath);
            while (!decryptService.decrypt(key, encryptedPath).equals(sourceText)){
                key++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return key;
    }
}
