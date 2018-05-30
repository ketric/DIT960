/**
 * Created by BenJ on 2017-05-05.
 */
import java.util.Scanner;

public class StockExchange {

    static Scanner sc = new Scanner(System.in);
    static BinaryHeap BuyerTree = new Buyer(10);
    static BinaryHeap SellerTree = new Seller(10);
    static String[] onwers = new String[];

    public static void main(String[] args) {


        System.out.println("enter your command");
        String command = sc.nextLine();
    }

    private static void offerToSell(String ownerName, int value) {
        SellerTree.add(value);
    }

    private static void offerToBuy(String ownerName, int value){
        BuyerTree.add(value);
    }

    private static void changeSellValue(String ownerName, int value) {
        SellerTree.
    }

}
