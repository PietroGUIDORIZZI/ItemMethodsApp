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


        for (Item item : FileManager.load()) {
            service.addItem(item);
        }

        boolean isRunning = true;

        while (isRunning) {
            ui.printMenu();

            String op = sc.nextLine();


            switch (op) {
                case "1":
                    addItemFlow(sc, service);
                    break;

                case "2":
                    listItemFlow(service);
                    break;

                case "3":
                    searchItemFlow(sc, service);
                    break;

                case "4":
                    removeItemFlow(sc, service);
                    break;

                case "5":
                    useItemFlow(sc, service);
                    break;

                case "6":
                    restockItemFlow(sc, service);
                    break;

                case "7":
                    updateItemFlow(sc, service);
                    break;


                case "8":
                    exitFlow(service);
                    isRunning = false;
                    break;


                default:
                    System.out.println("Invalid Option! Try again.");

            }


        }


    }

    private static void exitFlow(ItemService service) {
        FileManager.save(service.listItems());
        System.out.println("Thank you for using this app!");

    }

    private static void updateItemFlow(Scanner sc, ItemService service) {
        System.out.println("Update item name: ");
        String name = sc.nextLine();
        Item itemUpdate = service.findByName(name.trim());
        if (itemUpdate == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.println("Quantity update: ");
        String input = sc.nextLine();
        int qt = parseInt(input);
        itemUpdate.updateQuantity(qt);
        FileManager.save(service.listItems());
        System.out.println("Units total after restock: " + itemUpdate.getQuantity());

    }

    private static void restockItemFlow(Scanner sc, ItemService service) {
        System.out.println("Restock item: ");
        String name = sc.nextLine();
        Item itemRestock = service.findByName(name.trim());
        if (itemRestock == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.println("Units to restock: ");
        String input = sc.nextLine();
        int qt = parseInt(input);

        itemRestock.restock(qt);
        FileManager.save(service.listItems());
        System.out.println("Units total after restock: " + itemRestock.getQuantity());
    }

    private static void addItemFlow(Scanner sc, ItemService service) {

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

    private static void listItemFlow(ItemService service) {
        System.out.println("\n=== Item List         ===");

        for (Item item2 : service.listItems()) {
            System.out.println(item2);
        }

        System.out.println("\n=== Running out Items ===");

        for (Item item2 : service.listRunningOutItems()) {
            System.out.println(item2);
        }
    }

    private static void searchItemFlow(Scanner sc, ItemService service) {
        System.out.println("Search Item: ");

        String input = sc.nextLine();

        Item item = service.findByName((input.trim()));

        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.println(item);
    }

    private static void removeItemFlow(Scanner sc, ItemService service) {
        System.out.println("Remove Item: ");

        String input = sc.nextLine();

        if (service.removeByName(input.trim())) {

            FileManager.save(service.listItems());
            System.out.println("Item removed.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void useItemFlow(Scanner sc, ItemService service) {
        System.out.println("Use item: ");
        String input = sc.nextLine();
        Item itemUses = service.findByName(input.trim());
        if (itemUses == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.println("Units used: ");
        input = sc.nextLine();
        int qt = parseInt(input);
        itemUses.use(qt);
        FileManager.save(service.listItems());
        System.out.println("Units left: " + itemUses.getQuantity());


    }
}
