
public class myownLab1 {

//----------------------------------------------------------------------------------------------------
    //InsertionSort
    // O(n) - best case running time
    // O(n^2) - average case running time-
    // O(n^2) - worst case running time

    public static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int hole = i;
            while (hole > 0 && a[hole - 1] > temp) {
                a[hole] = a[hole - 1];
                hole = hole - 1;
            }
            a[hole] = temp;
        }
    }

    //----------------------------------------------------------------------------------------------------
    //Quicksort O(n log n) - average case running time
    // O(n^2) - worst case running time
    //in-place
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }


    private static void quickSort(int[] array, int start, int end) {
        int pivot;
        int index;

        if (start >= end) {
            return;
        }
        pivot = array[(start + end) / 2];
        index = partition(array, start, end, pivot);
        quickSort(array, start, index - 1);
        quickSort(array, index, end);

    }

    private static int partition(int[] array, int start, int end, int pivot) {
        while (start <= end) {
            //look for an element on the left part that is > pivot then it should be on the right side.
            while (array[start] < pivot) {
                start++;
            }
            //moves it inwards
            while (array[end] > pivot) {
                end--;
            }
            if (start <= end) {
                exchangeNumbers(array, start, end);
                start++;
                end--;
            }
        }
        return start;
    }

    private static void exchangeNumbers(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

//----------------------------------------------------------------------------------------------------
    //Merge Sort
    // O(n log n) - worst case running time
    // O(n) - space Complexity
    // Not in-place

    public static int[] mergeSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
       return mergeSort(array, 0, array.length - 1);
    }

    private static int[] mergeSort(int[] array,  int left, int right) {
        if (left >= right) {
            return new int[]{array[left]};
        }
        // Get the index of the element which is in the middle
        int middle = (left + right) / 2;
        // Sort the left side of the array
        int[] leftSide = mergeSort(array, left, middle);
        // Sort the right side of the array
        int[] rightSide = mergeSort(array, middle + 1, right);
        // Combine them both
        return mergeHalves(leftSide, rightSide);
    }

    private static int[] mergeHalves(int[] left, int[] right) {
        int leftStart = 0;
        int rightEnd = 0;
        int[] array = new int[left.length + right.length];

        for (int i = 0; i < array.length; i++) {
            if (rightEnd == right.length || (leftStart != left.length && left[leftStart] < right[rightEnd]))
                array[i] = left[leftStart++];
            else
                array[i] = right[rightEnd++];
        }
        return array;
    }
}









