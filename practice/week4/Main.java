package week4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        // try (FileWriter fw = new FileWriter("data.txt") ){ // SAVE FILE 
        //     //fw = new FileWriter("data.txt"); //SAVE FILE
        //     fw.write("Anything you want to save"); //CONTENT
        //     fw.flush();  //UPLOAD
        // } catch (IOException e) {
        //     System.out.println(e.getMessage());
        // } finally {
        //     // fw.close();
        // }
        

        StringBuilder sb = new StringBuilder();
        
        System.out.println("Enter something: ");
        try (Scanner sc = new Scanner(System.in)) {
            sb.append(sc.nextLine());
        } catch (InputMismatchException e) {
            System.out.println("invalid input");
        }
        
       try(FileWriter fw = new FileWriter("data.txt")){
            fw.write(sb.toString());
            System.out.println("Data is saved...");
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}