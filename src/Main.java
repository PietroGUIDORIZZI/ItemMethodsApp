import model.Category;
import model.Item;
import model.ItemBuilder;
import model.Room;
import service.ItemService;

import java.util.Scanner;

public class Main {
    static void main(String[] args){

        ItemService service = new ItemService();

        Item rice = new ItemBuilder()
                .name("Arroz")
                .description("5kg")
                .room(Room.KITCHEN)
                .category(Category.FOOD)
                .quantity(1)
                .build();

        service.addItem(rice);

        System.out.println(service.listItems());

    }
}
