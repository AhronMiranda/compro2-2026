package week3;

import java.util.Scanner;

public class SumElementsByColumn {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";


    public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);

    double[][] m = new double[3][4];
    

    System.out.println(ANSI_GREEN + "Enter a 3-by-4 matrix row by row:" + ANSI_RESET);
    for (int row = 0; row < m.length; row++) {
        for (int clm = 0; clm < m[row].length; clm++) {
            m[row][clm] = sc.nextDouble();
        }
    }

    int columnInd = 0;
    for (columnInd = 0; columnInd <= m.length; columnInd++) {
    System.out.println("Sum of the elements at column " + columnInd + " is " + sumColumn(m, columnInd));
    }
    
    double[][] mD = new double[4][4];
    System.out.println(ANSI_GREEN + "Enter a 4-by-4 matrix row by row:" + ANSI_RESET);
    for (int row = 0; row < mD.length; row++) {
        for (int clm = 0; clm < mD[row].length; clm++) {
            mD[row][clm] = sc.nextDouble();
        }
    }

    System.out.println("Sum of the elements at the major diagonal is " + sumMajorDiagonal(mD));
        
    }

    public static double sumColumn(double[][] m, int columnIndex) {
        double sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += m[i][columnIndex];
        }
        return sum;
    }

    public static double sumMajorDiagonal(double[][] mD) {
        double sum = 0;
        for (int i = 0; i < mD.length; i++ ){
            sum += mD[i][i];
        }
        return sum;
    }
}
