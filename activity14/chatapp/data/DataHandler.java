package com.chatapp.data;

import com.chatapp.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {

    private static final String FILE = "users.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public synchronized static void saveUser(User user) {
        List<User> users = loadUsers();
        users.add(user);

        try (Writer writer = new FileWriter(FILE)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static List<User> loadUsers() {
        try (Reader reader = new FileReader(FILE)) {
            Type type = new TypeToken<List<User>>() {
            }.getType();
            List<User> users = gson.fromJson(reader, type);
            return users != null ? users : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static boolean authenticate(String username, String password) {
        return loadUsers().stream()
                .anyMatch(u -> u.getUsername().equals(username)
                        && u.getPassword().equals(password));
    }

    public static boolean userExists(String username) {
        return loadUsers().stream()
                .anyMatch(u -> u.getUsername().equals(username));
    }
}