import java.util.*;

public class Uppgift7 {
    public static <T> T[] removeDups(T[] xs) {
        T[] ys = Arrays.copyOf(xs, xs.length);  // create a copy for sorting, leaving xs untouched
        T[] zs = Arrays.copyOf(xs, xs.length);  // result array
        Arrays.sort(ys);

        int size = 0;

        for (int i = 1; i < ys.length; i++) {   // compare with predecessor, only add if different
            if (!ys[i-1].equals(ys[i]))
                zs[size++] = ys[i-1];
        }

        zs[size++] = ys[ys.length - 1];         // we can't compare the last element, so add it

        return Arrays.copyOf(zs, size);         // return the array with right size
    }

    public static <T> boolean hasDups(T[] xs) {
        T[] ys = Arrays.copyOf(xs, xs.length);
        Arrays.sort(ys);

        for (int i = 1; i < ys.length; i++)
            if (ys[i-1].equals(ys[i]))
                return true;

        return false;
    }

    public static void main(String[] args) {
        Integer[] xs = {4,4,4,4,3,2,3,2,3,4,2,1,2,2,3,3,3,4,4,4,4};

        Integer[] ys = removeDups(xs);

        System.out.println(Arrays.toString(ys));
        System.out.println(hasDups(xs));
        System.out.println(hasDups(removeDups(xs)));

        // Should be tested more thoroughly, but that is left as an exercise ;-)
    }
}