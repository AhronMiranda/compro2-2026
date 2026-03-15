package com.gsonmenu.objects;

public class Grade {
    private String subject;
    private double prelims;
    private double midterms;
    private double finals;
    public static int subjectCount = 0;

    

    public Grade() {
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public double getPrelims() {
        return prelims;
    }
    public void setPrelims(double prelims) {
        this.prelims = prelims;
    }
    public double getMidterms() {
        return midterms;
    }
    public void setMidterms(double midterms) {
        this.midterms = midterms;
    }
    public double getFinals() {
        return finals;
    }
    public void setFinals(double finals) {
        this.finals = finals;
    }

    @Override
    public String toString(){
        return String.format("""
                ________________| Subject no.%d
                Subject: %s
                Prelims: %.1f
                Midterm: %.1f
                Final: %.1f
                """, subjectCount, subject, prelims, midterms, finals);
    }
}
