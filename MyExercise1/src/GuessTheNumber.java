import java.util.*;

public class GuessTheNumber {
    private int high = 200;
    private int low = 1;
    private int guess = (low+high)/2;
    private Scanner reply = new Scanner(System.in);

    public static void main(String[] args){
        GuessTheNumber guessnumber = new GuessTheNumber();
        guessnumber.run();
    }

    public void run(){
        int i = 1;
        printIntro();
        /*difference between do-while and while is that do-while
        evaluates its expression at the bottom of the loop instead of the top.
        Therefore, the statements within the do block are always executed at least once.
        */
        do {
            System.out.print(i++ + ". ");
        } while (!guess());

        System.out.println("\nYour number was: " + guess);

    }
    public void printIntro() {
        String msg = "Hi I am going to figure out the number you are thinking about! (numbers between 1-200)\n\n" + "please answer if your number is '(h)igher' or '(l)ower'\n";

        System.out.println(msg);
    }

    private boolean guess() {
        System.out.print("Is your number: " + guess + "?\n> ");
        String answer = reply.next();

        if (answer.startsWith("h"))
            low  = guess;
        else
            high = guess;

        guess = (low + high) / 2;

        return (high - low) <= 2;
    }
}

