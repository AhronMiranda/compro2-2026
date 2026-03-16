package com.hangman.miranda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    //TYPE DESIGN
        public static final String RED = "\033[0;31m";
        public static final String GREEN = "\033[0;32m";
        public static final String BLUE = "\033[0;34m";
        public static final String BOLD = "\u001B[1m";
        public static final String ITALIC = "\u001B[3m";
        public static final String RESET = "\033[0m";

    public static Scanner sc = new Scanner(System.in);
    public static HashMap<String, Integer> hm = new HashMap<>();
    public static ArrayList<Player> users = new ArrayList<Player>();
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
                login();
                break;
            case 2:
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
            while (true) {

            System.out.print("[ENTER THE USERNAME] user>>");
            String user = strInput();

            if (hm.containsKey(user) || users.contains(user)) {

            System.out.print("[ENTER THE PASSWORD] user>>");
            int pw = intInput();

            if ( (hm.containsKey(user) && hm.containsValue(pw)) || (users.contains(user))) {
            break;
            } else {
                System.out.println("Wrong Password");
            }
            } else {
                System.out.println(RED + "NO ONE IS REGISTERED WITH THAT USERNAME" + RESET);
                System.out.println(RED + BOLD + "REGISTER FIRST!" + RESET);
                greeting();
                }
            }

            System.out.println(GREEN + "[USER RECOGNIZED" + RESET);
            difficulty();
            
    }

    public static void register() {
        System.out.print("""

            +-----------------------------------------------+
            |                   REGISTER                    |
            +-----------------------------------------------+
            
            [ENTER THE USERNAME] user>>
                """);
            Player registeringPlayer = new Player();
            String user = strInput();
            registeringPlayer.setUsername(user);

            System.out.print("[ENTER THE PASSWORD] user>>");
            int pw = intInput();
            registeringPlayer.setPassword(pw);

            hm.put(user, pw);
            users.add(registeringPlayer);

            System.out.println(GREEN + "[USER NOW REGISTERED]" +  RESET);
            difficulty();
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

            switch (intInput()) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    greeting();
                    break;
                
            }
    }

    public static void afterGame() {
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
                    break;
                case 2:
                    break;
                case 0:
                    greeting();
                    break;
                
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

}