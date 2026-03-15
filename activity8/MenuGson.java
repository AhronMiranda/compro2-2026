package com.gsonmenu;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gsonmenu.objects.Grade;

public class MenuGson {

    static Scanner sc = new Scanner(System.in);
    static List<Grade> list = new ArrayList<>();

    static String FILE_PATH = "data/Grades.json";

    public static void main(String[] args) throws IOException {

        loadGrades();

        while (true) {

            int answer = menu();

            switch (answer) {

                case 1:
                    addGrade();
                    saveGrades();
                    break;

                case 2:
                    displayGrades();
                    break;

                case 0:
                    System.out.println("Exiting the terminal...");
                    System.exit(0);
            }
        }
    } //END MAIN

    // MENU
    public static int menu() {

        int answer = -1;

        System.out.println("""
                [1] Add Grade
                [2] Display Grades
                [0] Exit
                """);

        System.out.print("User>> ");

        try {
            answer = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid number");
            sc.nextLine();
        }

        return answer;
    }

    // ADD GRADE
    public static void addGrade() {

        Grade temp = new Grade();

        System.out.print("Enter Subject: ");
        temp.setSubject(inpString());

        System.out.print("Enter Prelim Grade: ");
        temp.setPrelims(inpDouble());

        System.out.print("Enter Midterm Grade: ");
        temp.setMidterms(inpDouble());

        System.out.print("Enter Finals Grade: ");
        temp.setFinals(inpDouble());

        list.add(temp);

        System.out.println("[GRADES SAVED]");
    } //END GRADES

    // DISPLAY GRADES
    public static void displayGrades() {
        Grade.subjectCount = 0;

        if (list.isEmpty()) {
            System.out.println("No grades found.");
            return;
        }

        for (Grade g : list) {
            Grade.subjectCount++;
            System.out.println(g);
        }
    } //DISPLAY

    // LOAD JSON
    public static void loadGrades() {

        try {

            File file = new File(FILE_PATH);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return;
            }

            Gson gson = new Gson();

            Type type = new TypeToken<List<Grade>>() {}.getType();

            FileReader reader = new FileReader(file);

            List<Grade> data = gson.fromJson(reader, type);

            if (data != null) {
                list = data;
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error loading grades.");
        }
    } //OPEN EXISTING JSON 

    // SAVE JSON
    public static void saveGrades() {

        try {

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            FileWriter writer = new FileWriter(FILE_PATH);

            gson.toJson(list, writer);

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving grades.");
        }
    } //SAVE

    
    public static String inpString() {
        return sc.nextLine();
    }

    
    public static double inpDouble() {

        double temp;

        while (true) {
            try {

                temp = sc.nextDouble();
                sc.nextLine();
                break;

            } catch (InputMismatchException e) {

                System.out.println("INVALID INPUT. Enter a number:");
                sc.nextLine();
            }
        }

        return temp;
    }
}