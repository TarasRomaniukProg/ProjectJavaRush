package service.impl;

import service.ReaderService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReaderServiceImpl implements ReaderService {

    @Override
    public String read(Path path) throws FileNotFoundException {
        if (!Files.exists(path)) throw new FileNotFoundException("Path doesn't exist!");
        StringBuilder text = new StringBuilder();
        try (BufferedReader bis = new BufferedReader(new FileReader(path.toFile()))) {
            int c;
            while ((c = bis.read()) != -1) {
                text.append((char) c);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return text.toString();
    }
}
