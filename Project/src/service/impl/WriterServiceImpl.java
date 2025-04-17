package service.impl;

import service.WriterService;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriterServiceImpl implements WriterService {

    @Override
    public void write(String content, Path path) throws FileNotFoundException {
        if (!Files.exists(path)) throw new FileNotFoundException("Path doesn't exist!");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile()))) {
            bw.write(content);
        } catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}