package com.practicesocket.services;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetwordService {
    
    public String fetchData(String host, int port, String path){
        StringBuilder response = new StringBuilder();
        //socket
        try (
        Socket socket = new Socket(host, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()
        ))) {
        //send a request
        System.out.println("Connected to server..");
        //send a HTTP request
        out.print("GET" + path + "HTTP/1.1 \r\n");
        out.print("Host: " + host + "\r\n");
        out.print("User-Agent: Java/SocketDemo \r\n");
        out.print("Accept: application/json \r\n");
        out.print("Connection: close \r\n");
        out.print("\r\n"); //ends the request header
        System.out.println("\n--- HTTP Response Headers---");

        String line;
        boolean isBody = false;
        while ((line = in.readLine()) != null) {
            if(line.isEmpty()) {
                System.out.println("[Header] " + line);
                isBody = true;
                continue;
            }

            if (isBody)
                response.append(line);
        }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }
}
