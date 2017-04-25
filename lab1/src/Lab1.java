public class Lab1 {
    /** Sorting algorithms **/

    // Insertion sort.

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; ++i) {
            int elem = array[i];
            int j = i;
            while (j > 0 && elem < array[j-1]) {
                array[j] = array[j-1];
                --j;
            }
            array[j] = elem;
        }
    }

    // Quicksort.

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length);
    }

    // Quicksort part of an array
    private static void quickSort(int[] array, int begin, int end) {
        if (end-begin <= 1) {
            return;
        }

        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot);
        quickSort(array, pivot+1, end);
    }

    // Partition part of an array, and return the index where the pivot
    // ended up.
    private static int partition(int[] array, int begin, int end) {
        int pivot = array[begin];
        int i = begin, j = end;
        while (true) {
            while (true) {
                ++i;
                if (i >= end || array[i] >= pivot)
                    break;
            }
            while (true) {
                --j;
                if (j <= begin || array[j] <= pivot)
                    break;
            }

            if (i >= j)
                break;

            swap(array, i, j);
        }
        swap(array, begin, j);  // Put pivot element into array[j]
        return j;
    }

    // Swap two elements in an array
    private static void swap(int[] array, int i, int j) {
        int x = array[i];
        array[i] = array[j];
        array[j] = x;
    }

    // Mergesort.

    public static int[] mergeSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        return mergeSort(array, 0, array.length);
    }

    // Mergesort part of an array
    private static int[] mergeSort(int[] array, int begin, int end) {
        if (end-begin <= 1) {
            return new int[]{array[begin]};
        }

        int mid = begin + (end-begin)/2;
        int[] left  = mergeSort(array, begin, mid);
        int[] right = mergeSort(array, mid, end);
        return merge(left, right);
    }

    // Merge two sorted arrays into one
    private static int[] merge(int[] left, int[] right) {
        int n = left.length + right.length;
        int[] array = new int[n];

        int i = 0, j = 0;
        for (int k = i; k < n; ++k) {
            if (i >= left.length) {
                array[k] = right[j++];
            } else if (j >= right.length) {
                array[k] = left[i++];
            } else if (left[i] <= right[j]) {
                array[k] = left[i++];
            } else {
                array[k] = right[j++];
            }
        }

        return array;
    }
}