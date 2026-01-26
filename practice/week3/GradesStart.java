package week3;

import java.util.Scanner;

public class GradesStart {

// Reset
public static final String RESET = "\033[0m";

// Regular Colors
public static final String RED = "\033[0;31m";
public static final String GREEN = "\033[0;32m";
public static final String BLUE = "\033[0;34m";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(BLUE + """
                WELCOME TO THE GRADE CALCULATOR
                ==============================
                """ + RESET);
        System.out.print(RED + "Enter your username: " + RESET);

        String username = sc.nextLine();

        String[] arrSubjects = {"COMPRO", "CFVE", "OOP"};
        double[][] grades = new double[3][3];
        
        System.out.println(GREEN + "Enter PRELIM> MIDTERM> FINALS per row" + RESET);
    for (int row = 0; row < grades.length; row++) {
        for (int clm = 0; clm < grades[row].length; clm++) {
            grades[row][clm] = sc.nextDouble();
        }
    }
    }

    public static double gradeAverage(double[][] grades) {
        double average = 0;
         for (int row = 0; row < grades.length; row++) {
        for (int i = 0; i < grades[row].length; i++) {
            average += grades[row][i];
        }
        }
        return average;
    }
}
