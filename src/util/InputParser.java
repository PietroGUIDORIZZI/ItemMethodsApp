package util;

import model.Category;
import model.Room;

public class InputParser {


    public static int parseInt(String input) {
        if (input == null) {
            return 0;
        }

        try {
            return Integer.parseInt(input.trim());

        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static Room parseRoom(String input) {
        if (input == null) {
            return Room.NOT_ALLOCATED;
        }

        try {

            return Room.valueOf(input.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            return Room.NOT_ALLOCATED;

        }
    }

    public static Category parseCategory(String input) {
        if (input == null) {
            return Category.NOT_CATEGORIZED;
        }
        try {

            return Category.valueOf(input.toUpperCase().trim());

        } catch (IllegalArgumentException e) {
            return Category.NOT_CATEGORIZED;
        }
    }
    public static Room parseRoomByNumber(String input) {

        try {

            int option = Integer.parseInt(input);

            Room[] rooms = Room.values();

            if(option < 1 || option > rooms.length){
                return Room.NOT_ALLOCATED;
            }

            return rooms[option - 1];

        } catch (Exception e) {

            return Room.NOT_ALLOCATED;
        }
    }

    public static Category parseCategoryByNumber(String input) {
        try{
            int option = Integer.parseInt(input);
            Category[] categories = Category.values();

            if(option < 1 || option > categories.length){
                return Category.NOT_CATEGORIZED;
            }

            return categories[option -1];

        }catch (Exception e){
            return Category.NOT_CATEGORIZED;
        }
    }
}
