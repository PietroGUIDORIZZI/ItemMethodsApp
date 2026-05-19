package model;


public class Item {

    private final String name;
    private String description;
    private Room room;
    private Category category;
    private int quantity;

    public Item(String name, String description, Room room, Category category, int quantity) {

        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Invalid item name");
        }
        this.name = name.trim();

        this.description =
                description != null && description.isBlank()
                ? description.trim()
                : "Sem descrição";

        this.room =
                room != null
                ? room
                : Room.NOT_ALLOCATED;

        this.category =
                category != null
                ? category
                : Category.NOT_CATEGORIZED;

        this.quantity = Math.max(quantity, 0);

    }

    public boolean isRunningOut() {
        return quantity <= 2;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity>=0) {
            this.quantity = quantity;
        }
        throw new IllegalArgumentException("Invalid quantity.");

    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDescription(String description) {
        if ( description==null || description.isBlank()) {
            return;
        }
        this.description = description.trim();
    }

    @Override
    public String toString(){

        return """
            -------------------------
            Name:        %s
            Description: %s
            Room:        %s
            Category:    %s
            Quantity:    %d
            -------------------------
            """.formatted(
                name,
                description,
                room,
                category,
                quantity
        );
    }

}

