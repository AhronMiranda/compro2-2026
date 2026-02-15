package activity6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SearchOption {

    static List<Grades> grades = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int answer = 0;

    public static void main(String[] args) {

        while (true) { //So it will loop until you choose 3
        
            printMenu();

        try {
            answer = sc.nextInt();
            sc.nextLine();

        } catch (InputMismatchException e) {
            System.out.println("[ERROR] Invalid Input");
            sc.nextLine();
            System.out.print("User>> ");
            answer = sc.nextInt();
            sc.nextLine();
        }

        switch (answer) {

            case 1:
                addSubjectGrades();
                writeData();
                break;
            case 2:
                BufferedReaderDisplay();
                break;
            case 3:
                System.out.print("Search: ");
                BufferedReaderSearch();
                System.out.println();
                break;
            case 0:
                System.out.println("Exiting the terminal...");
                System.exit(0);
        }
        
    }
    } // MAIN

    public static void printMenu() {
        System.out.println("""
                [1] Add Grade
                [2] Display Grades
                [3] Search
                [0] Exit
                """);

        System.out.print("User>> ");
    }// Menu

    public static void writeData() {
        StringBuilder sb = new StringBuilder();

        sb.append("Subject,Prelim,Midterm,Finals\n");
        for (Grades g : grades) {
            sb.append(g.subject).append(",")
                    .append(g.prelims).append(",")
                    .append(g.midterm).append(",")
                    .append(g.finals).append("\n");

        } 

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Act6.csv"))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(sb.toString().toUpperCase().replace(",", " | ")); //To make the final print look cleaner in the terminal

    }// write

    public static void addSubjectGrades() {
        
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
    }// Add Subject

    public static void BufferedReaderDisplay() {
        try (BufferedReader br = new BufferedReader(new FileReader("Act6.csv"))) {
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
    }// Reader | Display 

    public static void BufferedReaderSearch() {
        grades.clear();

        try (BufferedReader br = new BufferedReader(new FileReader("Act6.csv"))) {
        String line;
        br.readLine(); // skip header

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
        System.out.println("[ERROR] CSV file not found \n****ADD GRADES FIRST****\n");
        return;
    }

        String searchString = sc.nextLine();

        boolean hasData = false;
        
            Grades g = new Grades();
            List<Grades> filteredGrades = grades.stream().filter((f) ->
            f.subject.toLowerCase().contains(searchString.toLowerCase())
                || f.prelims == tryParseDouble(searchString)
                || f.midterm == tryParseDouble(searchString)
                || f.finals == tryParseDouble(searchString)
            ).toList();

            

            for(Grades f : filteredGrades) {
                hasData = true;
                System.out.printf("%s | %.2f | %.2f | %.2f", f.subject, f.prelims, f.midterm, f.finals);
            }
        if (!hasData) {
            System.out.println("[No Data Found]");
        }
    System.out.println();
    
    }// Reader | Search

    static class Grades {
        String subject;
        double prelims;
        double midterm;
        double finals;

    }

    static double tryParseDouble(String s) {
    try {
        return Double.parseDouble(s);
    } catch (NumberFormatException e) {
        return Double.NaN;
    }
}
}
