package Sorting;

public class insertionSort {

    // O(n) - best case running time
    // O(n^2) - average case running time-
    // O(n^2) - worst case running time

    public static void main(String args[]) {
        int arr[] = {12, 11, 13, 5, 6};

        insertionSort ob = new insertionSort();
        ob.insertionSort(arr);

        printArray(arr);
    }

    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    public static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int elem= a[i];
            int hole = i;
            while (hole > 0 && a[hole - 1] > elem) {
                a[hole] = a[hole - 1];
                hole =hole - 1;
            }
            a[hole] = elem;
        }
    }
}
