class main array1 {
    public static void main(String args[]) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i <= arr.length - 1; i++) {
            System.out.println(arr[i]);
        }
        Scanner sc = new Scanner(System.in);
        int chosen = 0;

        System.out.print("Enter an index in the array 0-9: ");
        while (sc <= 9 && sc >= 0) {
            chosen = sc.nextInt;
        }

        for (int equal = 0; equal <= arr.length - 1; equal++){
            if (arr[equal] =  chosen) {
                System.out.println(equal);
                break;
            }
        }

    }
}