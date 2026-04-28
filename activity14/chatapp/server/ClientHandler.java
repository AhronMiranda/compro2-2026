package com.chatapp.server;

import com.chatapp.data.DataHandler;
import com.chatapp.model.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientHandler implements Runnable {

    private static AtomicInteger clientCount = new AtomicInteger(0);
    private static final List<PrintWriter> clients = new ArrayList<>();

    private Socket socket;
    private PrintWriter clientOut;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        clientCount.incrementAndGet();

        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {

            clientOut = out;

            synchronized (clients) {
                clients.add(out);
            }

            while (true) {

                String action = in.readLine();
                if (action == null) break;

                String username = in.readLine();
                String password = in.readLine();

                switch (action) {

                    case "LOGIN":
                        if (DataHandler.authenticate(username, password)) {
                            out.println("[LOGGED IN]");
                            chatLoop(in, username);
                        } else {
                            out.println("[LOGIN FAILED]");
                        }
                        break;

                    case "REGISTER":
                        if (DataHandler.userExists(username)) {
                            out.println("[USER EXISTS]");
                        } else {
                            DataHandler.saveUser(new User(username, password));
                            out.println("[REGISTERED]");
                        }
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            clientCount.decrementAndGet();

            synchronized (clients) {
                clients.remove(clientOut);
            }
        }
    }

    // CHAT MODE
    private void chatLoop(BufferedReader in, String username) throws IOException {

        String msg;

        while ((msg = in.readLine()) != null) {

            if (msg.equalsIgnoreCase("EXIT")) {
                broadcast("[SERVER] " + username + " left the chat.");
                break;
            }

            broadcast(username + ": " + msg);
        }
    }

    // BROADCAST
    private void broadcast(String message) {

        synchronized (clients) {
            for (PrintWriter client : clients) {
                client.println(message);
            }
        }

        System.out.println(message);
    }
}