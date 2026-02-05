package activity3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NestedMenu {

    public static Scanner sc = new Scanner(System.in);
    public static boolean run  = true;
    public static int answer = 0;
    public static double[] compro2 = new double[3];
    public static double[] DSA = new double[3];
    public static double[] OOP = new double[3];
    public static void main(String[] args) {
        Menu();
    } // MAIN

    public static void Menu(){
        do {
        System.out.println("""
                MAIN MENU:
                [1] ENTER GRADES
                [2] DISPLAY GRADES
                [0] EXIT
                """);
        
        try {
            System.out.print("User>>");
            answer = sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("[INVALID] Try Again");
            System.out.print("User>>");
            answer = sc.nextInt();
            sc.nextLine();
        }
        
        switch (answer) {
            case 1:
                chooseSubject();
                break;
            case 2:
                DisplayGrades();
                break;
            case 0:
                System.out.println("Exiting the system...");
                run = false;
                break;
            default:
                System.out.println("Invalid Try Again");
                break;
        }
    } while (run);
    }

    public static void chooseSubject(){
        
        do {
        System.out.println("""
                ENTER GRADES FOR:
                [1] COMPRO2
                [2] DSA
                [3] OOP
                [0] BACK
                """);
        
        try {
            System.out.print("User>>");
            answer = sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("[INVALID] Try Again");
            System.out.print("User>>");
            answer = sc.nextInt();
            sc.nextLine();
        }
        
        switch (answer) {
            case 1:
                System.out.println("Enter Grades for COMPRO2:");
                for (int i = 0; i < compro2.length; i++) {
                    switch (i) {
                        case 0:
                            System.out.print("Prelims: ");
                            break;
                        case 1:
                            System.out.print("Midterms: ");
                            break;
                        case 2:
                            System.out.print("Finals: ");
                            break;
                            
                    }
                    compro2[i] = sc.nextDouble();
                    sc.nextLine();
                }
                System.out.println("Saving the grades...");
                break;
            case 2:
                System.out.println("Enter Grades for DSA:");
                for (int i = 0; i < DSA.length; i++) {
                    switch (i) {
                        case 0:
                            System.out.print("Prelims: ");
                            break;
                        case 1:
                            System.out.print("Midterms: ");
                            break;
                        case 2:
                            System.out.print("Finals: ");
                            break;
                            
                    }
                    DSA[i] = sc.nextDouble();
                    sc.nextLine();
                }
                System.out.println("Saving the grades...");
                break;
            case 3:
                System.out.println("Enter Grades for OOP:");
                for (int i = 0; i < OOP.length; i++) {
                    switch (i) {
                        case 0:
                            System.out.print("Prelims: ");
                            break;
                        case 1:
                            System.out.print("Midterms: ");
                            break;
                        case 2:
                            System.out.print("Finals: ");
                            break;
                            
                    }
                    OOP[i] = sc.nextDouble();
                    sc.nextLine();
                }
                System.out.println("Saving the grades...");
                break;
            case 0:
                System.out.println("Back to the Menu...");
                Menu();
                break;
            default:
                System.out.println("Invalid Try Again");
                break;
        }
    } while (run);
    } //Enter Grades

    public static void DisplayGrades(){
        while (true) {
            try {
        //for (int i = 0; i < 4; i++) {
            System.out.printf("""
                    GRADES | COMPRO | DSA | OOP
                    PRELIM: %6d | %4d | %4d
                    MIDTERM: %6d | %4d | %4d
                    FINALS: %6d | %4d | %4d
                    """, compro2[0], DSA[0], OOP[0],
                + compro2[1], DSA[1], OOP[1],
            + compro2[2], DSA[2], OOP[2] );
        //}
            } catch (Exception e) {
                System.out.println("Grades Are Missing.. (THIS FEATURE IS FOR THE NEXT ACTIVITY)");
                System.out.println("Going back to the Menu..");
                break;
            }
            }
    }

    
}
