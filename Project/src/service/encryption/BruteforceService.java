package service.encryption;

import java.nio.file.Path;

public interface BruteforceService {
    int bruteForce(Path sourcePath, Path encryptedPath);
}
