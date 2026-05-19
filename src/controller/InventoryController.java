package controller;

import model.Category;
import model.Item;
import model.ItemBuilder;
import model.Room;

import service.ItemService;
import ui.ConsoleUI;


import static util.InputParser.*;

public class InventoryController {

    private final ConsoleUI ui;
    private final ItemService service;




    public InventoryController() {
        ui = new ConsoleUI();

        service = new ItemService();


        service.loadItems();


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
                    moveItemFlow();
                    break;

                case "9":
                    changeCategoryFlow();
                    break;

                case "10":
                    changeDescriptionFlow();
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
        if(item == null){
            return;
        }
        ui.showItem(item);
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

        service.saveItems();

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

        service.saveItems();
        ui.showUnitsLeft(item);


    }

    private void restockItemFlow() {
        Item item = askExistingItem();
        if(item == null){
            return;
        }

        int qt = ui.askQuantity();

        service.restockItem(item, qt);
        service.saveItems();
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
        service.saveItems();
        ui.showUnitsLeft(item);


    }

    private void moveItemFlow() {
        Item item = askExistingItem();
        if(item == null){
            ui.failure();
            return;
        }

        ui.showActualLocation(item);

        Room room = ui.askRoom();
        boolean success = service.moveItem(item,room);
        if(!success){
            ui.failure();
            return;
        }

        service.saveItems();

    }

    private void changeCategoryFlow() {
        Item item = askExistingItem();
        if(item == null){
            return;
        }

        ui.showItemCategory(item);

        model.Category newCategory = ui.askCategory();
        service.changeCategory(item,newCategory);
        service.saveItems();
    }

    private void changeDescriptionFlow(){
        Item item = askExistingItem();
        if(item == null){
            return;
        }

        ui.showItemDescription(item);
        String input = ui.askDescription();
        service.changeDescription(item,input);
        service.saveItems();


    }

    private void removeItemFlow() {
        ui.removeItem();

        String input = ui.askItemName();

        if (service.removeByName(input.trim())) {

            service.saveItems();
            ui.showRemovedItem();
        } else {
            ui.showItemNotFound(input);
        }
    }

    private void updateDescription(){
        Item item = askExistingItem();
        if(item == null){
            return;
        }

        ui.showItemDescription(item);
        String description = ui.askDescription();
        service.changeDescription(item,description);

    }

    private void exitFlow() {
        service.saveItems();
        ui.showExitMessage();

    }

    //EXTRACTED

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



