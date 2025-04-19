package cli;


import model.EncryptionType;
import service.encryption.DecryptService;
import service.encryption.EncryptService;
import service.encryption.impl.DecryptServiceImpl;
import service.encryption.impl.EncryptServiceImpl;
import service.io.ReaderService;
import service.io.WriterService;
import service.io.impl.ReaderServiceImpl;
import service.io.impl.WriterServiceImpl;

import java.nio.file.Path;

public class CryptoAppService {
    private final ReaderService rs = new ReaderServiceImpl();
    private final WriterService ws = new WriterServiceImpl();

    private final EncryptService encrypt = new EncryptServiceImpl(rs, ws);
    private final DecryptService decrypt = new DecryptServiceImpl(rs, ws);

    public void run(String[] args) {

        EncryptionType et = EncryptionType.valueOf(args[0]);
        StringBuilder path = new StringBuilder(args[1]);
        int key = Integer.parseInt(args[2]);

        switch (et) {
            case ENCRYPT -> {
                Path readPath = Path.of(path.toString());
                Path writePath = Path.of(path.insert(path.indexOf("."), "[ENCRYPTED]").toString());

                encrypt.encrypt(key, readPath, writePath);
            }

            case DECRYPT -> {
                Path readPath = Path.of(path.toString());
                if (path.toString().contains("[ENCRYPTED]")) {
                    path.delete(path.indexOf("["), path.indexOf("]") + 1);
                }
                Path writePath = Path.of(path.insert(path.indexOf("."), "[DECRYPTED]").toString());

                decrypt.decrypt(key, readPath, writePath);
            }
        }
    }
}

/*
java -jar c:/MyProject/target/myApp.jar ENCRYPT folder/textFile1.txt 20 // Файл з результатом folder/textFile1[ENCRYPTED].txt
java -jar c:/MyProject/target/myApp.jar DECRYPT folder/textFile1[ENCRYPTED].txt 20 // Файл з результатом folder/textFile1[DECRYPTED].txt
 */
