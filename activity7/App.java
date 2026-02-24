package com.phonebook;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("1. Add | 2. Search | 3. Remove | 4. Display All | 5. Save to CSV | 0. Exit");
        while (true) {
            try {
        System.out.print("user>>");
        int answer = sc.nextInt(); 
        
        switch (answer) {
            case 0:
                System.exit(0);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    } catch (InputMismatchException e) {
        sc.nextLine();
        System.out.println("[ERROR] INVALID");
    }
    }

    }
}