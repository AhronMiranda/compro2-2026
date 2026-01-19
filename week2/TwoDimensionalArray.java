public class TwoDimensionalArray {
    public static void main(String[] args) {
        String[][] clothColors = {
        {"Red", "Blue", "Green"},
        {"Orange", "Yellow", "Violet"}
        };

        System.out.println(clothColors[1][2]); //VIOLET
        System.out.println(clothColors[0][1]); //BLUE
        System.out.println(clothColors[0][0]); //RED

        System.out.println(clothColors[1][1]); //INDEX OF YELLOW
        System.out.println(clothColors[1][0]); //INDEX OF ORANGE

        System.out.println();

        // Mano-mano way
        // String[][] clothColors = new String[2][3];

        // clothColors[0][0] = "Red";
        // clothColors[0][1] = "Blue";
        // clothColors[0][2] = "Green";

        // clothColors[1][0] = "Orange";
        // clothColors[1][1] = "Yellow";
        // clothColors[1][2] = "Violet";


        //PRINT 2D Array
        for (int i = 0; i < clothColors.length; i++) {
            for (int j = 0; j < clothColors[i].length; j++){
                System.out.printf("%-8s ", clothColors[i][j]);
            }
            System.out.println(); 
        }

    } //MAIN

    public static void clothTypes () {
        String[] clothTypes = {"shirts", "pants"};
    }
    
}
