package com.hangman.miranda;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    //TYPE DESIGN
        public static final String RED = "\033[0;31m";
        public static final String GREEN = "\033[0;32m";
        public static final String BLUE = "\033[0;34m";
        public static final String BOLD = "\u001B[1m";
        public static final String ITALIC = "\u001B[3m";
        public static final String RESET = "\033[0m";
        
    public int round = 0;
    public static String file = " ";
    public static Scanner sc = new Scanner(System.in);
    public static Scanner FR = new Scanner(file);
    public static int currentGame = 0; // game no.


    
    public int runGame(int difficulty){
        while (true) {
            String[] aWords = new String[20];
            String wordToGuess = "";
            int ctr = 0;
            int score;


            switch (difficulty) {
                case 1:
                    System.out.println("EASY MODE!");
                    file = "easy.txt";
                    score = 0;
                break;
                case 2:
                    System.out.println("MEDIUM MODE!");
                    file = "medium.txt";
                    score = 0;
                break;
                case 3:
                    System.out.println("HARD MODE!");
                    file = "hard.txt";
                    score = 0;
                break;
                default:
                    System.out.println("DEFAULT: EASY MODE!");
                    file = "easy.txt";
                    score = 0;
                break;
            }

            //SAVE TXT FILE INTO A 1D ARRAY
                while (FR.hasNextLine()) {
                aWords[ctr] = FR.nextLine();     
                if (!FR.hasNextLine()) break;
                ctr++;
                }

                wordToGuess = aWords[(int)(Math.random() * 20)];

                //MAKE HIDDEN WORD
                String[] hiddenWord = new String[wordToGuess.length()];
                hide(wordToGuess, hiddenWord);

                boolean playing = true; //Game start to be able to loop the game!
                System.out.println(); // space
        

                int lives = 6;
                int guessed = 0;
                String guessedLetters = "";
        
                while (playing) {
            
                System.out.print("\nGuess: ");
                String guess = strInput().toLowerCase();

                boolean run = false;
                
                if (guess.length() == 1) {
                    run = true;
                } else {
                    System.out.println("[INVALID] Input one character!");
                    continue; // no life lost
                }

                // Check if already guessed
                if (guessedLetters.contains(guess)) {
                    System.out.println("[INVALID] You already guessed that letter!");
                    continue; // no life lost
                }

                // Record new guess
                guessedLetters += guess;

                while (run) {
                    if (wordToGuess.contains(guess)) {
                        for (int i = 0; i < wordToGuess.length(); i++) {
                            if (wordToGuess.charAt(i) == (guess.charAt(0))) {
                            hiddenWord[i] = guess;
                            guessed++;
                        }
                    }
                    System.out.print("[CORRECT GUESS] Updated word is ");
                    for (int i = 0; i < wordToGuess.length(); i++) {
                    System.out.print(hiddenWord[i] + "");
                    }
                    


                    if (guessed == wordToGuess.length()) {
                        System.out.println();
                        System.out.println("YOU WON!!\n");
                        playing = false;
                    }
                    score += 1;
                    break;
                    } else {
                        System.out.println("[WRONG GUESS] The word does not contain that.");
                        lives--;
                        score -= 1;

                        if (lives == 0) {
                                System.out.println("YOU LOSE GAME OVER!!\n");
                                playing = false;
                            }
                        break;
                    }
                }
                }
                
        return score;
    } // END OF GAME

        
    }

    
    //======================= INPUTS

    
    public static String strInput(){
        while (true) {
            try {
                String temp = sc.nextLine();
                return temp;
            } catch (InputMismatchException e) {
                System.out.println(RED + "[INVALID INPUT]" + RESET);
            }
        }
    }

    public static int intInput(){
        while (true) {
            try {
                int temp = sc.nextInt();
                sc.nextLine();
                return temp;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println(RED + "[INVALID INPUT]" + RESET);
            }
        }
    }

    // ======================== OTHER METHODS

    //HIDE THE WORD
    public static void hide(String wordToGuess, String[] hiddenWord) {
        System.out.print("You need to guess the word: ");
        for (int i = 0; i < wordToGuess.length(); i++) {
        hiddenWord[i] = "*";
        System.out.print(hiddenWord[i] + "");
        }
    }

}
