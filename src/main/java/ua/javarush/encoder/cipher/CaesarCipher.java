package ua.javarush.encoder.cipher;

import java.util.ArrayList;

public class CaesarCipher {

    private final ArrayList<Character> alphabet;

    public CaesarCipher(ArrayList<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public String encoder(String text, int key) {
        throw new UnsupportedOperationException();
    }

    public String decoder(String text, int key) {
        throw new UnsupportedOperationException();
    }

}
