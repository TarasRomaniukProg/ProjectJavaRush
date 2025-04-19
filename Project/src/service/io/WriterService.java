package service.io;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public interface WriterService {
    void write(String content, Path path) throws FileNotFoundException;
}
