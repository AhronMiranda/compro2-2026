package com.Main;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;


public class app {
    public static void main(String[] args) throws FileNotFoundException {

        //Json File Displayed to Main

        String json = "";
        
            Scanner sc = new Scanner(new File("data/person.json"));
            while(sc.hasNextLine()){
                json += sc.nextLine();
            }
            Gson gson = new Gson();
            Person p = gson.fromJson(json, Person.class);

            p.setFirstName("Kent");
            p.setAge(19);

            System.out.println("Hello!\n");
            System.out.println(p.toString());

        //WRITTEN TO FILE
        System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");

        Person person = new Person("Ahron", "Miranda", 19, "ahronjieannekent.miranda@lorma.edu", "+639672169260",
        "2006-09-05", "Sevilla, SFC, LU", false, "Filipino", "Male");
        Gson jesion = new Gson();

        try(FileWriter fw = new FileWriter("data/newPersonSave.json", true)) {
            jesion.toJson(person, fw);
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] FileNotFoundException");
        } catch (IOException e) {
            System.out.println("[ERROR] IOException");
        }

        System.out.println(person.toString());


    }
}
