import model.Item;
import model.ItemBuilder;
import service.ItemService;

import java.util.Scanner;

import static util.InputParser.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ItemService service = new ItemService();

        String input = "";

        int qt = 0;


        boolean isRunning = true;

        while (isRunning) {
            System.out.println("========================");
            System.out.println("   | Inventory         | ");
            System.out.println(" 1 | add item          | ");
            System.out.println(" 2 | list items        | ");
            System.out.println(" 3 | search items      | ");
            System.out.println(" 4 | remove items      | ");
            System.out.println(" 5 | use items         | ");
            System.out.println(" 6 | restock items     | ");
            System.out.println(" 7 | update quantity   | ");
            System.out.println(" 8 | exit              | ");
            System.out.println("========================");
            System.out.println();
            String op = sc.nextLine();


            switch (op) {
                case "1":
                    System.out.println("Item Name: ");
                    String name = sc.nextLine();


                    System.out.println("Item Description: ");
                    String description = sc.nextLine();

                    System.out.println("Item Room: YARD,\n" +
                            "           LAUNDRY_ROOM,\n" +
                            "           KITCHEN,\n" +
                            "           SMALL_RESTROOM,\n" +
                            "           LIVING_ROOM,\n" +
                            "           STAIRS,\n" +
                            "           BIG_BEDROOM,\n" +
                            "           SMALL_BEDROOM,\n" +
                            "           RESTROOM,\n" +
                            "           BALCONY,\n" +
                            "           NOT_ALLOCATED");
                    String Sroom = sc.nextLine();

                    System.out.println("Item Category: FOOD,\n" +
                            "               CLEANING,\n" +
                            "               HYGIENE,\n" +
                            "               SAFETY,\n" +
                            "               BED_BATH,\n" +
                            "               OTHERS,\n" +
                            "               NOT_CATEGORIZED\n");
                    String Scategory = sc.nextLine();

                    System.out.println("Item Quantity: ");
                    int quantity = sc.nextInt();
                    sc.nextLine();

                    Item item = new ItemBuilder()
                            .name(name)
                            .description(description)
                            .room(parseRoom(Sroom))
                            .category(parseCategory(Scategory))
                            .quantity(quantity)
                            .build();

                    service.addItem(item);
                    System.out.println(name + " was Added!");

                    break;

                case "2":
                    System.out.println("\n=== Item List         ===");

                    for(Item item2 : service.listItems()){
                        System.out.println(item2);
                    }

                    System.out.println("\n=== Running out Items ===");

                    for(Item item2 : service.listRunningOutItems()){
                        System.out.println(item2);
                    }

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
                        System.out.println("Item romoved.");
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
                    System.out.println("Update item quantity: ");
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
                    System.out.println("Thank you for using this app!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid Option! Try again.");
            }


        }
    }


}
