package ui;

import model.Category;
import model.Item;
import model.Room;
import util.InputParser;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private Scanner sc;

    public ConsoleUI(){

        sc = new Scanner(System.in);
    }

    public void printMenu(){
        System.out.println("========================|");
        System.out.println("       Inventory        | ");
        System.out.println("                        | ");
        System.out.println("  1 |List items         | ");
        System.out.println("  2 |Search items       | ");
        System.out.println("  3 |Filter by Category | ");
        System.out.println("  4 |Show Statistics    | ");
        System.out.println("========================|");
        System.out.println("  5 |Add item           | ");
        System.out.println("  6 |Use items          | ");
        System.out.println("  7 |Restock items      | ");
        System.out.println("  8 |Update quantity    | ");
        System.out.println("  9 |Move Item          | ");
        System.out.println(" 10 |Change Category    | ");
        System.out.println(" 11 |Remove items       | ");
        System.out.println(" 12 |Exit               | ");
        System.out.println("    |                   | ");
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

    public Room askRoom(){
        showRooms();
        String input = sc.nextLine();

        return InputParser.parseRoomByNumber(input);

    }

    public Category askCategory(){
        showCategories();
        String input = sc.nextLine();

        return InputParser.parseCategoryByNumber(input);
    }

    public String askMenuOption(){
        return sc.nextLine();
    }
    public String askItemCategory(){
        System.out.println("Item Category: ");
        return  sc.nextLine();
    }

    public int askQuantity(){
        System.out.println("Quantity: ");
        return Integer.parseInt(sc.nextLine());
    }



    public void showUnitsLeft(Item item){
        System.out.println("Units left: " + item.getQuantity());

    }

    public void showRunningOutTotal(int total){
        System.out.println("Running out items: " + total);
    }

    public void showTotalItems(int total){
        System.out.println("Total items: " + total);
    }

    public void showItemAdded(String name){
        System.out.println(name + " was added!");
    }

    public void showItemNotFound(String name){
        System.out.println(name + " not found!");
    }


    public void showExitMessage(){
        System.out.println("Thanks for using the app!");
    }

    public void showItems(List<Item> items){
        for (Item item : items){
            System.out.println(item);
        }
    }

    public void showInvalidOption() {
        System.out.println("Invalid Option! Try again.");
    }

    public void showRooms() {

        Room[] rooms = Room.values();

        for (int i = 0; i < rooms.length; i++) {

            System.out.println((i + 1) + " - " + rooms[i]);
        }
    }

    public void showCategories(){
        Category[] categories = Category.values();

        for (int i = 0; i< categories.length; i++){
            System.out.println((i+1) + " - " + categories[i]);
        }
    }

    public void showList() {
        System.out.println("\n=== Item List ===");
    }

    public void showRunningOutList() {
        System.out.println("\n=== Running out Items ===");
    }

    public void searchItem() {
        System.out.println("Search Item: ");
    }

    public void removeItem() {
        System.out.println("Remove Item: ");
    }

    public void showRemovedItem() {
        System.out.println("Item removed.");
    }

    public void showActualLocation(Item item) {
        System.out.printf("Item Location: %s\n", item.getRoom());
    }

    public void showItemCategory(Item item) {
        System.out.printf("Item Category: %s\n", item.getCategory());
    }

    public void showItem(Item item){
        System.out.println(item);
    }


    public void failure() {
        System.out.println("Operation invalidated.");
    }
}
