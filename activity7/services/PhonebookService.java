package com.phonebook.services;

import java.util.HashMap;

import com.phonebook.models.Contact;

public class PhonebookService {
    private HashMap<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact c) {
        contacts.put(c.getName(), c) ;
    }

    public static void searchContact(String name){

    }

    public static void removeContact(String name) {

    }

    public static void saveToCSV(String filename) {

    }
}
