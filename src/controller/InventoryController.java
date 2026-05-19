package controller;

import model.Category;
import model.Item;
import model.ItemBuilder;
import persistence.FileManager;
import service.ItemService;
import ui.ConsoleUI;

import java.util.List;

import static util.InputParser.*;

public class InventoryController {
    private final ConsoleUI ui;
    private final ItemService service;

    public InventoryController() {
        ui = new ConsoleUI();

        service = new ItemService();

        for (Item item : FileManager.load()) {
            service.addItem(item);
        }
    }

    public void start() {
        boolean isRunning = true;

        while (isRunning) {
            ui.printMenu();
            String op = ui.askMenuOption();

            switch (op) {
                case "1":
                    addItemFlow();
                    break;

                case "2":
                    listItemFlow();
                    break;

                case "3":
                    searchItemFlow();
                    break;

                case "4":
                    removeItemFlow();
                    break;

                case "5":
                    useItemFlow();
                    break;

                case "6":
                    restockItemFlow();
                    break;

                case "7":
                    updateItemFlow();
                    break;

                case "8":
                    moveItemFlow();
                    break;

                case "9":
                    filterByCategoryFlow();
                    break;

                case "10":
                    showStatisticsFlow();
                    break;

                case "11":
                    changeCategoryFlow();
                    break;

                case "12":
                    exitFlow();
                    isRunning = false;
                    break;


                default:
                    ui.showInvalidOption();

            }


        }

    }
    private void addItemFlow() {

        String name = ui.askItemName();

        String description = ui.askItemDescription();

        String room = ui.askItemRoom();

        String category = ui.askItemCategory();

        int quantity = parseInt(ui.askQuantity());

        Item item = new ItemBuilder()
                .name(name)
                .description(description)
                .room(parseRoom(room))
                .category(parseCategory(category))
                .quantity(quantity)
                .build();

        service.addItem(item);

        FileManager.save(service.listItems());

        ui.showItemAdded(name);
    }

    private void showStatisticsFlow(){
        int totalItems = service.countItems();
        int runningOut = service.countRunningOutItems();

        ui.showTotalItems(totalItems);
        ui.showRunningOutTotal(runningOut);
    }

    private void useItemFlow() {
        String input = ui.askItemName();
        Item itemUses = service.findByName(input.trim());
        if (itemUses == null) {
            ui.showItemNotFound(input);
            return;
        }
        input = ui.askQuantity();
        int qt = parseInt(input);
        itemUses.use(qt);
        save();
        ui.showUnitsLeft(itemUses);


    }

    private void filterByCategoryFlow() {
        String input = ui.askItemCategory();
        Category category = parseCategory(input);
        List<Item> filteredItems = service.listByCategory(category);

        for (Item item : filteredItems) {

            System.out.println(item);
        }

    }

    private void exitFlow() {
        FileManager.save(service.listItems());
        ui.showExitMessage();

    }

    private void updateItemFlow() {
        String name = ui.askItemName();
        Item itemUpdate = service.findByName(name.trim());
        if (itemUpdate == null) {
            ui.showItemNotFound(name);
            return;
        }
        String input = ui.askQuantity();
        int qt = parseInt(input);
        itemUpdate.updateQuantity(qt);
        save();
        ui.showUnitsLeft(itemUpdate);


    }

    private void restockItemFlow() {
        String name = ui.askItemName();
        Item itemRestock = service.findByName(name.trim());
        if (itemRestock == null) {
            ui.showItemNotFound(name);
            return;
        }
        String input = ui.askQuantity();
        int qt = parseInt(input);

        itemRestock.restock(qt);
        save();
        ui.showUnitsLeft(itemRestock);

    }


    private void listItemFlow() {

        System.out.println("\n=== Item List ===");

        ui.showItems(service.listItems());

        if(!service.listRunningOutItems().isEmpty()){

            System.out.println("\n=== Running out Items ===");

            ui.showItems(service.listRunningOutItems());
        }
    }

    private void searchItemFlow() {
        System.out.println("Search Item: ");

        String input = ui.askItemName();

        Item item = service.findByName((input.trim()));

        if (item == null) {
            ui.showItemNotFound(input);
            return;
        }
        System.out.println(item);
    }

    private void removeItemFlow() {
        System.out.println("Remove Item: ");

        String input = ui.askItemName();

        if (service.removeByName(input.trim())) {

            save();
            System.out.println("Item removed.");
        } else {
            ui.showItemNotFound(input);
        }
    }


    private void moveItemFlow() {
        String input = ui.askItemName();
        Item itemMove = service.findByName(input.trim());
        if (itemMove == null) {
            ui.showItemNotFound(input);
            return;
        }
        System.out.printf("Item Location: %s\n", itemMove.getRoom());
        ui.showRooms();
        input = ui.askItemRoom();
        itemMove.moveToRoom(parseRoomByNumber(input));
        save();

    }

    public void changeCategoryFlow() {
        String input = ui.askItemName();
        Item itemCategory = service.findByName(input.trim());
        if(itemCategory == null){
            ui.showInvalidOption();
            return;
        }
        System.out.printf("Item Category: %s\n", itemCategory.getCategory());
        ui.showCategories();
        input = ui.askItemCategory();
        itemCategory.changeCategory(parseCategoryByNumber(input));
        save();
    }

    public void save(){
        FileManager.save(service.listItems());
    }
}



