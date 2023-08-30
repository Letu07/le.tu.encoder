package ua.javarush.encoder.runner;

import ua.javarush.encoder.io.FileService;
import ua.javarush.encoder.cipher.CaesarCipher;
import ua.javarush.encoder.commands.Command;

import java.io.IOException;
import java.nio.file.Path;

public class Runner {
    private final CaesarCipher cipher;
    private final FileService fileService;

    public Runner(CaesarCipher cipher, FileService fileService) {
        this.cipher = cipher;
        this.fileService = fileService;
    }

    public void run(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Minimum two arguments must be provided:");
        } else {
            String command = args[0];
            Path filepath = Path.of(args[1]);

            Command commands = Command.valueOf(command.toUpperCase());

            if (!fileService.isFileExist(filepath)) {
                System.err.println("The specified file does not exist.");
            } else {
                int key = -1;
                if (args.length > 2) {
                    key = Integer.parseInt(args[2]);
                }

                if (Command.ENCRYPT.equals(commands)) {
                    String plaintext = fileService.read(filepath);
                    String encryptedText = cipher.encoder(plaintext, key);
                    fileService.write(Path.of(fileService.generateFileName(filepath, Command.ENCRYPT)), encryptedText);
                    System.out.println("The text has been successfully encrypted.");
                } else if (Command.DECRYPT.equals(commands)) {
                    String encryptedText = fileService.read(filepath);
                    String decryptedText = cipher.decoder(encryptedText, key);
                    fileService.write(Path.of(fileService.generateFileName(filepath, Command.DECRYPT)), decryptedText);
                    System.out.println("The text has been successfully decrypted.");
                } else if (Command.BRUTE_FORCE.equals(commands)) {
                    String encryptedText = fileService.read(filepath);
                    String decryptedText = cipher.bruteForceDecrypt(encryptedText);
                    fileService.write(Path.of(fileService.generateFileName(filepath, Command.BRUTE_FORCE)), decryptedText);
                    System.out.println("The text has been successfully decrypted by Brute Force.");
                } else {
                    System.out.println("Unknown command.");
                }
            }
        }
    }
}
