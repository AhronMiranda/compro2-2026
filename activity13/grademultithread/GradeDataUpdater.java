package com.grademultithread;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GradeDataUpdater extends Thread {

    private static final String FILE_NAME = "Grades.json";

    private List<Subject> subjects = new ArrayList<>();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private void loadSubjects() {
        try (FileReader fr = new FileReader(FILE_NAME)) {
            Type type = new TypeToken<ArrayList<Subject>>() {}.getType();
            subjects = gson.fromJson(fr, type);
            if (subjects == null) subjects = new ArrayList<>();
        } catch (IOException e) {
            subjects = new ArrayList<>();
        }
    }

    public void displaySubjects() {

        if (subjects.isEmpty()) {
            System.out.println("No grades found.");
            return;
        }

        System.out.println("""
                +--------------------------+
                |       SUBJECT DATA       |
                +--------------------------+
                """);

        int i = 1;
        for (Subject s : subjects) {
            System.out.println("Subject #" + i++);
            System.out.println("Name: " + s.getName());
            System.out.println("Prelim: " + s.getPrelim());
            System.out.println("Midterm: " + s.getMidterm());
            System.out.println("Finals: " + s.getFinals());
            System.out.println();
        }
    }

    @Override
    public void run() {
        System.out.println("Updater thread running...");

        while (true) {
            loadSubjects();

            try {
                Thread.sleep(5000); // refresh every 5 sec
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}