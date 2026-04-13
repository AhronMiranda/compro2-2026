package com.grademultithread;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GradeDataSaver extends Thread {

    private static final String FILE_NAME = "Grades.json";

    private static final Queue<Subject> queue = new LinkedList<>();

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Add subject to queue
    public static void queueSave(Subject s) {
        queue.add(s);
    }

    private List<Subject> loadSubjects() {
        try (FileReader fr = new FileReader(FILE_NAME)) {
            Type type = new TypeToken<ArrayList<Subject>>() {}.getType();
            List<Subject> list = gson.fromJson(fr, type);
            return (list != null) ? list : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveSubjects(List<Subject> subjects) {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            gson.toJson(subjects, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Saver thread running...");

        while (true) {

            if (!queue.isEmpty()) {
                Subject s = queue.poll();

                List<Subject> subjects = loadSubjects();
                subjects.add(s);
                saveSubjects(subjects);

                System.out.println("Data Saved: " + s.getName());
            }

            try {
                Thread.sleep(1000); // check every second
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}