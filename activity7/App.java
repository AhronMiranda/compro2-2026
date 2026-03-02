package activity7;

import java.util.InputMismatchException;
import java.util.Scanner;

import activity7.models.Contact;
import activity7.services.PhonebookService;

public class App {
    public static Scanner sc = new Scanner(System.in);
    public static String filename = "contacts.csv";
    public static void main(String[] args) {
        PhonebookService PS = new PhonebookService();
        Contact c = new Contact();
        menu(PS, c);
    }   //MAIN

    public static void menu(PhonebookService PS, Contact c) {
            menu();
        while (true) {
            try {
        System.out.print("user>>");
        int answer = sc.nextInt(); sc.nextLine();
        
        switch (answer) {
            case 0:
                System.exit(0);
                break;
            case 1:
                setContact(c);
                System.out.println(c.toCsvString());
                PS.addContact(c);
                PS.saveToCSV(filename);
                menu();
                break;
            case 2:
                String strSearch = "";
                try {
                    System.out.print("Input Contact Number>>");
                    strSearch = sc.nextLine();
                    PS.searchContact(strSearch);
                    menu();
                } catch (InputMismatchException e) {
                System.out.println("[ERROR] INVALID INPUT");
            }
                //PS.searchContact(strSearch);
                break;
            case 3:
                String strRemove = "";
                try {
                    System.out.print("Input Contact Number>");
                    strRemove = sc.nextLine();
                    PS.removeContact(strRemove);
                    
                    System.out.println("1. Add | 2. Search | 3. Remove | 4. Display All | 5. Save to CSV | 0. Exit");
                } catch (InputMismatchException e) {
                System.out.println("[ERROR] INVALID INPUT");
            }

                //PS.removeContact(strRemove);
                break;
            case 4:
                PS.loadFromCSV(filename);
                System.out.println("1. Add | 2. Search | 3. Remove | 4. Display All | 5. Save to CSV | 0. Exit");
                break;
            case 5:
                PS.saveToCSV(filename);
                System.out.println("1. Add | 2. Search | 3. Remove | 4. Display All | 5. Save to CSV | 0. Exit");
                break;
        }
    } catch (InputMismatchException e) {
        sc.nextLine();
        System.out.println("[ERROR] INVALID");
    }
    }
    }   // MENU

    public static void setContact(Contact c) {
        while (true) {
            try {
        System.out.print("Input Contact Name>>");
        c.setName(sc.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("[ERROR] INVALID INPUT");
            }

            try {
        System.out.print("Input Contact Phone Number>>");
        c.setPhoneNumber(sc.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("[ERROR] INVALID INPUT");
            }

            try {
        System.out.print("Input Contact Email>>");
        c.setEmail(sc.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("[ERROR] INVALID INPUT");
            }

            break; //EXIT THE SETTING OF CONTACT
        }
    }   //SET CONTACT

    public static void menu(){
        System.out.println("1. Add | 2. Search | 3. Remove | 4. Display All | 5. Save to CSV | 0. Exit");
    }
}