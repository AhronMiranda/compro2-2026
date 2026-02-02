package week4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Practice {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        
        
        try(Scanner sc = new Scanner(System.in)){
            System.out.println("Enter First Name: ");
            sb.append("First Name: " + sc.nextLine() + "\n");
            
            System.out.println("Enter Last Name: ");
            sb.append("Last Name: " + sc.nextLine() + "\n");
            
            System.out.println("Enter age: ");
            sb.append("age: " + sc.nextInt() + "\n"); sc.nextLine();
            
            System.out.println("Enter Email: ");
            sb.append("Email: " + sc.nextLine() + "\n");
            
            System.out.println("Enter Phone Number: ");
            sb.append("Phone Number: " + sc.nextInt() + "\n"); 
            System.out.println();
        }catch(InputMismatchException e){
            System.out.println("Invalid input");
        }


        //try-with-resource 
        try(FileWriter fw = new FileWriter("data.txt")){
            fw.write(sb.toString());
            System.out.println("Data is saved...");
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }
}
