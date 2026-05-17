package ui;

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
        System.out.println(" 1 |add item           | ");
        System.out.println(" 2 |list items         | ");
        System.out.println(" 3 |search items       | ");
        System.out.println(" 4 |remove items       | ");
        System.out.println(" 5 |use items          | ");
        System.out.println(" 6 |restock items      | ");
        System.out.println(" 7 |update quantity    | ");
        System.out.println(" 8 |exit               | ");
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

    public void showItemAdded(String name){
        System.out.println(name + " was added!");
    }

    public void showItemNotFound(String name){
        System.out.println(name + " not found!");
    }

    public String askMenuOption(){
        return sc.nextLine();
    }

}
