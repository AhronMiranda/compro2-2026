package week4;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Writer {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter first name: ");
            sb.append("First Name: ");
            sb.append(sc.nextLine());
            sb.append("\n");

        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }

        try(FileWriter fw = new FileWriter("file.txt")) {
          fw.write(sb.toString());
            System.out.println("Data is saved...");
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        try(FileReader fr = new FileReader("data.txt")) {
            int i;
            while((i = fr.read()) != -1) {
                System.out.println((char)-1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
