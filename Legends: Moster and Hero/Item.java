// This class is the super class of all the item: weapons, armors, spells and potions
public abstract class Item {

    protected String name;
    protected int price;
    protected int requiredLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public Item(String name, int price, int requiredLevel) {
        this.name = name;
        this.price = price;
        this.requiredLevel = requiredLevel;
    }

    // Default required level is 1.
    public Item(String name, int price){
        this.name = name;
        this.price = price;
        this.requiredLevel = 1;
    }

}
