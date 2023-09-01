package ua.javarush.encoder.cipher;

import java.util.ArrayList;
import java.util.Map;

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

    //    public String bruteForceDecrypt(String encryptedText) {
//        String decryptedText = "";
//
//        for (int key = 1; key <= alphabet.size(); key++) {
//            decryptedText = decoder(encryptedText, key);
//            if (decryptedText.contains("the")) {
//                break;
//            }
//        }
//        return decryptedText;
//    }
    public String bruteForceDecrypt(String encryptedText, Map<Character, Double> frequencyMap) {
        String decryptedText = "";
        int bestKey = 0;
        double bestScore = Double.MAX_VALUE; // Начальное лучшее значение оценки

        for (int key = 1; key <= alphabet.size(); key++) {
            String candidateText = decoder(encryptedText, key);

            double score = calculateScore(candidateText, frequencyMap);

            if (score < bestScore) {
                bestScore = score;
                bestKey = key;
                decryptedText = candidateText;
            }
        }
        System.out.println("=".repeat(50));
        System.out.println("Best Key: " + bestKey);

        return decryptedText;
    }

    private double calculateScore(String text, Map<Character, Double> frequencyMap) {

        int[] letterFrequency = new int[26];
        int totalLetters = 0;

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = Character.toLowerCase(c) - 'a';
                letterFrequency[index]++;
                totalLetters++;
            }
        }

        double score = 0.0;
        for (int i = 0; i < 26; i++) {
            char letter = (char) ('a' + i);
            double expectedFrequency = frequencyMap.get(letter) * totalLetters;
            double observedFrequency = letterFrequency[i];
            score += Math.pow(expectedFrequency - observedFrequency, 2) / expectedFrequency;
        }

        return score;
    }
}
