import java.util.*;
public class Strings {

    public static void main(String args[]){

        String randomString = "I'm just a string...";
        String gotToQuote = "He said, \"I'm here\"";
        int numTwo = 2;

        System.out.println(randomString + " " + gotToQuote + numTwo);

        String uppercaseStr = "BIG";
        String lowercaseStr = "big";

        if(uppercaseStr.equalsIgnoreCase(lowercaseStr)){
            System.out.println("They're equal");
        }

        String letters = "abcde";
        String moreLetters = "fghijk";

        System.out.println("2nd char: " + letters.charAt(1));
        System.out.println(letters.compareTo(moreLetters));
        System.out.println(letters.contains("abc"));
        StringBuffer randSB = new StringBuffer("asdas");
        System.out.println(randSB.append(" again"));
        randSB.ensureCapacity(60);
        System.out.println(randSB.capacity());
        System.out.println(randSB.length());
        randSB.trimToSize();
        System.out.println(randSB.insert(1, "nother"));

    }
}
