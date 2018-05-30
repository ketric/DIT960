import java.util.*;
public class Arraylists {

    public static void main(String[] args) {
        ArrayList<String> myArray = new ArrayList<>();
        System.out.println(myArray);

        myArray.add("a");
        myArray.add("b");
        myArray.set(0, "b");
        System.out.println(myArray);
        System.out.println(myArray.get(0));
    }
}
