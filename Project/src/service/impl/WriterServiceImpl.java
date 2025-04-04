package service.impl;

import service.WriterService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterServiceImpl implements WriterService {

    private final String path;

    public WriterServiceImpl(String path) {
        this.path = path;
    }

    @Override
    public void write(String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(content);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}