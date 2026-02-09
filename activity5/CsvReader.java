package activity5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CsvReader {

    static List<Grades> grades = new ArrayList<>();

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

            case 1:

                int noSub = 1;

                for (int r = 0; r < noSub; r++) {
                    Grades a = new Grades(); //Temporary class that will rewrite and be added to the Array List per Rotations
                    System.out.print("Enter Subject Name: ");
                        a.subject = sc.nextLine();

                    System.out.print("Prelim Grade: ");
                    try {
                        a.prelims = sc.nextDouble();

                    } catch (InputMismatchException e) {
                        System.out.println("Invalid number");
                    }

                    System.out.print("Midterm Grade: ");
                    try {
                        a.midterm = sc.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid number");
                    }

                    System.out.print("Finals: ");
                    try {
                        a.finals = sc.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid number");
                    }

                    grades.add(a);
                    sc.nextLine();

                    System.out.println("""
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
                
                // OPTION 1
                // for (Grades g : grades) {
                // System.out.println(g.subject + " | Prelim: " + g.prelims +
                // " | Midterm: " + g.midterm +
                // " | Finals: " + g.finals);
                // }

                // OPTION 2 BUFFERED READER 
                try (BufferedReader br = new BufferedReader(new FileReader("Act5.csv"))) {
                     // READS CREATED CSV OR PREVIOUS CSV SAVE
                   
                    String line;
                    br.readLine();
                    int i = 0;
                    while ((line = br.readLine()) != null) {
                        String[] arr = line.split(",");
                        Grades g = new Grades();
                        g.subject = arr[0];
                        g.prelims = Double.parseDouble(arr[1]);
                        g.midterm = Double.parseDouble(arr[2]);
                        g.finals = Double.parseDouble(arr[3]);
                        grades.add(g);
                        

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println("[ERROR] No data to interpret \n ****ADD GRADES FIRST**** ");
                } 

                break;
            case 3:
                System.out.println("Exiting the terminal...");
                break;
        }
    
    }

    public static void writeData() {
        StringBuilder sb = new StringBuilder();

        sb.append("Subject,Prelim,Midterm,Finals\n");
        for (Grades g : grades) {
            sb.append(g.subject).append(",")
                    .append(g.prelims).append(",")
                    .append(g.midterm).append(",")
                    .append(g.finals).append("\n");

        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Act5.csv"))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(sb.toString());

    }// write

    static class Grades {
        String subject;
        double prelims;
        double midterm;
        double finals;

    }
}
