package Sorting;

public class bubblesort { //average/worst O(n^2),  Best case O(n)

    public static void main(String[] args) {

        bubblesort s = new bubblesort();
        int[] arr = new int[] {5, 3, 8, 9, 2, 1, 7, 10};
        s.bubbleSort(arr);
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    public void bubbleSort(int[] arr) {
        boolean swapped = true;
        while(swapped) {
            swapped = false;
            for(int i = 0; i < arr.length - 1; i++) {
                if(arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
        }
    }

}
