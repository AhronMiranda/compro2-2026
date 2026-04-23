package com.rps.Services;

import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.rps.Model.Player;

public class DataHandler {
    private static final String FILE_NAME = "players.json";
    private List<Player> users;
    private Gson gson;

    public DataHandler() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        loadUsers();
    }

    private void loadUsers() {
        try (FileReader fr = new FileReader(FILE_NAME)) {
            Type type = new TypeToken<List<Player>>(){}.getType();
            users = gson.fromJson(fr, type);
            if (users == null) users = new ArrayList<>();
        } catch (IOException e) {
            users = new ArrayList<>();
        }
    }

    private void saveUsers() {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            gson.toJson(users, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player getOrCreatePlayer(String username, int password) {
        for (Player p : users) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        Player newPlayer = new Player(username, password);
        users.add(newPlayer);
        saveUsers();
        return newPlayer;
    }

    public void updatePlayer(Player player) {
        saveUsers();
    }

    public List<Player> getLeaderboard() {
        users.sort((a, b) -> b.getWinRate() - a.getWinRate());
        return users;
    }
}