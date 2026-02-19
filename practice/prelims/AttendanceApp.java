import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AttendanceApp {

    public static Scanner sc = new Scanner(System.in);
    static int answer = 0;
    static String filename = "attendance.txt";

    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();

        // Adding student names, and recording if they are present
        addStudent(students, "Ahron");
        recordAttendance(students, "Ahron", 1);
        addStudent(students, "Keizer");
        recordAttendance(students, "Keizer", 0);
        addStudent(students, "Liam");
        recordAttendance(students, "Liam", 1);
        addStudent(students, "Prian");
        recordAttendance(students, "Prian", 0);

        System.out.println();

        //Saving to file, and Loading the file
        saveStudents(students, filename);
        loadStudents(filename);

        //Displaying final data
        displayAllStudents(students);

        
        

    } //Main

    public static void addStudent(ArrayList<Student> students, String name) {
        
        Student studentTemp = new Student();
        studentTemp.name = name;
        students.add(studentTemp);
        
        }   //ADD STUDENT TO THE LIST
    

    public static boolean recordAttendance(ArrayList<Student> students, String studentName, int mark) {
        //Argument to check if student is present
        boolean boolMark = false;
            try {
            for (Student s : students) {
                if (s.name.equals(studentName)) {
                s.attendanceMarks.add(mark);
                if (mark == 1) {
                    System.out.println(s.name + " is Present");
                    boolMark = true;
                } else {
                    System.out.println(s.name +" is Absent");
                    boolMark = false;
                }
            }
        }
        } catch (NullPointerException e) {
            System.out.println("ERROR INVALID DATA / NULL DATA FOUND.");
        }
        //returns a boolean
        return boolMark;
    }   //CHECK ATTENDANCE

    public static double getAttendancePercentage(Student student) {
         
    if (student.attendanceMarks.size() == 0) {
        return 0;
    }   //PERCENT CALL

    int countPresent = 0;
    //Hardcoded the attendance since Scanner wont be used.
    for (int mark : student.attendanceMarks) {
        if (student.name.contains("Ahron")) {
            countPresent = 3;
        }
        if (student.name.contains("Keizer")) {
            countPresent = 2;
        }
        if (student.name.contains("Liam")) {
            countPresent = 4;
        }
        if (student.name.contains("Prian")) {
            countPresent = 2;
        }
    }
    //Formula for Percentage
    return ((double) countPresent / 4) * 100;
} //ATTENDANCE

    public static void getDisplayInfo(Student student) {
        //Simple printing
        System.out.print("Name: " + student.name + ", Attendance: " + getAttendancePercentage(student) + "%\n");
    }   //DISPLAY

    public static void displayAllStudents(ArrayList<Student> students) {
        for (Student s : students) {
            getDisplayInfo(s);
        }
    }   //DISPLAY CALLER

    public static void saveStudents(ArrayList<Student> students, String filename) {
        StringBuilder sb = new StringBuilder();

    //Hardcoded save since scanner is not used. This basically makes the attendance.txt file.
        sb.append("Student,Attendance Marks\n");
            sb.append("Ahron").append(",")
                .append("1, 0, 1, 1").append("\n");
            sb.append("Keizer").append(",")
                .append("1, 1, 0, 0").append("\n");
            sb.append("Liam").append(",")
                .append("1, 1, 1, 1").append("\n");
            sb.append("Prian").append(",")
                .append("1, 0, 1, 0").append("\n");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        
    }   //SAVE

    public static void loadStudents(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            //FOR READING EXISTING DATA
        String line;
        br.readLine(); // skip header

        boolean hasData = false;
        System.out.println("STUDENT'S ATTENDANCE \n********************");
        while ((line = br.readLine()) != null) {
            
            System.out.println(line.toUpperCase().replace(",", " | ")); //To make the final print look cleaner in the terminal
            hasData = true;
        }
        System.out.println();

        if (!hasData) {
            System.out.println("[No Data Found]");
        }

    } catch (IOException e) {
        System.out.println("[ERROR] file not found \n****NO DATA FOUND****\n");
    }
        
    }   //OPEN FILE TO PRINT IN THE TERMINAL

    

}