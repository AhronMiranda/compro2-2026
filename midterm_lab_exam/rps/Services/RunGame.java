package com.rps.Services;

import java.io.*;
import com.rps.Model.Player;
import com.rps.Services.ServiceAttributes.*;

public class RunGame {

    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String RESET = "\033[0m";

    public void start(BufferedReader in1, PrintWriter out1,
                      BufferedReader in2, PrintWriter out2,
                      Player p1, Player p2) throws IOException {

        Tools Scissor = new Scissors();
        Tools Rock = new Rock();
        Tools Paper = new Paper();

        while (true) {
            out1.println("""
                +-----------------------------------+
                | What is your move?....            |
                +-----------------------------------+
                
                [1] ROCK
                [2] PAPER
                [3] SCISSORS
                [0] EXIT GAME

                user>>
            """);

            out2.println("""
                +-----------------------------------+
                | What is your move?....            |
                +-----------------------------------+

                [1] ROCK
                [2] PAPER
                [3] SCISSORS
                [0] EXIT GAME

                user>>
            """);

            if (in1 != null) {
                out1.println("Waiting for other player.");
            }
            if (in2 != null) {
                out2.println("Waiting for other player.");
            }

            String m1Str = in1.readLine();
            String m2Str = in2.readLine();

            if (m1Str == null || m2Str == null) {
                System.out.println(RED + "A player disconnected." + RESET);
                return;
            }

            if (m1Str.isEmpty() || m2Str.isEmpty()) {
                out1.println(RED + "Invalid input from a Player!" + RESET);
                out2.println(RED + "Invalid input from a Player!" + RESET);
                continue;
            }

            int m1 = Character.getNumericValue(m1Str.charAt(0));
            int m2 = Character.getNumericValue(m2Str.charAt(0));

            if (m1 > 3 || m2 > 3) {
                out1.println(RED + "Invalid input from a Player!" + RESET);
                out2.println(RED + "Invalid input from a Player!" + RESET);
                continue;
            }

            if (m1 == 0 || m2 == 0) {
                out1.println(RED + "GAME END" + RESET);
                out2.println(RED + "GAME END" + RESET);

                out1.println(GREEN + "UPDATE_SCORE:" + p1.getWins() + ":" + p1.getLoses() + RESET);
                out2.println(GREEN + "UPDATE_SCORE:" + p2.getWins() + ":" + p2.getLoses() + RESET);

                break;
            }


            //MOVE DISPLAY USING OOP ABSTRACTION
            switch (m1) {
                case 1:
                    out1.println(RED + Rock.runMove() + RESET);
                    break;
                case 2:
                    out1.println(RED + Paper.runMove() + RESET );
                    break;
                case 3:
                    out1.println(RED + Scissor.runMove() + RESET);

                    break;
            }

            switch (m2) {
                case 1:
                    out2.println(RED + Rock.runMove() + RESET );
                    break;
                case 2:
                    out2.println(RED + Paper.runMove() + RESET);
                    break;
                case 3:
                    out2.println(RED + Scissor.runMove()+ RESET);
                    break;
            }

            

            String result = getWinner(m1, m2);

            out1.println(result);
            out2.println(result);

            if (result.equals("Player 1 wins!")) {
                p1.addWin();
                p2.addLose();
            } else if (result.equals("Player 2 wins!")) {
                p2.addWin();
                p1.addLose();
            }
        }
    }

    private String getWinner(int m1, int m2) {
        if (m1 == m2) return "Draw!";
        if ((m1 == 1 && m2 == 3) || (m1 == 3 && m2 == 2) || (m1 == 2 && m2 == 1)) {
            return "Player 1 wins!";
        }
        return "Player 2 wins!";
    }
}