package Searching;

public class BinarySearch {// average/worst case O(log n)

    // Driver method to test below
    public static void main(String args[])
    {
        BinarySearch ob = new BinarySearch();
        int arr[] = {2, 3, 4, 10, 40};
        int n = arr.length;
        int x = 10;
        int result = ob.binarySearch(arr, x);
        if (result == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at " +
                    "index " + result);
    }

    // Returns index of x if it is present in arr[],
    // else return -1
    int binarySearch(int arr[], int x)
    {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi)
        {
            int mid = lo + (hi-lo)/2;

            // Check if x is present at mid
            if (arr[mid] == x)
                return mid;

            // If x greater, ignore left half
            if (arr[mid] < x)
                lo = mid + 1;
                // If x is smaller, ignore right half
            else
                hi = mid - 1;
        }
        // if we reach here, then element was not present
        return -1;
    }
}
