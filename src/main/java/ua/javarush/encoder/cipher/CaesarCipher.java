package ua.javarush.encoder.cipher;

import java.util.ArrayList;

public class CaesarCipher {

    private final ArrayList<Character> alphabet;

    public CaesarCipher(ArrayList<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public String shiftCharacter(char character, int key) {
        if (Character.isLetter(character)) {
            boolean isUppercase = Character.isUpperCase(character);
            character = Character.toLowerCase(character);
            int originalIndex = alphabet.indexOf(character);
            int newIndex = (originalIndex + key + alphabet.size()) % alphabet.size();
            char newChar = alphabet.get(newIndex);

            if (isUppercase) {
                return Character.toString(Character.toUpperCase(newChar));
            } else {
                return Character.toString(newChar);
            }
        }
        return Character.toString(character);
    }

    public String encoder(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();

        for (char character : text.toCharArray()) {
            encryptedText.append(shiftCharacter(character, key));
        }

        return encryptedText.toString();
    }

    public String decoder(String text, int key) {
        StringBuilder decryptedText = new StringBuilder();

        for (char character : text.toCharArray()) {
            decryptedText.append(shiftCharacter(character, -key));
        }

        return decryptedText.toString();
    }
}
