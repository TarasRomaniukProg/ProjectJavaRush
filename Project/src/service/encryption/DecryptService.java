package service.encryption;

import java.nio.file.Path;

public interface DecryptService {
    void decrypt(int key, Path readPath, Path writePath);
}
