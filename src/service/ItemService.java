package service;

import model.Category;
import model.Item;
import model.Room;
import persistence.FileManager;

import java.util.ArrayList;
import java.util.List;

public class ItemService {

    private List<Item> items = new ArrayList<>();


    private boolean alreadyExists(String name) {
        return findByName(name) != null;
    }


    public void addItem(Item item){



        if (item.getName() == null || item.getName().isBlank())  {
            return;

        }

        if (alreadyExists(item.getName())) {
            return;

        }

        items.add(item);


    }

    public List<Item> sortItemsAlphabetic(){
        List<Item> sortedItems = new ArrayList<>(items);

        sortedItems.sort((a,b) ->
                a.getName().compareToIgnoreCase(b.getName()));

        return sortedItems;

    }

    public List<Item> listItems() {

        return new ArrayList<>(items);

    }

    public List<Item> listByCategory(Category category) {
        List<Item> filteredItems = new ArrayList<>();

        for (Item item : items) {
            if (item.getCategory() == category) {
                filteredItems.add(item);
            }
        }

        return filteredItems;
    }

    public List<Item> listRunningOutItemsByCategory(Category category ){

        List<Item> filteredItems = new ArrayList<>();

        for (Item item : items){
            if(item.isRunningOut() && item.getCategory() == category){
                filteredItems.add(item);
            }
        }

        return filteredItems;
    }

    public List<Item> listByRoom(Room room) {
        List<Item> filteredItems = new ArrayList<>();

        for (Item item : items) {
            if(item.getRoom() == room){
                filteredItems.add(item);
            }
        }

        return filteredItems;
    }

    public List<Item> listRunningOutItems(){
        List<Item> runningOutItems = new ArrayList<>();

        for(Item item : items){

            if(item.isRunningOut()){
                runningOutItems.add(item);
            }
        }

        return runningOutItems;
    }

    public Item findByName(String name){

        for(Item item : items){
            if(item.getName().equalsIgnoreCase(name)){
                return item;
            }
        }

        return null;
    }


    public boolean removeByName(String input) {

        Item item = findByName(input);

        if (item == null) {
            return false;
        }

        items.remove(item);
        return true;
    }

    public int countRunningOutItems(){
        return listRunningOutItems().size();
    }

    public int countItems() {
        return items.size();
    }


}


