package activity5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CsvReader {

    static List<String> subjects = new ArrayList<>();
    static List<Double> grades = new ArrayList<>();

    public static void main(String[] args) {
        int answer = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("""
                [1] Add Grade
                [2] Display Grades
                [3] Exit
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
            subjects.add(sc.nextLine());

            System.out.print("Prelim Grade: ");
            try {
                grades.add(sc.nextDouble());
                
            } catch (InputMismatchException e) {
                System.out.println("Invalid number");
            }

            System.out.print("Midterm Grade: ");
            try {
                grades.add(sc.nextDouble());
            } catch (InputMismatchException e) {
                System.out.println("Invalid number");
            }

            System.out.print("Finals: ");
            try {
                grades.add(sc.nextDouble());
            } catch (InputMismatchException e) {
                System.out.println("Invalid number");
            }

            sc.nextLine();

            System.out.print("""
                [1] Add Grade
                [2] Display Grades
                [3] Exit
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
        System.out.println("Displayed the Grades");
        break;
    case 3:
        System.out.println("Exiting the terminal...");
    }

    }

    public static void writeData() {
        StringBuilder sb = new StringBuilder();

        sb.append("Subject,Prelim,Midterm,Finals\n");

        
        for (String subject : subjects) {
            sb.append(subject + " | ");
            for (Double grade : grades) {
                sb.append(grade.toString() + ",");
            }
            sb.append("\n");
        }


            
            
        

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("Act5.csv"))){
            bw.write(sb.toString());
            bw.flush();
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(sb.toString());
  
    }// write

}
