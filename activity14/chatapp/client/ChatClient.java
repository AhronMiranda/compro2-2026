package com.chatapp.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try (
                Socket socket = new Socket("localhost", 12345);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {

            boolean running = true;

            while (running) {

                menu(); // MENU
                int choice = intInput("Select option: "); // INPUT

                switch (choice) {

                    case 1: // LOGIN
                        handleLogin(in, out);
                        break;

                    case 2: // REGISTER
                        handleRegister(in, out);
                        break;

                    case 3: // EXIT
                        System.out.println("Exiting...");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MENU
    public static void menu() {
        System.out.println("+----------------------+");
        System.out.println("|      CHAT MENU       |");
        System.out.println("+----------------------+");
        System.out.println("| [1] Login            |");
        System.out.println("| [2] Register         |");
        System.out.println("| [3] Exit             |");
        System.out.println("+----------------------+");
    }

    // INPUT
    public static int intInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // CLEAR
        return value;
    }

    // INPUT
    public static String strInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // LOGIN
    private static void handleLogin(BufferedReader in, PrintWriter out) throws IOException {

        while (true) { // LOOP

            System.out.println("\n+----------------------+");
            System.out.println("|        LOGIN         |");
            System.out.println("+----------------------+");
            System.out.println("| [1] Continue         |");
            System.out.println("| [0] Back             |");
            System.out.println("+----------------------+");

            int choice = intInput("Choice: ");

            if (choice == 0)
                return; // BACK

            String username = strInput("Username: ");
            String password = strInput("Password: ");

            out.println("LOGIN");
            out.println(username);
            out.println(password);

            String response = in.readLine();
            System.out.println(response);

            if (response.contains("successful")) {
                chatLoop(out); // CHAT
                break;
            }
        }
    }

    // REGISTER
    private static void handleRegister(BufferedReader in, PrintWriter out) throws IOException {

        while (true) { // LOOP

            System.out.println("\n+----------------------+");
            System.out.println("|      REGISTER        |");
            System.out.println("+----------------------+");
            System.out.println("| [1] Continue         |");
            System.out.println("| [0] Back             |");
            System.out.println("+----------------------+");

            int choice = intInput("Choice: ");

            if (choice == 0)
                return; // BACK

            String username = strInput("Username: ");
            String password = strInput("Password: ");

            out.println("REGISTER");
            out.println(username);
            out.println(password);

            System.out.println(in.readLine());
        }
    }

    // CHAT
    private static void chatLoop(PrintWriter out) {
        System.out.println("Enter messages (type 'exit' to leave):");

        while (true) {
            String msg = scanner.nextLine();

            if (msg.equalsIgnoreCase("exit")) {
                out.println("EXIT"); // SIGNAL
                break;
            }

            out.println(msg);
        }
    }
}