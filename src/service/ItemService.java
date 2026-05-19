package service;

import model.Category;
import model.Item;
import model.Room;
import repository.InventoryRepository;

import java.util.ArrayList;
import java.util.List;

public class ItemService {

    private final List<Item> items = new ArrayList<>();

    private final InventoryRepository repository;

    public ItemService(){
        repository = new InventoryRepository();
    }


    private boolean alreadyExists(String name) {
        return findByName(name) != null;
    }


    public boolean addItem(Item item){



        if (item.getName() == null || item.getName().isBlank())  {
            return false;

        }

        if (alreadyExists(item.getName())) {
            return false;

        }

        items.add(item);
        return true;

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

    public boolean useItem(Item item, int qt){
        if (item == null){
            return false;
        }

        if(qt <= 0){
            return false;
        }

        if(item.getQuantity() < qt){
            return false;
        }

        item.setQuantity(item.getQuantity() - qt);

        return true;


    }

    public void restockItem(Item item, int qt){
        if(item == null || qt <= 0){
            return;
        }
        item.setQuantity(item.getQuantity()+qt);

    }

    public boolean moveItem(Item item, Room room){
        if(item ==null || room == null){
            return false;
        }

        item.setRoom(room);
        return true;
    }

    public void changeCategory(Item item, Category category){
        if(item == null || category == null){
            return;
        }
        item.setCategory(category);
    }

    public boolean updateQuantity(Item item, int qt){
        if(item == null || qt < 0 ){
            return false;
        }

        item.setQuantity(qt);
        return true;

    }

    public void changeDescription(Item item, String input){
        if(item == null){
            return;
        }
        item.setDescription(input);
        saveItems();
    }

    public void saveItems(){
        repository.save(items);
    }

    public void loadItems(){
        items.addAll(repository.load());
    }

}


