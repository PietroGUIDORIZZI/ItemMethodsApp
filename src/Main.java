import model.Item;
import model.ItemBuilder;
import persistence.FileManager;
import service.ItemService;
import ui.ConsoleUI;

import java.util.Scanner;

import static util.InputParser.*;

public class Main {
    public static void main(String[] args) {


        ConsoleUI ui = new ConsoleUI();
        ItemService service = new ItemService();


        for (Item item : FileManager.load()) {
            service.addItem(item);
        }

        boolean isRunning = true;

        while (isRunning) {
            ui.printMenu();

            String op = ui.askMenuOption();


            switch (op) {
                case "1":
                    addItemFlow(ui, service);
                    break;

                case "2":
                    listItemFlow( service);
                    break;

                case "3":
                    searchItemFlow(ui, service);
                    break;

                case "4":
                    removeItemFlow(ui, service);
                    break;

                case "5":
                    useItemFlow(ui, service);
                    break;

                case "6":
                    restockItemFlow(ui, service);
                    break;

                case "7":
                    updateItemFlow(ui, service);
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

    private static void updateItemFlow(ConsoleUI ui, ItemService service) {
        System.out.println("Update item name ");
        String name = ui.askItemName();
        Item itemUpdate = service.findByName(name.trim());
        if (itemUpdate == null) {
            System.out.println("Item not found.");
            return;
        }
        String input = ui.askQuantity();
        int qt = parseInt(input);
        itemUpdate.updateQuantity(qt);
        FileManager.save(service.listItems());
        System.out.println("Units total after restock: " + itemUpdate.getQuantity());

    }

    private static void restockItemFlow(ConsoleUI ui, ItemService service) {
        System.out.println("Restock item: ");
        String name = ui.askItemName();
        Item itemRestock = service.findByName(name.trim());
        if (itemRestock == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.println("Units to restock: ");
        String input = ui.askQuantity();
        int qt = parseInt(input);

        itemRestock.restock(qt);
        FileManager.save(service.listItems());
        System.out.println("Units total after restock: " + itemRestock.getQuantity());
    }

    private static void addItemFlow(ConsoleUI ui, ItemService service) {

        String name = ui.askItemName();

        String description = ui.askItemDescription();

        String sRoom = ui.askItemRoom();

        String sCategory = ui.askItemCategory();

        int quantity = parseInt(ui.askQuantity());

        Item item = new ItemBuilder()
                .name(name)
                .description(description)
                .room(parseRoom(sRoom))
                .category(parseCategory(sCategory))
                .quantity(quantity)
                .build();

        service.addItem(item);

        FileManager.save(service.listItems());

        ui.showItemAdded(name);
    }

    private static void listItemFlow(ItemService service) {
        System.out.println("\n=== Item List ===========");

        for (Item item2 : service.listItems()) {
            System.out.println(item2);
        }

        System.out.println("\n=== Running out Items ===");

        for (Item item2 : service.listRunningOutItems()) {
            System.out.println(item2);
        }
    }

    private static void searchItemFlow(ConsoleUI ui, ItemService service) {
        System.out.println("Search Item: ");

        String input = ui.askItemName();

        Item item = service.findByName((input.trim()));

        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.println(item);
    }

    private static void removeItemFlow(ConsoleUI ui, ItemService service) {
        System.out.println("Remove Item: ");

        String input = ui.askItemName();

        if (service.removeByName(input.trim())) {

            FileManager.save(service.listItems());
            System.out.println("Item removed.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void useItemFlow(ConsoleUI ui, ItemService service) {
        System.out.println("Use item: ");
        String input = ui.askItemName();
        Item itemUses = service.findByName(input.trim());
        if (itemUses == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.println("Units used: ");
        input = ui.askQuantity();
        int qt = parseInt(input);
        itemUses.use(qt);
        FileManager.save(service.listItems());
        System.out.println("Units left: " + itemUses.getQuantity());


    }
}
