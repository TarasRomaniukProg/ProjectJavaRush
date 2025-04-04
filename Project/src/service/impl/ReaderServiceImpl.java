package service.impl;

import service.ReaderService;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReaderServiceImpl implements ReaderService {
    private final String path;

    public ReaderServiceImpl(String path) {
        this.path = path;
    }


    @Override
    public String read() {
        StringBuilder text = new StringBuilder();
        try (BufferedReader bis = new BufferedReader(new FileReader(path))) {
            int c;
            while((c = bis.read()) != -1) {
                text.append((char) c);
            }
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }

        return text.toString();
    }
}
