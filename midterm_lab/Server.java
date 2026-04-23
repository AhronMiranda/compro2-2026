package com.rps;

import java.io.*;
import java.net.*;
import com.rps.Services.DataHandler;
import com.rps.Services.RunGame;
import com.rps.Model.Player;

public class Server {
    public static void main(String[] args) {
        int port = 8000;
        DataHandler dataHandler = new DataHandler();

        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server started, waiting for clients...");

            Socket client1 = server.accept();
            Socket client2 = server.accept();

            BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
            PrintWriter out1 = new PrintWriter(client1.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            PrintWriter out2 = new PrintWriter(client2.getOutputStream(), true);

            // GETS OR RECOGNIZED THE USERNAME AND PASSWORD
            String username1 = in1.readLine();
            int password1 = Integer.parseInt(in1.readLine());
            String username2 = in2.readLine();
            int password2 = Integer.parseInt(in2.readLine());

            // LOAD OR CREATE PLAYERS
            Player p1 = dataHandler.getOrCreatePlayer(username1, password1);
            Player p2 = dataHandler.getOrCreatePlayer(username2, password2);

            out1.println(p1.getUsername() + " you are Player 1");
            out2.println(p2.getUsername() + " you are Player 2");

            // START
            RunGame rg = new RunGame();
            rg.start(in1, out1, in2, out2, p1, p2);

            // After game, update JSON
            dataHandler.updatePlayer(p1);
            dataHandler.updatePlayer(p2);


            System.out.println("Game finished. Scores saved.");

            //AFTER GAME PRINT LEADERBOARD
            out1.println(formatLeaderboard(dataHandler.getLeaderboard()));
            out2.println(formatLeaderboard(dataHandler.getLeaderboard()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatLeaderboard(java.util.List<Player> leaderboard) {
    StringBuilder sb = new StringBuilder();
    sb.append("""
            +--------------------------+
            |       LEADERBOARD        |
            +--------------------------+

            """);
    int i = 1;
    for (Player p : leaderboard) {
        sb.append(i++)
          .append(". ")
          .append(p.getUsername())
          .append(" - W: ")
          .append(p.getWins())
          .append(" L: ")
          .append(p.getLoses())
          .append(" Winrate: ")
          .append(Math.round(p.getWinRate()))
          .append("%\n");
    }
    return sb.toString();
}
}