package com.hangman.miranda;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String RESET = "\033[0m";

    public static void main(String[] args) {
        int port = 8000;

        try (ServerSocket server = new ServerSocket(port)) {

            System.out.println("Server started waiting for client to join...");
            Socket client = server.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            
            while (true) {

                System.out.println("PLAYER HAS LOGGED IN");

                String msg = in.readLine();
                if (msg == null) continue;

                if (msg.equalsIgnoreCase("INITIALIZE GAME")) {

                    out.println("""
                        +-----------------------+
                        |      DIFFICULTY       |
                        +-----------------------+
                        [1] EASY
                        [2] MEDIUM
                        [3] HARD

                        user>>
                    """);

                    String dif = in.readLine();
                    String file = "";

                    switch (dif) {
                        case "1": 
                            out.println("[EASY]");

                        file ="easy.txt";
                        break;
                        case "2": 
                            out.println("[MEDIUM]");

                        file ="medium.txt";
                        break;
                        case "3":
                            out.println("[HARD]");

                        file ="hard.txt";
                        break;
                        default:
                            out.println(RED + "[INPUT NOT RECOGNIZED]" + RESET +
                            "[DEFAULT INITIALIZED] EASY MODE");
                            file = "easy.txt";
                    };

                    List<String> words = new ArrayList<>();
                    try (Scanner fr = new Scanner(new File(file))) {
                        while (fr.hasNextLine()) words.add(fr.nextLine());
                    }

                    String word = words.get(new Random().nextInt(words.size())).toLowerCase();

                    String[] hidden = new String[word.length()];
                    hide(word, hidden);

                    int lives = 6;
                    int score = 0;
                    String guessed = "";

                    while (true) {

                        out.print("Word: ");
                        for (String s : hidden) out.print(s);
                        out.println();

                        out.println("Lives: " + lives + " | Score: " + score);
                        out.println("Guess>>");

                        String g = in.readLine();

                        if (g == null) continue;
                        g = g.toLowerCase();

                        if (g.length() != 1 || guessed.contains(g)) {
                            out.println("Invalid.");
                            continue;
                        }

                        guessed += g;

                        if (word.contains(g)) {
                            for (int i = 0; i < word.length(); i++) {
                                if (word.charAt(i) == g.charAt(0)) {
                                    hidden[i] = g;
                                }
                            }
                            score += 5;
                            out.println(GREEN + "[Correct] Score + 5" + RESET);
                        } else {
                            lives--;
                            score -= 3;
                            out.println(RED + "[Wrong!] Score -3" + RESET);
                        }

                        boolean win = true;
                        for (String s : hidden) {
                            if (s.equals("*")) win = false;
                        }

                        if (win || lives == 0) {
                            if (win) out.println("YOU WIN!");
                            else out.println("YOU LOSE! Word: " + word);

                            out.println("SCORE:" + score);
                            out.println("DISPLAYING AFTER GAME MENU");
                            break;
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void hide(String wordToGuess, String[] hidden) {
        System.out.print("[ADMIN] You need to guess the word: " + wordToGuess);
        System.out.print("\nHIDDEN: ");
        for (int i = 0; i < wordToGuess.length(); i++) {
        hidden[i] = "*";
        System.out.print(hidden[i] + "");
        }
    }
}