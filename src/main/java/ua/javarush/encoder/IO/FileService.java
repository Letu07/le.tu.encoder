package ua.javarush.encoder.IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {
    public boolean isFileExist(Path filePath) {
        return Files.exists(filePath);
    }

    public String read(Path filepath) throws IOException {
        return new String(Files.readAllBytes(filepath));
    }

    public void write(Path path, String text) throws IOException {
        Files.writeString(path, text);
    }

}
