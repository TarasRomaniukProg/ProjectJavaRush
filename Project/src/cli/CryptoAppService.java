package cli;


import model.EncryptionType;
import service.encryption.BruteforceService;
import service.encryption.DecryptService;
import service.encryption.EncryptService;
import service.encryption.impl.BruteforceServiceImpl;
import service.encryption.impl.DecryptServiceImpl;
import service.encryption.impl.EncryptServiceImpl;
import service.io.ReaderService;
import service.io.WriterService;
import service.io.impl.ReaderServiceImpl;
import service.io.impl.WriterServiceImpl;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class CryptoAppService {
    private final ReaderService rs = new ReaderServiceImpl();
    private final WriterService ws = new WriterServiceImpl();

    private final EncryptService encrypt = new EncryptServiceImpl(rs);
    private final DecryptService decrypt = new DecryptServiceImpl(rs);
    private final BruteforceService bruteforce = new BruteforceServiceImpl(rs, decrypt);

    public void run(String[] args) {

        EncryptionType et = EncryptionType.valueOf(args[0]);
        StringBuilder path = new StringBuilder(args[1]);
        Path encryptedPath = Path.of("");
        int key = 0;
        if (et.equals(EncryptionType.ENCRYPT) || et.equals(EncryptionType.DECRYPT)) {
            key = Integer.parseInt(args[2]);
        } else {
           encryptedPath = Path.of(args[2]);
        }

        Path readPath = Path.of(path.toString());
        try {
            switch (et) {
                case ENCRYPT -> {
                    Path writePath = Path.of(path.insert(path.indexOf("."), "[ENCRYPTED]").toString());

                    String encrypted = encrypt.encrypt(key, readPath);
                    ws.write(encrypted, writePath);
                }

                case DECRYPT -> {
                    if (path.toString().contains("[ENCRYPTED]")) {
                        path.delete(path.indexOf("["), path.indexOf("]") + 1);
                    }
                    Path writePath = Path.of(path.insert(path.indexOf("."), "[DECRYPTED]").toString());

                    String decrypted = decrypt.decrypt(key, readPath);
                    ws.write(decrypted, writePath);
                }
                
                case BRUTE_FORCE -> {
                    int decryptedKey = bruteforce.bruteForce(readPath, encryptedPath);
                    Path writePath = Path.of(path.insert(path.indexOf("."), "(B-" + decryptedKey + ")").toString());
                    ws.write(rs.read(readPath), writePath);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}

/*
java -jar c:/MyProject/target/myApp.jar ENCRYPT folder/textFile1.txt 20 // Файл з результатом folder/textFile1[ENCRYPTED].txt
java -jar c:/MyProject/target/myApp.jar DECRYPT folder/textFile1[ENCRYPTED].txt 20 // Файл з результатом folder/textFile1[DECRYPTED].txt
 */
