public class Weapon extends Item {
    private double damage;
    private int hands;
    public Weapon(String name, int price, int requiredLevel) {
        super(name, price, requiredLevel);
    }

    public Weapon(String name, int price, int requiredLevel, double damage, int hands) {
        this(name, price, requiredLevel);
        this.damage = damage;
        this.hands = hands;
    }

    public String toString() {
        return "Weapon: " + name + ", price is " + price + ", required level is " + requiredLevel + ", damage is " +
                damage + ", required " + hands +" hands";
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public int getHands() {
        return hands;
    }

    public void setHands(int hands) {
        this.hands = hands;
    }

}
