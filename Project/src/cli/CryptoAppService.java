package cli;


import service.io.ReaderService;
import service.io.WriterService;
import service.io.impl.ReaderServiceImpl;
import service.io.impl.WriterServiceImpl;

import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class CryptoAppService {
    private final ReaderService rs = new ReaderServiceImpl();
    private final WriterService ws = new WriterServiceImpl();

    public void run(String[] args) {
        try {
        System.out.println(rs.read(Paths.get(args[1])));
        } catch (FileNotFoundException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
