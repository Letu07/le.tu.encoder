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
            int newIndex = (originalIndex + key) % alphabet.size();
            if (newIndex < 0) {
                newIndex += alphabet.size();
            }
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

    public String decoder(String encryptedText, int key) {
        StringBuilder decryptedText = new StringBuilder();

        for (char character : encryptedText.toCharArray()) {
            decryptedText.append(shiftCharacter(character, -key));
        }

        return decryptedText.toString();
    }

    public String bruteForceDecrypt(String encryptedText) {
        String decryptedText = "";

        for (int key = 1; key <= alphabet.size(); key++) {
            decryptedText = decoder(encryptedText, key);
            if (decryptedText.contains("the")) {
                break;
            }
        }
        return decryptedText;
    }
}
