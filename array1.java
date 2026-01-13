import java.util.Scanner;
    class array1 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i <= arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }

        System.out.print("\nEnter an number in the array: ");
        int chosen = sc.nextInt();

        for (int equal = 0; equal <= arr.length - 1; equal++){
            if (arr[equal] ==  chosen) {
                System.out.println(equal);
                break;
            }
        }

    }
}