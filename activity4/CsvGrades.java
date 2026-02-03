package activity4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CsvGrades {
    static String[] subjects;
    static double[][] grades;

    public static void main(String[] args) {
        subjects = new String[50];
        grades = new double[50][3];
        int answer = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("""
                [1] Add Grade
                [2] Exit
                """);

        System.out.print("User>> ");

            try {
                answer = sc.nextInt();
                sc.nextLine();
       
            } catch (InputMismatchException e) {
                System.out.println("Invalid number");
            } 

        switch (answer) {

        case 1 :

        
        int noSub = 1;
        
        

        for (int r = 0; r < noSub; r++) {
            System.out.print("Enter Subject Name: ");
            subjects[r] = sc.nextLine();

            System.out.print("Prelim Grade: ");
            try {
                grades[r][0] = sc.nextDouble();
                
            } catch (InputMismatchException e) {
                System.out.println("Invalid number");
            }

            System.out.print("Midterm Grade: ");
            try {
                grades[r][1] = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid number");
            }

            System.out.print("Finals: ");
            try {
                grades[r][2] = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid number");
            }

            sc.nextLine();

            System.out.print("""
                [1] Add Grade
                [2] Exit
                """);
            System.out.print("User>> ");
            try {
                answer = sc.nextInt();
                sc.nextLine();
                if (answer == 1) {
                    noSub++;
                    continue;
                } else {
                    System.out.println("[Grades registered]");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid number");
            }

            
            System.out.println();
        }
        writeData();
        break;

    case 2:
        System.out.println("Exiting the terminal...");
    }

    }

    public static void writeData() {
        StringBuilder sb = new StringBuilder();

        sb.append("Subject,Prelim,Midterm,Finals\n");
        for (int r = 0; r < subjects.length; r++) {
            if(subjects[r] == null) 
                break;

            sb.append(subjects[r]);
            for (int c = 0; c < grades[r].length; c++) {
                sb.append(",").append(grades[r][c]);
            }
            sb.append("\n");
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data.csv"))){
            bw.write(sb.toString());
            bw.flush();
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(sb.toString());
    
    }
}