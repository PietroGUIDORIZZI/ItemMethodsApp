package repository;

import model.Item;
import persistence.FileManager;

import java.util.List;

public class InventoryRepository {
    public void save(List<Item> items){
        FileManager.save(items);
    }

    public List<Item> load(){
        return FileManager.load();
    }
}
