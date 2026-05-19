package controller;

import model.Category;
import model.Item;
import model.ItemBuilder;
import model.Room;
import persistence.FileManager;
import service.ItemService;
import ui.ConsoleUI;


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


        Item item = buildItemFromInput();

        service.addItem(item);

        FileManager.save(service.listItems());

        ui.showItemAdded(item.getName());
    }

    private Item buildItemFromInput() {

        return new ItemBuilder()
                .name(ui.askItemName())
                .description(ui.askItemDescription())
                .room(ui.askRoom())
                .category(ui.askCategory())
                .quantity(ui.askQuantity())
                .build();
    }

    private void showStatisticsFlow(){
        int totalItems = service.countItems();
        int runningOut = service.countRunningOutItems();

        ui.showTotalItems(totalItems);
        ui.showRunningOutTotal(runningOut);
    }

    private void useItemFlow() {
        Item item = askExistingItem();

        if(item == null){
            return;
        }

        int qt = ui.askQuantity();
        boolean success = service.useItem(item,qt);
        if(!success){
            ui.failure();
            return;
        }

        save();
        ui.showUnitsLeft(item);


    }

    private void filterByCategoryFlow() {

        Category category = parseCategoryByNumber(ui.askItemCategory());

        ui.showItems(service.listByCategory(category));

    }

    private void exitFlow() {
        save();
        ui.showExitMessage();

    }

    private void updateItemFlow() {
        Item item = askExistingItem();
        if(item == null){
            return;
        }


        int qt = ui.askQuantity();
        boolean success = service.updateQuantity(item,qt);
        if(!success){
            ui.failure();
            return;
        }
        save();
        ui.showUnitsLeft(item);


    }

    private void restockItemFlow() {
        Item item = askExistingItem();
        if(item == null){
            return;
        }

        int qt = ui.askQuantity();

        service.restockItem(item, qt);
        save();
        ui.showUnitsLeft(item);

    }


    private void listItemFlow() {

        ui.showList();

        ui.showItems(service.listItems());

        if(!service.listRunningOutItems().isEmpty()){

            ui.showRunningOutList();

            ui.showItems(service.listRunningOutItems());
        }
    }

    private void searchItemFlow() {
        ui.searchItem();

        Item item = askExistingItem();
        System.out.println(item);
    }

    private void removeItemFlow() {
        ui.removeItem();

        String input = ui.askItemName();

        if (service.removeByName(input.trim())) {

            save();
            ui.showRemovedItem();
        } else {
            ui.showItemNotFound(input);
        }
    }


    private void moveItemFlow() {
        Item item = askExistingItem();
        if(item == null){
            ui.failure();
            return;
        }

        ui.showActualLocation(item);
        ui.showRooms();
        Room room = ui.askRoom();
        boolean success = service.moveItem(item,room);
        if(!success){
            ui.failure();
            return;
        }

        save();

    }


    private void changeCategoryFlow() {
        Item item = askExistingItem();
        if(item == null){
            return;
        }

        ui.showItemCategory(item);
        ui.showCategories();
        model.Category newCategory = ui.askCategory();
        service.changeCategory(item,newCategory);
        save();
    }

    private void save(){
        FileManager.save(service.listItems());
    }

    private Item findItemOrShowError(String input) {
        Item item = service.findByName(input.trim());
        if (item == null) {
            ui.showItemNotFound(input);

        }
        return item;
    }

    private Item askExistingItem( ){
        String input = ui.askItemName();
        return findItemOrShowError(input);

    }
}



