package week3;

import java.util.Scanner;


public class ExceptionPractice1 {

    // Reset
    public static final String RESET = "\033[0m";
    
    // Regular Colors
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String BLUE = "\033[0;34m";
    public static final String BOLD = "\u001B[1m";
    public static final String ITALIC = "\u001B[3m";

    //SCANNER
    public static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        
        System.out.print("ENTER NUMBER: ");
        
        System.out.print("Your number is " + input());
    }
    
    public static int input() {
        int userNumber;

        while (true) {
        try  {
            userNumber = sc.nextInt();
            break;
            
        } catch (Exception e) {
            System.out.println(RED + BOLD + "[ERROR] " + e.getMessage() + RESET);
            System.out.print(BLUE + BOLD + "[TRY AGAIN]" + RESET + " ENTER NUMBER: ");
            sc.nextLine();
            
        } 
    } 
    return userNumber;

    }


}
