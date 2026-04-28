package com.chatapp.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private static Scanner scanner = new Scanner(System.in);

    // STATUS FLAGS
    private static volatile boolean loginSuccess = false;
    private static volatile boolean loginFail = false;

    private static volatile boolean registerSuccess = false;
    private static volatile boolean registerFail = false;

    public static void main(String[] args) {

        try (
                Socket socket = new Socket("localhost", 12345);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {

            // RECEIVER THREAD
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {

                        System.out.println(msg);

                        // LOGIN FLAGS
                        if (msg.equals("[LOGGED IN]")) loginSuccess = true;
                        if (msg.equals("[LOGIN FAILED]")) loginFail = true;

                        // REGISTER FLAGS
                        if (msg.equals("[REGISTERED]")) registerSuccess = true;
                        if (msg.equals("[USER EXISTS]")) registerFail = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            boolean running = true;

            while (running) {

                menu();

                int choice = intInput("Select: ");

                switch (choice) {

                    case 1:
                        resetFlags();

                        if (login(out)) {
                            chat(out);
                        }
                        break;

                    case 2:
                        resetFlags();
                        register(out);
                        break;

                    case 3:
                        running = false;
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // RESET FLAGS BEFORE EACH ATTEMPT
    static void resetFlags() {
        loginSuccess = false;
        loginFail = false;
        registerSuccess = false;
        registerFail = false;
    }

    // MENU
    static void menu() {
        System.out.println("+------------------+");
        System.out.println("| [1] LOGIN        |");
        System.out.println("| [2] REGISTER     |");
        System.out.println("| [3] EXIT         |");
        System.out.println("+------------------+");
    }

    static int intInput(String prompt) {
        System.out.print(prompt);
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    static String strInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // LOGIN (NOW RETURNS BOOLEAN)
    static boolean login(PrintWriter out) {

        String user = strInput("Username: ");
        String pass = strInput("Password: ");

        out.println("LOGIN");
        out.println(user);
        out.println(pass);

        // WAIT FOR RESPONSE
        while (!loginSuccess && !loginFail) {
            sleep(50);
        }

        if (loginSuccess) {
            System.out.println("[SYSTEM] Login successful. Entering chat...");
            return true;
        } else {
            System.out.println("[SYSTEM] Login failed. Returning to menu.");
            return false;
        }
    }

    // REGISTER (RETURN TO MENU ALWAYS)
    static void register(PrintWriter out) {

        String user = strInput("Username: ");
        String pass = strInput("Password: ");

        out.println("REGISTER");
        out.println(user);
        out.println(pass);

        // WAIT FOR RESPONSE
        while (!registerSuccess && !registerFail) {
            sleep(50);
        }

        if (registerSuccess) {
            System.out.println("[SYSTEM] Registered successfully.");
        } else {
            System.out.println("[SYSTEM] Username already exists.");
        }
    }

    // CHAT MODE
    static void chat(PrintWriter out) {

        System.out.println("TYPE EXIT TO LEAVE CHAT");

        while (true) {

            String msg = scanner.nextLine();

            if (msg.equalsIgnoreCase("exit")) {
                out.println("EXIT");
                break;
            }

            out.println(msg);
        }
    }

    static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}