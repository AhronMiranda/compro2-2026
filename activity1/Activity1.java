

import java.util.Scanner;

public class Activity1 {
    public static void main(String[] args) {

        int[] theaterRow = new int[8];

        Scanner sc = new Scanner(System.in);
        System.out.print("Choose your seat: ");
        int chosen = sc.nextInt();
        theaterRow[chosen] = 1;

        int count = 0;
        for (int i = 0; i < theaterRow.length; i++) {
            if (theaterRow[i] == 1) {
                System.out.println("Seat " + i + " is Taken");
                count += 1;
            } else {
                System.out.println("Seat " + i + " Available");
            }
            
        }
        System.out.println("Number of Available seats: " + (theaterRow.length - count));
    }
}
