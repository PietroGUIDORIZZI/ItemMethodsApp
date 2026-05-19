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

        load();


    }

    //APP

    public void start() {
        boolean isRunning = true;

        while (isRunning) {
            ui.printMenu();
            String op = ui.askMenuOption();

            switch (op) {
                case "1":
                    listItemFlow();
                    break;

                case "2":
                    searchItemFlow();
                    break;

                case "3":
                    filterByCategoryFlow();
                    break;

                case "4":
                    showStatisticsFlow();
                    break;

                case "5":
                    addItemFlow();
                    break;

                case "6":
                    useItemFlow();
                    break;

                case "7":
                    restockItemFlow();
                    break;

                case "8":
                    updateItemFlow();
                    break;

                case "9":
                    moveItemFlow();
                    break;

                case "10":
                    changeCategoryFlow();
                    break;

                case "11":
                    removeItemFlow();
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

    //BUILDER

    private Item buildItemFromInput() {

        return new ItemBuilder()
                .name(ui.askItemName())
                .description(ui.askItemDescription())
                .room(ui.askRoom())
                .category(ui.askCategory())
                .quantity(ui.askQuantity())
                .build();
    }

    //FLOWS

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

    private void filterByCategoryFlow() {

        Category category = parseCategoryByNumber(ui.askItemCategory());

        ui.showItems(service.listByCategory(category));

    }

    private void showStatisticsFlow(){
        int totalItems = service.countItems();
        int runningOut = service.countRunningOutItems();

        ui.showTotalItems(totalItems);
        ui.showRunningOutTotal(runningOut);
    }


    private void addItemFlow() {


        Item item = buildItemFromInput();

        service.addItem(item);

        FileManager.save(service.listItems());

        ui.showItemAdded(item.getName());
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

    private void exitFlow() {
        save();
        ui.showExitMessage();

    }

    //EXTRACTED

    private void save(){
        FileManager.save(service.listItems());
    }

    private void load() {for (Item item : FileManager.load()) {
        service.addItem(item);
    }}

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



