import model.Item;
import model.ItemBuilder;
import persistence.FileManager;
import service.ItemService;
import ui.ConsoleUI;

import java.util.Scanner;

import static util.InputParser.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ConsoleUI ui = new ConsoleUI();
        ItemService service = new ItemService();

        String name = "";

        for(Item item : FileManager.load()){
            service.addItem(item);
        }

        String input = "";

        int qt = 0;


        boolean isRunning = true;

        while (isRunning) {
            ui.printMenu();

            String op = sc.nextLine();


            switch (op) {
                case "1":
                    addItemFlow(sc, service);
                    break;

                case "2":
                    listItemFlow(sc, service);

                    break;

                case "3":

                    System.out.println("Search Item: ");
                    input = sc.nextLine();
                    if(service.findByName(input.trim()) == null){
                        System.out.println("Item not found.");
                        break;
                    }
                    System.out.println((service.findByName(input.trim())));
                    break;

                case "4":

                    System.out.println("Remove Item: ");
                    input = sc.nextLine();
                    if(service.removeByName(input.trim())) {
                        System.out.println("Item removed.");
                    }else {
                        System.out.println("Item not found.");
                    }


                    break;

                case "5":
                    System.out.println("Use item: ");
                    input = sc.nextLine();
                    Item itemUses = service.findByName(input.trim());
                    if(itemUses == null){
                        System.out.println("Item not found.");
                        break;
                    }
                    System.out.println("Units used: ");
                    input = sc.nextLine();
                    qt = parseInt(input);
                    itemUses.use(qt);
                    System.out.println("Units left: " + itemUses.getQuantity());

                    break;

                case "6":
                    System.out.println("Restock item: ");
                    name = sc.nextLine();
                    Item itemRestock = service.findByName(name.trim());
                    if(itemRestock == null){
                        System.out.println("Item not found.");
                        break;
                    }
                    System.out.println("Units to restock: ");
                    input = sc.nextLine();
                    qt = parseInt(input);

                    itemRestock.restock(qt);
                    System.out.println("Units total after restock: " + itemRestock.getQuantity());
                    break;

                case "7":
                    System.out.println("Update item name: ");
                    name = sc.nextLine();
                    Item itemUpdate = service.findByName(name.trim());
                    if(itemUpdate == null){
                        System.out.println("Item not found.");
                        break;
                    }
                    System.out.println("Quantity update: ");
                    input = sc.nextLine();
                    qt = parseInt(input);
                    itemUpdate.updateQuantity(qt);
                    System.out.println("Units total after restock: " + itemUpdate.getQuantity());
                    break;


                case "8":

                    FileManager.save(service.listItems());
                    System.out.println("Thank you for using this app!");
                    isRunning = false;
                    break;


                default:
                    System.out.println("Invalid Option! Try again.");

            }



        }


    }
    private static void addItemFlow(Scanner sc, ItemService service){

        System.out.println("Item Name: ");
        String name = sc.nextLine();

        System.out.println("Item Description: ");
        String description = sc.nextLine();

        System.out.println("""
            Item Room:
            YARD
            LAUNDRY_ROOM
            KITCHEN
            SMALL_RESTROOM
            LIVING_ROOM
            STAIRS
            BIG_BEDROOM
            SMALL_BEDROOM
            RESTROOM
            BALCONY
            NOT_ALLOCATED
            """);

        String sRoom = sc.nextLine();

        System.out.println("""
            Item Category:
            FOOD
            CLEANING
            HYGIENE
            SAFETY
            BED_BATH
            OTHERS
            NOT_CATEGORIZED
            """);

        String sCategory = sc.nextLine();

        System.out.println("Item Quantity: ");

        int quantity = parseInt(sc.nextLine());

        Item item = new ItemBuilder()
                .name(name)
                .description(description)
                .room(parseRoom(sRoom))
                .category(parseCategory(sCategory))
                .quantity(quantity)
                .build();

        service.addItem(item);

        FileManager.save(service.listItems());

        System.out.println(name + " was added!");
    }
    private static void listItemFlow(Scanner sc, ItemService service) {
        System.out.println("\n=== Item List         ===");

        for(Item item2 : service.listItems()){
            System.out.println(item2);
        }

        System.out.println("\n=== Running out Items ===");

        for(Item item2 : service.listRunningOutItems()){
            System.out.println(item2);
        }
    }


}
