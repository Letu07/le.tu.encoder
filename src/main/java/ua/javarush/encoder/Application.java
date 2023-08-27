package ua.javarush.encoder;

import ua.javarush.encoder.io.FileService;
import ua.javarush.encoder.cipher.CaesarCipher;
import ua.javarush.encoder.constants.Constants;
import ua.javarush.encoder.runner.Runner;

import java.io.IOException;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) throws IOException {

        CaesarCipher caesarCipher = new CaesarCipher(Constants.ALPHABET_EN);
        FileService fileService = new FileService();
        Runner runner = new Runner(caesarCipher, fileService);
        runner.run(args);

    }
}
