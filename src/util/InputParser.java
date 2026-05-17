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
}
