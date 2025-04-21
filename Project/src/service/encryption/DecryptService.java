package service.encryption;

import java.nio.file.Path;

public interface DecryptService {
    String decrypt(int key, Path readPath);
}
