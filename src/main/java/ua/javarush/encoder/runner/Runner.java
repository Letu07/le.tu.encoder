package ua.javarush.encoder.runner;

import ua.javarush.encoder.IO.FileService;
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
            System.out.println("Должно быть передано 3 аргумента:");
            return;
        } else {
            String command = args[0];
            Path filepath = Path.of(args[1]);
            int key = Integer.parseInt(args[2]);

            Commands commands = Commands.valueOf(command.toUpperCase());
            if (Commands.ENCRYPT.equals(commands)) {
                String plaintext = fileService.read(filepath);
                String encryptedText = cipher.encoder(plaintext, key);
                fileService.write(Path.of("D:\\Programms\\IDEA_project\\le.tu.encoder\\src\\main\\resources.EncodedText"), encryptedText);
                System.out.println("Текст успешно зашифрован.");
            } else if (Commands.DECRYPT.equals(command)) {
                String encryptedText = fileService.read(filepath);
                String decryptedText = cipher.decoder(encryptedText, key);
                fileService.write(Path.of("new path"), decryptedText);
                System.out.println("Текст успешно дешифрован.");
            } else if (Commands.BRUTE_FORCE.equals(command)) {
                System.out.println("Неизвестная команда.");
            } else {
                System.out.println("Неизвестная команда.");
            }
        }
    }
}
