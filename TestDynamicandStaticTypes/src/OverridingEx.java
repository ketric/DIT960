public class OverridingEx {

    public void run(){
        System.out.println("I'm run() from parent");
    }
}

class BMW extends OverridingEx {

    public void run(){
        System.out.println(" ");
        System.out.println("I'm run() from child");
    }
}