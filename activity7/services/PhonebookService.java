package com.phonebook.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.phonebook.models.Contact;

public class PhonebookService {
    private HashMap<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact c) {
        contacts.put(c.getName(), c) ;
    }

    public void searchContact(String name){
        System.out.println(contacts.get(name));
    }

    public void removeContact(String name) {
        contacts.remove(name);

    }

    public void saveToCSV(String filename) {
        try (BufferedWriter BF = new BufferedWriter(new FileWriter(filename))) {
            BF.write(contacts.toString());
            BF.close();
        } catch (IOException e) {
            System.out.println("[ERROR] IOException");
        }
        
    }
}
