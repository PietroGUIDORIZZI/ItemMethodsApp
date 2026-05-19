package model;

public class ItemBuilder {

    private String name;
    private String description;
    private Room room;
    private Category category;
    private int quantity;

    public ItemBuilder name(String name) {

        this.name = name;

        return this;

    }

    public ItemBuilder description(String description) {

        this.description = description;

        return this;
    }


    public ItemBuilder room(Room room) {

        this.room = room;

        return this;
    }


    public ItemBuilder category(Category category) {

        this.category = category;

        return this;
    }


    public ItemBuilder quantity(int quantity) {
        this.quantity = quantity;

        return this;
    }

    public Item build() {

        return new Item(name, description, room, category, quantity);
    }

}
