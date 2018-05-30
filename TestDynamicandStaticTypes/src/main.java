/**
 * Created by kenny on 2018-03-15.
 */
public class main {
    public static void main(String arg[]) {
        Animal x = new Animal();
        Animal doggy = new dog();
        Animal pussycat = new cat();
        dog d = new dog();

        method(doggy);   //Dog is eating
        x.eat();         //eating
        doggy.eat();     //Dog is eating
        pussycat.eat();  //Cat is eating


        OverloadingEx obj = new OverloadingEx();
        obj.add();      //Hi i'm add without parameter
        obj.add(5);     //Hi i'm add with parameter

        OverridingEx obj2 = new OverridingEx(); //PARENT CLASS
        BMW obj1 = new BMW();
        obj1.run();     //I'm run() from child
        obj2.run();     //I'm run() from parent
    }


    private static void method(Animal var) {
        var.eat();
    }
}
