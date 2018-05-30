package Searching;

public class LinearSearch { //average/worst O(n). Best case O(1)

    public static void main(String a[]){
        int[] a1= {10,20,30,50,70,90};
        int key = 50;
        System.out.println(key+" is found at index: "+search(a1, key));
    }

    // This function returns index of element x in arr[]
    static int search(int arr[], int key)
    {
        for (int i = 0; i < arr.length; i++)
        {
            // Return the index of the element if the element
            // is found
            if (arr[i] == key)
                return i;
        }

        // return -1 if the element is not found
        return -1;
    }
}
