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

        if (item == null) {
            return;

        }

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

    public Item findByName(String name){

        for(Item item : items){
            if(item.getName().equalsIgnoreCase(name)){
                return item;
            }
        }

        return null;
    }

}


