package ui;

import model.Item;

import java.util.Scanner;

public class ConsoleUI {

    private Scanner sc;

    public ConsoleUI(){

        sc = new Scanner(System.in);
    }

    public void printMenu(){
        System.out.println("=========================");
        System.out.println("   |     Inventory     | ");
        System.out.println("   |                   | ");
        System.out.println(" 1 |Add item           | ");
        System.out.println(" 2 |List items         | ");
        System.out.println(" 3 |Search items       | ");
        System.out.println(" 4 |Remove items       | ");
        System.out.println(" 5 |Use items          | ");
        System.out.println(" 6 |Restock items      | ");
        System.out.println(" 7 |Update quantity    | ");
        System.out.println(" 8 |Move Item          | ");
        System.out.println(" 9 |Exit               | ");
        System.out.println("   |                   | ");
        System.out.println("   |                   | ");
        System.out.println("=========================");
        System.out.println();

    }

    public String askItemName(){
        System.out.println("Item Name: ");
        return sc.nextLine();
    }

    public String askItemDescription(){
        System.out.println("Item Description: ");
        return sc.nextLine();
    }

    public String askItemRoom(){
        System.out.println("Item Room: ");
        return sc.nextLine();
    }

    public String askItemCategory(){
        System.out.println("Item Category: ");
        return  sc.nextLine();
    }

    public String askQuantity(){
        System.out.println("Quantity: ");
        return  sc.nextLine();
    }

    public void showUnitsLeft(int quantity){
        System.out.println("Units left: " + quantity);

    }

    public void showItem(Item item){
        System.out.println(item.toString());
    }

    public void showItemAdded(String name){
        System.out.println(name + " was added!");
    }

    public void showItemNotFound(String name){
        System.out.println(name + " not found!");
    }

    public String askMenuOption(){
        return sc.nextLine();
    }

    public void showExitMessage(){
        System.out.println("Thanks for using the app!");
    }
}
