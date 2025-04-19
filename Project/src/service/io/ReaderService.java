package service.io;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public interface ReaderService {
     String read(Path path) throws FileNotFoundException;
}
