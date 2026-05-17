package persistence;

import model.Item;
import model.ItemBuilder;

import java.io.PrintWriter;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static util.InputParser.*;

public class FileManager {

        public static void save(List<Item> items){
            try(PrintWriter writer = new PrintWriter("items.txt")){

                for(Item item : items){
                    writer.println(item.getName() + ";" +
                            item.getDescription() + ";" +
                            item.getRoom() + ";" +
                            item.getCategory() + ";" +
                            item.getQuantity()
                    );
                }

                }catch (Exception e) {
                System.out.println("Error saving file.");
            }
        }

        public static List<Item> load(){
            List<Item> items = new ArrayList<>();

            File file = new File("items.txt");

            if(!file.exists()){
                return items;
            }

            try(Scanner sc = new Scanner(file)){
                while(sc.hasNextLine()){
                    String line = sc.nextLine();
                    String[] parts = line.split(";");

                    Item item = new ItemBuilder()
                            .name(parts[0])
                            .description(parts[1])
                            .room(parseRoom(parts[2]))
                            .category(parseCategory(parts[3]))
                            .quantity(parseInt(parts[4]))
                            .build();

                    items.add(item);

                }
            }catch (Exception e){
                System.out.println("Error loading file.");
            }
            return items;
        }
}
