package ua.javarush.encoder;

import ua.javarush.encoder.IO.FileService;
import ua.javarush.encoder.cipher.CaesarCipher;
import ua.javarush.encoder.constants.Constants;
import ua.javarush.encoder.runner.Runner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        CaesarCipher caesarCipher = new CaesarCipher(Constants.ALPHABET_EN);
        FileService fileService = new FileService();
        Runner runner = new Runner(caesarCipher, fileService);
        runner.run(args);

    }
}
