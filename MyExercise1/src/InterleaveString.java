import static java.lang.Integer.max;
/*
Write a method that interleaves two strings: it should take one character from the first string,
 then one from the second string, another from the first string and so on.
 Once one string has no characters left it should carry on with the other string.
 For example, interleaving the strings “anna” and “patrik” should give the result “apnantarik”
 (or “paantnraik”). Use s.length() to find the length of a string
 and s.charAt(i) to find the character at index i.
 */
public class InterleaveString {

    public static String interleave(String a, String b){
        int n = a.length();
        int m = b.length();
        char[] res = new char[n + m];
        int i = 0;
        int j = 0;

        while(j < max(n,m)){
            if (j < n) res[i++] = a.charAt(j);
            if (j < m) res[i++] = b.charAt(j++);
        }
        return new String(res);
    }

    public static void main(String[] args){
        String a = "anna";
        String p = "patrik";
        String res = interleave(a, p);

        System.out.println(res);
    }
}
