package com.hangman.miranda;

import java.io.*;
import java.net.Socket;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class Client {

    //TYPE DESIGNS
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String RESET = "\033[0m";

    // SO IT WILL RECOGNIZE SCORE AND RUN THROUGHOUT THE WHOLE AND WONT CAUSE COMPILE ISSUES
    public static int score = 0;
    public static boolean run = true;

    //SO THE PROGRAM WILL SHARE ONE SCANNER INPUT
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Player> users = new ArrayList<>();

    //SO THE PROGRAM WILL RECOGNIZE THE LOGGED IN PLAYER, OR REGISTERED PLAYER AND SAVING LOGIC WILL WORK
    public static Player currentUser = null;

    // clean json print
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        String server = "127.0.0.1"; //DEFAULT PORT
        int port = 8000;

        try (
            Socket socket = new Socket(server, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {

            System.out.println("Connected to server!");

            greeting();

            if (run) {
                out.println("INITIALIZE GAME");
            }

            while (true) {

                String reply = in.readLine();
                if (reply == null) continue;

                // HANDLE SCORE
                if (reply.startsWith("SCORE:")) {
                    score = Integer.parseInt(reply.split(":")[1]);
                    System.out.println(GREEN + "Your score: " + score + RESET);
                    continue;
                }

                System.out.println(reply);

                if (reply.equalsIgnoreCase("BACK TO LOGIN SCREEN")) {
                    greeting();
                    out.println("INITIALIZE GAME");
                    continue;
                }

                if (reply.equalsIgnoreCase("DISPLAYING AFTER GAME MENU")) {
                    afterGame(currentUser);
                    out.println("INITIALIZE GAME");
                    continue;
                }

                if (reply.equalsIgnoreCase("/quit")) {
                    System.out.println("Disconnected.");
                    break;
                }

                if (reply.contains("user>>") || reply.contains("Guess>>")) {
                    String msg = strInput();
                    out.println(msg);
                }
            }

        } catch (IOException e) {
            System.out.println("Connection error.");
        } 
    } // MAIN

    // ================= MENU =================

    public static void greeting() {
        System.out.println("""
            +----------------------+
            |     HANGMAN GAME     |
            +----------------------+
            [1] LOGIN
            [2] REGISTER
            [0] EXIT
        """);

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
                break;
            default:
                System.out.println(RED + "[INPUT NOT RECOGNIZED]" + RESET +
                "[RUNNING DEFAULTS] Register new Player");
                loadRegistered();
                register();
                break;
        }
    }

    public static void login() {
        System.out.print("Username: ");
        String user = strInput();

        for (Player p : users) {
            if (p.getUsername().equals(user)) {
                System.out.print("Password: ");
                int pw = intInput();

                if (p.getPassword() == pw) {
                    currentUser = p;
                    System.out.println(GREEN + "Login success!" + RESET);
                    return;
                }
            }
        }

        System.out.println(RED + "Login failed." + RESET);
        greeting();
    }

    public static void register() {
        System.out.print("New username: ");
        String user = strInput();

        for (Player p : users) {
            if (p.getUsername().equals(user)) {
                System.out.println("Username exists.");
                greeting();
                return;
            }
        }

        System.out.print("Password: ");
        int pw = intInput();

        Player p = new Player(user, pw);
        users.add(p);
        currentUser = p;

        saveRegister();

        System.out.println(GREEN + "Registered!" + RESET);
    }

    //WILL RUN AFTER A ROUND OF GUESSING
    public static void afterGame(Player user) {
        if (user == null) return;

        if (score > user.getScore()) {
            user.setScore(score);
            saveRegister();
        }

        while (true) {
            System.out.println("""
                +-----------------------+
                | GOOD JOB! PLAY AGAIN? |
                +-----------------------+

                [1] Play Again
                [2] Leaderboard
                [0] Logout

            """);

            int choice = intInput();

            switch (choice) {
                case 1:
                    return;
                case 2:
                    printLeaderboard();
                    break;
                case 0: greeting(); return;
                default:
                System.out.println(RED + "[INPUT NOT RECOGNIZED]" + RESET +
                "[DEFAULT] Printing the Leaderboard. \n\n");
                printLeaderboard();
                break;
            }
        }
    }

    // ====== INPUT LOGICS AND MAKING SURE NO INPUT MISMATCH

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

    // ================= JSON ================= SAVING LOGIC

    public static void saveRegister() {
        try (FileWriter fw = new FileWriter("players.json")) {
            gson.toJson(users, fw);
        } catch (IOException e) {}
    }

    public static void loadRegistered() {
        try (FileReader fr = new FileReader("players.json")) {
            Type type = new TypeToken<ArrayList<Player>>(){}.getType();
            users = gson.fromJson(fr, type);
            if (users == null) users = new ArrayList<>();
        } catch (IOException e) {
            users = new ArrayList<>();
        }
    }

    public static void printLeaderboard() {
        users.sort((a, b) -> b.getScore() - a.getScore());

        System.out.println("=== LEADERBOARD ===");
        int i = 1;
        for (Player p : users) {
            System.out.println(i++ + ". " + p.getUsername() + " - " + p.getScore());
        }
    }
}