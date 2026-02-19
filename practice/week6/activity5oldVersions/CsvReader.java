package practice.week6.activity5oldVersions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import practice.week6.activity5oldVersions.CsvReader.Grades;

public class CsvReader {

    static List<Grades> grades = new ArrayList<>();

    public static void main(String[] args) {
        int answer = 0;

        Scanner sc = new Scanner(System.in);

        while (true) { //So it will loop until you choose 3
        
            printMenu();

        try {
            answer = sc.nextInt();
            sc.nextLine();

        } catch (InputMismatchException e) {
            System.out.println("[ERROR] Invalid number");
            sc.nextLine();
            System.out.print("User>> ");
            answer = sc.nextInt();
            sc.nextLine();
            
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

                    printMenu();
                    try {
                        answer = sc.nextInt();
                        sc.nextLine();
                        if (answer == 1) {
                            noSub++;
                            continue;
                        } else if (answer == 3) {
                            System.out.println("Exiting the terminal...");
                            System.exit(0);
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
                 try (BufferedReader br = new BufferedReader(new FileReader("Act5.csv"))) {
            //FOR READING EXISTING DATA
        String line;
        br.readLine(); // skip header

        boolean hasData = false;
        System.out.println("SUBJECT | PRELIMS | MIDTERM | FINALS \n********************");
        while ((line = br.readLine()) != null) {
            String[] arr = line.split(",");

            Grades g = new Grades();
            g.subject = arr[0];
            g.prelims = Double.parseDouble(arr[1]);
            g.midterm = Double.parseDouble(arr[2]);
            g.finals = Double.parseDouble(arr[3]);

            
            System.out.println(g.subject + " | Prelim: " + g.prelims + " | Midterm: " + g.midterm + " | Finals: " + g.finals);
            hasData = true;
        }

        if (!hasData) {
            System.out.println("[No grades found]");
        }

    } catch (IOException e) {
        System.out.println("[ERROR] CSV file not found \n****ADD GRADES FIRST****\n");
    }
    System.out.println();
    break;
            case 3:
                System.out.println("Exiting the terminal...");
                System.exit(0);
        }
        
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

        System.out.println(sb.toString().replace(",", " | ")); //To make the final print look cleaner in the terminal

    }// write

    public static void printMenu() {
        System.out.println("""
                [1] Add Grade
                [2] Display Grades
                [3] Exit
                """);

        System.out.print("User>> ");
    }

    static class Grades {
        String subject;
        double prelims;
        double midterm;
        double finals;

    }
}