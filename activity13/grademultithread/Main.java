package com.grademultithread;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        GradeDataSaver saverThread = new GradeDataSaver();
        GradeDataUpdater updaterThread = new GradeDataUpdater();

        saverThread.start();
        updaterThread.start();

        try {
        Thread.sleep(500); //WAITS HALF A SECOND SO THE MESSAGE CAN PRINT 
        } catch (InterruptedException e) {}
        
        while (true) {

            menu();


            int answer = intInput();

            switch (answer) {

                case 1:
                    addSubjectGrades();
                    break;

                case 2:
                    updaterThread.displaySubjects();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }

    public static void menu() {
        
        
        System.out.println("""
                +--------------------------+
                |           MENU           |
                +--------------------------+

                    [1] Enter Grade
                    [2] Display Grades
                    [0] Exit

                """);
            System.out.print("user>> ");
              
    }

    public static void addSubjectGrades() {

        Subject a = new Subject();

        System.out.print("Enter Subject Name: ");
        a.setName(strInput());

        System.out.print("Prelim Grade: ");
        a.setPrelim(dobInput());

        System.out.print("Midterm Grade: ");
        a.setMidterm(dobInput());

        System.out.print("Finals: ");
        a.setFinals(dobInput());

        // Tells the saver thread to update it 
        GradeDataSaver.queueSave(a);

        System.out.println("[Queued for Saving]");
    }

    // INPUT METHODS

    public static String strInput() {
        while (true) {

            try {
                return sc.nextLine();
            } catch (Exception e) {
                System.out.println("[INVALID INPUT]");
            }
        }
    }

    public static double dobInput() {
        while (true) {

            try {
                double temp = sc.nextDouble();
                sc.nextLine();
                return temp;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("[INVALID INPUT]");
            }
        }
    }

    public static int intInput() {
        while (true) {
            try {
                int temp = sc.nextInt();
                sc.nextLine();
                return temp;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("[INVALID INPUT]");
            }
        }
    }
}