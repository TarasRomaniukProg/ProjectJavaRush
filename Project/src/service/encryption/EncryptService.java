package service.encryption;

import java.nio.file.Path;

public interface EncryptService {
    void encrypt(int key, Path readPath, Path writePath);
}
