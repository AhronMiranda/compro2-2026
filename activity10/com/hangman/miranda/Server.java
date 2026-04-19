package com.hangman.miranda;

import java.io.*;
import java.net.*;
import java.util.*;
import com.hangman.miranda.services.GameLogicService;

public class Server {

    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String RESET = "\033[0m";

    public static void main(String[] args) {
        int port = 8000;

        try (ServerSocket server = new ServerSocket(port)) {

            System.out.println("Server started waiting for client to join...");
            Socket client = server.accept();
            
            System.out.println("PLAYER HAS LOGGED IN");

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            while (true) {

                

                String msg = in.readLine();
                if (msg == null)
                    continue;

                if (msg.equalsIgnoreCase("INITIALIZE GAME")) {

                    GameLogicService game = new GameLogicService();

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
                    game.initialize(dif);

                    while (true) {

                        out.println("Word: " + game.getHiddenWord());
                        out.println("Lives: " + game.getLives() + " | Score: " + game.getScore());
                        out.println("Guess>>");

                        String g = in.readLine();
                        if (g == null)
                            continue;

                        out.println(game.processGuess(g));

                        if (game.isGameOver()) {
                            if (game.isWin())
                                out.println("YOU WIN!");
                            else
                                out.println("YOU LOSE! Word: " + game.getWord());

                            out.println("SCORE:" + game.getScore());
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