package controller;

import service.ReaderService;
import service.WriterService;
import service.impl.ReaderServiceImpl;
import service.impl.WriterServiceImpl;

public class EncryptController {
    public void run(String[] args) {
        WriterService writerService = new WriterServiceImpl(args[0]);
        writerService.write(args[1]);

        ReaderService readerService = new ReaderServiceImpl(args[0]);
        System.out.println(readerService.read());
    }
}
