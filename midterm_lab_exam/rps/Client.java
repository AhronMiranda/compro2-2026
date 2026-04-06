package com.rps;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rps.Model.Player;

public class Client {

    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String RESET = "\033[0m";

    public static Scanner sc = new Scanner(System.in);
    public static Player currentUser = null;

    public static ArrayList<Player> users = new ArrayList<Player>();

    public static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void main(String[] args) {
        String server = "127.0.0.1";
        int port = 8000;

        try (
            Socket socket = new Socket(server, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {

            System.out.println("Connected to server!");

            System.out.println("[LOADED PLAYERS SUCCESSFULLY]");

            greeting();

            if (currentUser == null) {
                System.out.println("No user logged in.");
                return;
            }

            // SEND LOGIN INFO TO SERVER
            out.println(currentUser.getUsername());
            out.println(currentUser.getPassword());

            while (true) {
                String reply = in.readLine();

                if (reply == null) {
                    System.out.println("Server disconnected.");
                    break;
                }

                // HANDLES UPDATING THE SCORE
                if (reply.startsWith("UPDATE_SCORE:")) {
                    String[] parts = reply.split(":");
                    int wins = Integer.parseInt(parts[1]);
                    int loses = Integer.parseInt(parts[2]);
                    currentUser.setWins(wins);
                    currentUser.setLoses(loses);
                    currentUser.setWinrate(currentUser.getWinRate());
                    System.out.println(GREEN + "Score updated!" + RESET +
                        " WIN: " + wins + " LOSE: " + loses + 
                        " WINRATE: " + currentUser.getWinRate() + "%");
                    continue;
                }

                System.out.println(reply);

                //TO RECOGNIZE WHEN IT WILL ASK FOR YOUR MOVE
                if (reply.contains("user>>")) {
                    String msg = sc.nextLine();
                    out.println(msg);
                }

                //TO RECOGNIZE A QUIT FUNCTION OR COMMAND JUST IN CASE THE USER NEEDS IT
                if (reply.equalsIgnoreCase("/quit")) {
                    System.out.println("Disconnected.");
                    break;
                }

                //TO REDIRECT THE USER BACK TO THE LOG IN SCREEN
                if (reply.equalsIgnoreCase("BACK TO LOGIN SCREEN")) {
                    greeting();
                    out.println(currentUser.getUsername());
                    out.println(currentUser.getPassword());
                }
            }

        } catch (IOException e) {
            System.out.println(RED + "USER DISCONNECTED" + RESET);
        }
    }

    // ========== MENU & LOGIN LOGIC ==========

    public static void greeting() {
        loadRegistered();
        System.out.println("""
            +----------------------+
            |     ROSHAMBO GAME    |
            +----------------------+
            [1] LOGIN
            [2] REGISTER
            [0] EXIT
        """);

        int choice = intInput();
        switch (choice) {
            case 1 -> login();
            case 2 -> register();
            case 0 -> System.exit(0);
            default -> {
                System.out.println(RED + "[INVALID INPUT]" + RESET);
                greeting();
            }
        }
    }

        public static void login() {
        System.out.print("""
        +-----------------------------------------------+
        |                    LOGIN                      |
        +-----------------------------------------------+

        """);

        Player foundUser = null;

        while (foundUser == null) {
            System.out.print("Username: ");
            String user = sc.nextLine();

            for (Player p : users) {
                if (p.getUsername().equals(user)) {
                    foundUser = p;
                    break;
                }
            }

            if (foundUser == null) {
                System.out.println(RED + "NO ONE IS REGISTERED WITH THAT USERNAME" + RESET);
            }
        }

        while (true) {
            System.out.print("Passcode: ");
            int pw = intInput();

            if (foundUser.getPassword() == pw) {
                currentUser = new Player(foundUser.getUsername(), pw);
                System.out.println(GREEN + "Login ready! Server will verify..." + RESET);
                break;
            } else {
                System.out.println(RED + "Wrong password. Try again." + RESET);
            }
        }
    }

        public static void register() {
        loadRegistered(); // PARA SURE NA LOAD YUNG EXISTING USERS

        System.out.print("""
    +-----------------------------------------------+
    |                   REGISTER                    |
    +-----------------------------------------------+

    """);

        String user;
        while (true) {
            System.out.print("New username: ");
            user = sc.nextLine();

            boolean exists = false;
            for (Player p : users) {
                if (p.getUsername().equals(user)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println(RED + "Username already exists! Try another." + RESET);
            } else {
                break;
            }
        }

        System.out.print("Passcode: ");
        int pw = intInput();
        
        
        currentUser = new Player(user, pw);
        users.add(currentUser);
        saveRegister(); //SAVE NEW PLAYER
        System.out.println(GREEN + "Registered! Server will save your data." + RESET);
    }

    //LOAD
    public static void loadRegistered() {
    try (FileReader fr = new FileReader("players.json")) {

        Type listType = new TypeToken<ArrayList<Player>>() {}.getType();
        ArrayList<Player> loadedList = gson.fromJson(fr, listType);

        if (loadedList != null) {
            users = loadedList;

        } else {
            users = new ArrayList<>();
        }

        

    } catch (IOException e) {
        users = new ArrayList<>();
        System.out.println("[NO SAVE FILE FOUND]");
        }
    }

    //SAVE

    public static void saveRegister() {
        try (FileWriter fw = new FileWriter("players.json")) {
            gson.toJson(users, fw);
            System.out.println("[SAVED SUCCESSFULLY]");
        } catch (IOException e) {
            System.out.println("[ERROR SAVING FILE]");
            e.printStackTrace();
        }
    }

    // ========== UTIL ==========

    public static int intInput() {
        while (true) {
            try {
                int val = sc.nextInt();
                sc.nextLine();
                return val;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println(RED + "[INVALID INPUT]" + RESET);
            }
        }
    }
}

/*
    Explaination for Sir

    I added Confirmation logic on the Client Side by allowing the Internals to access the json file which is basically the database.
    Before the Client will pass on the username and password text to the server, the actual login details and registering details will be confirmed beforehand
    through the client authentication logic, so yes I am opening the Json file on both the Data Handler and the Client program, 
    however one is done to authenticate the player client side, and create a new player if they choose to register,
    while the Data Handler Program focuses more on adjusting player stats, assigning, file handling, and sorting.
    
    The Server Program acts as the middle ground where all the communication between the programs happen.
    I made sure to incorporate basic abstraction like the vocal instruction said during class, and I also made sure to use my own logic
    when dealing with what happens "under the hood" in the Rock Paper Scissors Program.

    I also made the Add win and Add lose logic so that the Player data model
    is being manipulated directly before being saved.

    Its better as a method rather than setting wins and loses since
    it will require more calculation and saving the before or after, or keeping track of old data
    by having add win and add lose the model does it for you.

    Overall I think this is a pretty good code logic for it.
    */