import controller.InventoryController;
import model.Category;
import model.Item;
import model.ItemBuilder;
import persistence.FileManager;
import service.ItemService;
import ui.ConsoleUI;

import java.util.List;

import static util.InputParser.*;

public class Main {
    public static void main(String[] args) {
        InventoryController controller = new InventoryController();

        controller.start();




    }


}
