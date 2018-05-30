import java.util.*;
import java.util.Arrays;
import java.util.Collections;

public class ReverseArray {

    public static void main (String[] args) {
        String[] array = {"hej","b"};
        String[] array2 = {"hej","b"};
        /*
        Since you are returning the reversed array from the reverse() method,
        you will need to assign the result to a variable in the main function,
        and then print using that.
        Since simply printing newArray in this case will print some weird object string,
        we need to use Arrays.toString() to print the actual contents of the reversed array.
         */

        String[] rev = ReverseArray.reverse(array);

        System.out.println(Arrays.toString(rev));

        reverseInPlace(array2);
    }

    public static <A> A[] reverse(A[] xs) {
        A[] ys = Arrays.copyOf(xs, xs.length);

        for (int i = 0; i < xs.length; i++)
            ys[i] = xs[xs.length - i - 1];

        return ys;
    }

    public static <A> void reverseInPlace(A[] xs) {
        for (int i = 0; i < xs.length / 2; i++) {
            A temp = xs[i];
            xs[i] = xs[xs.length - i - 1];
            xs[xs.length - i - 1] = temp;
        }
    }
}
