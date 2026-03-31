package com.hangman.miranda;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class Main {

    //TYPE DESIGN
        public static final String RED = "\033[0;31m";
        public static final String GREEN = "\033[0;32m";
        public static final String BLUE = "\033[0;34m";
        public static final String BOLD = "\u001B[1m";
        public static final String ITALIC = "\u001B[3m";
        public static final String RESET = "\033[0m";

    public static int score = 0;

    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Player> users = new ArrayList<Player>();

    public static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void main(String[] args) {
        
        greeting();
    }

    public static void greeting() {
        System.out.println("""
            +-----------------------------------------------+
            |                  HANGMAN GAME                 |
            +-----------------------------------------------+

            [1] LOGIN
            [2] REGISTER
            [0] CLOSE GAME

                """);
            System.out.print("user>>");

            switch (intInput()) {
            case 1:
                loadRegistered();
                login();
                break;
            case 2:
                loadRegistered();
                register();
                break;
            case 0:
                System.exit(0);
        }
    }
    

    public static void login() {
    System.out.print("""
        +-----------------------------------------------+
        |                    LOGIN                      |
        +-----------------------------------------------+
    """);
        Player foundUser = null;
    while (true) {
        System.out.print("[ENTER THE USERNAME] user>>");
        String username = strInput();

        

        for (Player p : users) {
            if (p.getUsername().equals(username)) {
                foundUser = p;
                break;
            }
        }

        if (foundUser != null) {
            System.out.print("[ENTER THE PASSWORD] user>>");
            int pw = intInput();

            if (foundUser.getPassword() == pw) {
                break;
            } else {
                System.out.println("Wrong Password");
            }
        } else {
            System.out.println(RED + "NO ONE IS REGISTERED WITH THAT USERNAME" + RESET);
            greeting();
            return;
        }
    }

    System.out.println(GREEN + "[USER RECOGNIZED]" + RESET);
    difficulty();

    
    if (score > foundUser.getScore()) {
    foundUser.setScore(score);
    }
    saveRegister();
    afterGame(foundUser);
    
}

    public static void register() {
        System.out.print("""
    
            +-----------------------------------------------+
            |                   REGISTER                    |
            +-----------------------------------------------+
            
            [ENTER THE USERNAME] user>>
                """);
            String user = strInput();
            

            System.out.print("[ENTER THE PASSWORD] user>>");
            int pw = intInput();
            Player registeringPlayer = new Player(user, pw);

            for (Player p : users) {
            if (p.getUsername().equals(user)) {
            System.out.println("Username already exists!");
            greeting();
            return;
                }
            }

            users.add(registeringPlayer);

            saveRegister();

            System.out.println(GREEN + "[USER NOW REGISTERED]" +  RESET);
            difficulty();
            if (score > registeringPlayer.getScore()) {
                registeringPlayer.setScore(score);
            }
            saveRegister();
            afterGame(registeringPlayer);
    }

    public static void difficulty() {
        System.out.println("""
            +-----------------------------------------------+
            |                  HANGMAN GAME                 |
            +-----------------------------------------------+

            [1] EASY
            [2] MEDIUM
            [3] HARD
            [0] BACK TO LOGIN SCREEN


                """);
            
            System.out.print("user>>");
            int dif = intInput();
            if (dif == 0) {
                greeting();
                return;
            } else {
                Game game = new Game();
                score = game.runGame(dif);
            }
    }

    public static void afterGame(Player currentUser) {
    while (true) {
        System.out.println("""
            +-----------------------------------------------+
            |                  HANGMAN GAME                 |
            +-----------------------------------------------+

            [1] PLAY ANOTHER ROUND
            [2] CHECK THE LEADERBOARD
            [0] BACK TO LOGIN SCREEN
        """);

        System.out.print("user>>");

        switch (intInput()) {
            case 1:
                difficulty();

                if (score > currentUser.getScore()) {
                    currentUser.setScore(score);
                    saveRegister();
                }
                break;

            case 2:
                printLeaderboard();
                break;

            case 0:
                greeting();
                return; 
        }
    }
}

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

    // ========================== SAVE, LOAD, ADD Player

   public static void saveRegister() {
        try (FileWriter fw = new FileWriter("players.json")) {
            gson.toJson(users, fw);
            System.out.println("[SAVED SUCCESSFULLY]");
        } catch (IOException e) {
            System.out.println("[ERROR SAVING FILE]");
            e.printStackTrace();
        }
    }

    public static void loadRegistered() {
    try (FileReader fr = new FileReader("players.json")) {

        Type listType = new TypeToken<ArrayList<Player>>() {}.getType();
        ArrayList<Player> loadedList = gson.fromJson(fr, listType);

        if (loadedList != null) {
            users = loadedList;

        } else {
            users = new ArrayList<>();
        }

        System.out.println("[LOADED PLAYERS SUCCESSFULLY]");

    } catch (IOException e) {
        users = new ArrayList<>();
        System.out.println("[NO SAVE FILE FOUND]");
        }
    }

    // ==========================

    public static void printLeaderboard() {
    // Sort before printing
    users.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));

    System.out.println("""
        +-----------------------------------------------+
        |                 LEADERBOARD                   |
        +-----------------------------------------------+
    """);
    int ctr = 1;
    for (Player p : users) {
        System.out.println("Player Rank " + ctr++ + ":" + p.getUsername() + " | Score: " + p.getScore());
    }
}

}