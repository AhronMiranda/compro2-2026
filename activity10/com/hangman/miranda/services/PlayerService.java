package com.hangman.miranda.services;

import java.io.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.hangman.miranda.Player;

public class PlayerService {

    private static final String FILE = "players.json";
    private static Gson gson = new Gson();

    public static ArrayList<Player> load() {
        try (FileReader fr = new FileReader(FILE)) {
            Type type = new TypeToken<ArrayList<Player>>(){}.getType();
            ArrayList<Player> users = gson.fromJson(fr, type);
            return (users != null) ? users : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void save(ArrayList<Player> users) {
        try (FileWriter fw = new FileWriter(FILE)) {
            gson.toJson(users, fw);
        } catch (IOException e) {}
    }

    public static void leaderboard(ArrayList<Player> users) {
        users.sort((a, b) -> b.getScore() - a.getScore());

        System.out.println("=== LEADERBOARD ===");
        int i = 1;
        for (Player p : users) {
            System.out.println(i++ + ". " + p.getUsername() + " - " + p.getScore());
        }
    }
}