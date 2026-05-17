package service;

import model.Item;

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


    public List<Item> listItems() {

        return new ArrayList<>(items);

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

}


