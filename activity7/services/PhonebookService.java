package activity7.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import activity7.models.Contact;

public class PhonebookService {
    private HashMap<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact c) {
        contacts.put(c.getPhoneNumber(), c) ;
        System.out.println("[CONTACT ADDED]");
        

    }

    public void searchContact(String name){
        Contact c = contacts.get(name);
    if (c != null) {
        System.out.println("[CONTACT FOUND]");
        System.out.println(c);
    } else {
        System.out.println("[CONTACT NOT FOUND]");
    }

    }

    public void removeContact(String name) {
        contacts.remove(name);
        System.out.println("[CONTACT REMOVED]");

    }

    public void saveToCSV(String filename) {
        
        try (BufferedWriter BF = new BufferedWriter(new FileWriter(filename))) {
            for (Contact c : contacts.values()) {
                BF.write(c.toCsvString());
                BF.newLine();
            }
            System.out.println("[CONTACT SAVED]");
        } catch (IOException e) {
            System.out.println("[ERROR] IOException");
        }
        
    }

    public void loadFromCSV(String filename) {
        contacts.clear();
        String line;
        try (BufferedReader BR = new BufferedReader(new FileReader(filename))) {
            while ((line = BR.readLine()) != null) {
            String[] values = line.split(",");
            if (values.length == 3) {
                Contact c = new Contact();
                c.setName(values[0]);
                c.setPhoneNumber(values[1]);
                c.setEmail(values[2]);
                contacts.put(c.getName(), c);

                System.out.println(c);
            }
        }
        System.out.println("[LOADED]");

        } catch (IOException e) {
            System.out.println("[ERROR] IOException reading the CSV file");
        }
        
    }
}
