package model;


public class Item {

    private String name;
    private String description;
    private Room room;
    private Category category;
    private int quantity;

    public Item(String name, String description, Room room, Category category, int quantity) {
        this.name = name;

        this.description =
                description != null
                ? description
                : "descrição";

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

    public void moveToRoom(Room newRoom) {
        this.room = newRoom;
    }

    public void updateQuantity(int newQuantity) {
        if(newQuantity < 0 ){
            return;
        }
        this.quantity = newQuantity;
    }

    public void use(int n) {
        if (n <= 0) {
            return;
        }

        if (n > quantity) {
            this.quantity = 0;
            return;
        }

        quantity -= n;
    }

    public void restock(int n) {
        if (n <= 0) {
            return;
        }
        quantity += n;
    }

    @Override
    public String toString(){
        return name + " (" + quantity + ")";
    }

}

