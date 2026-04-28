package com.chatapp.server;

import com.chatapp.data.DataHandler;
import com.chatapp.model.User;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientHandler implements Runnable {

    private static AtomicInteger clientCount = new AtomicInteger(0);
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        clientCount.incrementAndGet(); // COUNT

        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {

            while (true) {

                String action = in.readLine(); // INPUT
                if (action == null) break;

                String username = in.readLine();
                String password = in.readLine();

                switch (action) {

                    case "LOGIN": // LOGIN
                        if (DataHandler.authenticate(username, password)) {
                            out.println("Login successful!");
                            handleChat(in, username);
                        } else {
                            out.println("Invalid credentials.");
                        }
                        break;

                    case "REGISTER": // REGISTER
                        DataHandler.saveUser(new User(username, password));
                        out.println("Registered successfully!");
                        break;

                    default:
                        out.println("Unknown command.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clientCount.decrementAndGet(); // COUNT
        }
    }

    // CHAT
    private void handleChat(BufferedReader in, String username) throws IOException {
        String msg;
        while ((msg = in.readLine()) != null) {
            System.out.println(username + ": " + msg);
        }
    }
}