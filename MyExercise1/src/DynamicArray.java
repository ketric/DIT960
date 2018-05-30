import java.util.*;
/*
When we enter element in array but array is full then create a function,
this function create a new array double size and copy element from previous array to new array
 and return this new array
 */
public class DynamicArray {

    static int[] increaseSizeOfArray(int[] arr) {
        int[] brr = new int[(arr.length * 2)];
        for (int i = 0; i < arr.length; i++) {      //O(n)
            brr[i] = arr[i];
        }
        return brr;
    }

    public static void main(String[] args) {

        int[] arr = new int[5];
        for (int i = 0; i < 11; i++) {  //O(n)
            if (i < arr.length) {
                arr[i] = i + 100;
            } else {
                arr = increaseSizeOfArray(arr);
                arr[i] = i + 100;
            }
        }
        for(int i = 0; i < arr.length; i++) {   //O(n)
            System.out.println("arr="+arr[i]);
        }

        List<Integer> myArray = new ArrayList<>();
        myArray.add(1); //O(1)

        System.out.println(myArray);
    }
}
