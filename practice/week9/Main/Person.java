package com.Main;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private String emailAddress;
    private String phoneNumber;
    private String dateOfBirth;
    private String homeAddress;
    private boolean isEmployed;
    private String nationality;
    private String gender;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getHomeAddress() {
        return homeAddress;
    }
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
    public boolean isEmployed() {
        return isEmployed;
    }
    public void setEmployed(boolean isEmployed) {
        this.isEmployed = isEmployed;
    }
    public String isNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String isGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String employment(){
        if (isEmployed()) {
            return "Employed";
        } else {
            return "Unemployed";
        }
    }

    public Person( String firstName,
     String lastName,
     int age,
     String emailAddress,
     String phoneNumber,
     String dateOfBirth,
     String homeAddress,
     boolean isEmployed,
     String nationality,
     String gender) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setEmailAddress(emailAddress);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);
        setHomeAddress(homeAddress);
        setEmployed(isEmployed);
        setNationality(nationality);
        setGender(gender);
     }

    @Override
    public String toString() {
        return firstName + " " + lastName + " | Age: " + age + " \n" + emailAddress + " | " + phoneNumber
        + " \n" + dateOfBirth +  "\n" + homeAddress + "\n" + employment() + "\n" + nationality + " | " + gender;
    }
}
