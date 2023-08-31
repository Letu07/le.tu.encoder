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
            System.err.println("NOT ENOUGH ARGUMENTS PROVIDED");
            System.out.println("1. Write one of the commands: encrypt | decrypt | brute_force");
            System.out.println("2. Write path to the file:");
            System.out.println("3. Write key:");
            return;
        }

        Command cmd;
        int key = -1;
        try {
            String command = args[0];
            cmd = Command.valueOf(command.toUpperCase());

            if (args.length > 2) {
                key = Integer.parseInt(args[2]);
            }

            Path filePath = Path.of(args[1]);
            if (fileService.isFileExist(filePath)) {
                if (Command.ENCRYPT.equals(cmd)) {
                    String plaintext = fileService.read(filePath);
                    String encryptedText = cipher.encoder(plaintext, key);
                    fileService.write(Path.of(fileService.generateFileName(filePath, Command.ENCRYPT)), encryptedText);
                    System.out.println("The text has been successfully encrypted.");
                } else if (Command.DECRYPT.equals(cmd)) {
                    String encryptedText = fileService.read(filePath);
                    String decryptedText = cipher.decoder(encryptedText, key);
                    fileService.write(Path.of(fileService.generateFileName(filePath, Command.DECRYPT)), decryptedText);
                    System.out.println("The text has been successfully decrypted.");
                } else if (Command.BRUTE_FORCE.equals(cmd)) {
                    String encryptedText = fileService.read(filePath);
                    String decryptedText = cipher.bruteForceDecrypt(encryptedText);
                    fileService.write(Path.of(fileService.generateFileName(filePath, Command.BRUTE_FORCE)), decryptedText);
                    System.out.println("The text has been successfully decrypted by Brute Force.");
                } else {
                    System.out.println("Unknown command.");
                    System.out.println("1. Write one of the commands: encrypt | decrypt | brute_force");
                }
            } else {
                System.err.println("THE FILE DOES NOT EXIST");
            }
        } catch (NumberFormatException e) {
            System.err.println("THE KEY HAS BEEN ENTERED INCORRECTLY. PLEASE ENTER A NUMBER");
        } catch (IllegalArgumentException e) {
            System.err.println("THE COMMAND IS UNKNOWN");
        }
    }
}
