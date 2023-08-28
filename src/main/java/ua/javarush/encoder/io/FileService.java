package ua.javarush.encoder.io;

import ua.javarush.encoder.commands.Commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {
    public boolean isFileExist(Path filePath) {
        return Files.exists(filePath);
    }

    public String read(Path filePath) throws IOException {
        return new String(Files.readAllBytes(filePath));
    }

    public void write(Path path, String text) throws IOException {
        Files.writeString(path, text);
    }

    public String generateFileName(Path filePath, Commands commands) {
        String fileName = filePath.getFileName().toString();
        String newFileName = null;

        switch (commands) {
            case ENCRYPT:
                newFileName = fileName.replace(".txt", "[ENCRYPTED].txt");
                break;
            case DECRYPT:
                newFileName = fileName.replace("[ENCRYPTED].txt", "[DECRYPTED].txt");
                break;
            case BRUTE_FORCE:
                newFileName = fileName.replace(".txt", "[BRUTE_FORCED].txt");
                break;
        }

        if (newFileName != null) {
            return filePath.resolveSibling(newFileName).toString();
        } else {
            throw new UnsupportedOperationException();
        }

    }
}
