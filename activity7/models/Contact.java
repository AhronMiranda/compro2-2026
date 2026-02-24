package com.phonebook.models;

public class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(){ }

    public String setName(String name) {
        return this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public String setPhoneNumber(String phoneNumber) {
        return this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String setEmail(String email) {
        return this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    public String toCsvString() {
        String finalStr = getName() + "," + getPhoneNumber() + "," + getEmail();
        return finalStr;
    }
}
