package ua.javarush.encoder.io;

import ua.javarush.encoder.commands.Command;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

    public String generateFileName(Path filePath, Command commands) {
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
                newFileName = fileName.replace("[ENCRYPTED].txt", "[BRUTE_FORCED].txt");
                break;
        }

        if (newFileName != null) {
            return filePath.resolveSibling(newFileName).toString();
        } else {
            throw new UnsupportedOperationException();
        }

    }

    public static Map<Character, Double> loadFrequencyMap() {
        Properties properties = new Properties();
        Map<Character, Double> frequencyMap = new HashMap<>();

        try (InputStream inputStream = FileService.class.getResourceAsStream("/eng_letter_frequencies.properties")) {
            properties.load(inputStream);

            for (String key : properties.stringPropertyNames()) {
                char letter = key.charAt(0);
                double frequency = Double.parseDouble(properties.getProperty(key));
                frequencyMap.put(letter, frequency);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return frequencyMap;
    }
}
