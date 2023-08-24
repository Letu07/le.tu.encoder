package ua.javarush.encoder.cipher;

import java.util.ArrayList;

public class CaesarCipher {

    private final ArrayList<Character> alphabet;

    public CaesarCipher(ArrayList<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public String encoder(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (alphabet.contains(character)) {
                int originalIndex = alphabet.indexOf(character);
                int newIndex = (originalIndex + key) % alphabet.size();
                char newCharacter = alphabet.get(newIndex);
                encryptedText.append(newCharacter);
            } else {
                encryptedText.append(character);
            }
        }
        return encryptedText.toString();
    }

    public String decoder(String text, int key) {
        StringBuilder decryptedText = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (alphabet.contains(character)) {
                int originalIndex = alphabet.indexOf(character);
                int newIndex = (originalIndex - key + alphabet.size()) % alphabet.size();
                char newCharacter = alphabet.get(newIndex);
                decryptedText.append(newCharacter);
            } else {
                decryptedText.append(character);
            }
        }
        return decryptedText.toString();
    }
}

