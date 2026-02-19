

import java.util.InputMismatchException;
import java.util.Scanner;

public class TheaterSeating2D {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static void main(String[] args) {
        // 1. Declare and initialize the 2D array for the theater
        int[][] theater = new int[5][8]; // 5 rows, 8 columns

        // 2. Book the seat at row 2, column 5
        // TODO: Your code here
        theater[2][5] = 1;

        // 3. Book the seat at row 0, column 0
        // TODO: Your code here
        theater[0][0] = 1;

        System.out.println("Theater Seating Chart (0=Available, 1=Booked):\n");
        // 4. Use nested loops to print the seating chart
        // The outer loop should iterate through rows
        // The inner loop should iterate through columns
        // TODO: Your code here

        int count = 0;
        int totalSeats = 0;
        

        for (int i = 0; i < theater.length; i++) {
            for(int j = 0; j < theater[i].length; j++) {

            if (theater[i][j] == 0) {
                System.out.print( GREEN + "|-| " + RESET);
                totalSeats++;
            } else {
                System.out.print( RED + "|x| " + RESET);
                totalSeats++;
                count++;
            }
            }
            System.out.println();
        }

        // 5. Count and print the total number of booked seats
        // TODO: Your code here
        System.out.println("\nThe number of booked seats are " + count + " out of " + totalSeats + " seats");
        System.out.println("Total seats available: " + (totalSeats - count));


        // BOOK A SEAT 
        Scanner sc = new Scanner(System.in);
        while (true) {
        
        try {
        System.out.println("BOOK A SEAT??");
        System.out.print("Y/N:");
        

        String answ = sc.nextLine();
        char answer = answ.charAt(0);

        if (answer == 'n' || answer == 'N') {
            System.out.println(RED + "[SYSTEM EXIT]" + RESET);
            break;
        }
        
        while (answer == 'y' || answer == 'Y') {
            System.out.print("Enter Row:");
            int a = sc.nextInt();
            System.out.print("Enter Column:");
            int b = sc.nextInt();
            sc.nextLine();
            count = 0;

            theater[a][b] = 1;

            for (int i = 0; i < theater.length; i++) {
            for(int j = 0; j < theater[i].length; j++) {

            if (theater[i][j] == 0) {
                System.out.print(  GREEN + "[-] " + RESET);
            } else {
                System.out.print( RED + "[x] " + RESET);
                count++;
            }
            }
            System.out.println();
        }

        System.out.println("\nThe number of booked seats are " + count + " out of " + totalSeats + " seats");
        System.out.println("Total seats available: " + (totalSeats - count));
        break;
        }
        } catch (InputMismatchException e) {
        System.out.print("[ERROR RESTART?] Y/N:");
        String answ = sc.nextLine();
        continue;
        } catch (IndexOutOfBoundsException e) {
        continue;
        }
        }
        
        
    } //MAIN
}
