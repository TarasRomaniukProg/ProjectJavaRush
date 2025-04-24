package service.encryption;

import java.nio.file.Path;

public interface EncryptService {
    String encrypt(int key, Path readPath);
}
