package com.grademultithread;

public class Subject {
    private String name;
    private double prelim;
    private double midterm;
    private double finals;
    
    public Subject() {}
    

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrelim() {
        return prelim;
    }

    public void setPrelim(double prelim) {
        this.prelim = prelim;
    }

    public double getMidterm() {
        return midterm;
    }

    public void setMidterm(double midterm) {
        this.midterm = midterm;
    }

    public double getFinals() {
        return finals;
    }

    public void setFinals(double finals) {
        this.finals = finals;
    }

   
}
