package com.hangman.miranda.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GameLogicService {

    private String word;
    private String[] hidden;
    private int lives;
    private int score;
    private String guessed = "";

    public void initialize(String difficulty) {
        String file;

        switch (difficulty) {
            case "1":
                file = "easy.txt";
                break;
            case "2":
                file = "medium.txt";
                break;
            case "3":
                file = "hard.txt";
                break;
            default:
                file = "easy.txt";
        }

        List<String> words = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(file))) {
            while (sc.hasNextLine())
                words.add(sc.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] Word file not found.");
        }

        if (words.isEmpty()) {
            word = "default";
        } else {
            word = words.get(new Random().nextInt(words.size())).toLowerCase();
        }
        hidden = new String[word.length()];
        Arrays.fill(hidden, "*");

        lives = 6;
        score = 0;
        guessed = "";
    }

    public String getHiddenWord() {
        StringBuilder sb = new StringBuilder();
        for (String s : hidden)
            sb.append(s);
        return sb.toString();
    }

    public String processGuess(String g) {
        g = g.toLowerCase();

        if (g.length() != 1 || guessed.contains(g)) {
            return "Invalid.";
        }

        guessed += g;

        if (word.contains(g)) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == g.charAt(0)) {
                    hidden[i] = g;
                }
            }
            score += 5;
            return "[Correct] Score +5";
        } else {
            lives--;
            score -= 3;
            return "[Wrong] Score -3";
        }
    }

    public boolean isGameOver() {
        return lives == 0 || isWin();
    }

    public boolean isWin() {
        for (String s : hidden) {
            if (s.equals("*"))
                return false;
        }
        return true;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public String getWord() {
        return word;
    }
}