package ua.javarush.encoder.runner;

import ua.javarush.encoder.io.FileService;
import ua.javarush.encoder.cipher.CaesarCipher;
import ua.javarush.encoder.commands.Commands;

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

        if (args.length == 0) {
            System.out.println("Minimum two arguments must be provided:");
            return;
        } else {
            String command = args[0];
            Path filepath = Path.of(args[1]);
            int key = Integer.parseInt(args[2]);

            Commands commands = Commands.valueOf(command.toUpperCase());

            if (!fileService.isFileExist(filepath)) {
                System.err.println("The specified file does not exist.");
            } else {


                if (Commands.ENCRYPT.equals(commands)) {
                    String plaintext = fileService.read(filepath);
                    String encryptedText = cipher.encoder(plaintext, key);
                    fileService.write(Path.of(fileService.generateFileName(filepath, Commands.ENCRYPT)), encryptedText);
                    System.out.println("The text has been successfully encrypted.");
                } else if (Commands.DECRYPT.equals(commands)) {
                    String encryptedText = fileService.read(filepath);
                    String decryptedText = cipher.decoder(encryptedText, key);
                    fileService.write(Path.of(fileService.generateFileName(filepath, Commands.DECRYPT)), decryptedText);
                    System.out.println("Text successfully decrypted.");
                } else if (Commands.BRUTE_FORCE.equals(commands)) { //TODO
//Тут должен быть код
                } else {
                    System.out.println("Unknown command.");
                }
            }
        }
    }
}
