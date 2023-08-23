package ua.javarush.encoder.runner;

import ua.javarush.encoder.IO.FileService;
import ua.javarush.encoder.cipher.CaesarCipher;

import java.io.IOException;

public class Runner {
    private final CaesarCipher cipher;
    private final FileService fileService;

    public Runner(CaesarCipher cipher, FileService fileService) {
        this.cipher = cipher;
        this.fileService = fileService;
    }

    public static void run(String[] args) throws IOException {
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
