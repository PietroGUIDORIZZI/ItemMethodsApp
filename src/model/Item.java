package model;


public class Item {

    private String name;
    private String description;
    private Room room;
    private Category category;
    private int quantity;

    public Item(String name, String description, Room room, Category category, int quantity) {
        this.name =
                name != null && name.isBlank()
                ? name : "Unnamed";

        this.description =
                description != null && description.isBlank()
                ? description
                : "Sem descrição";

        this.room =
                room != null
                ? room
                : Room.NOT_ALLOCATED;

        this.category =
                category != null
                ? category
                : Category.NOT_CATEGORIZED;

        this.quantity = quantity;

    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isRunningOut() {
        return quantity <= 2;
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

    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
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

