public class Armor extends Item {

    private double damageTaken;

    public Armor(String name, int price, int requiredLevel) {
        super(name, price, requiredLevel);
    }

    Armor(String name, int price, int requiredLevel, double damageTaken) {
        this(name, price, requiredLevel);
        this.damageTaken = damageTaken;
    }

    public String toString() {
        return "Armor: " + name + ", price is " + price + ", required level is " + requiredLevel + ", it can take damage: " +
                damageTaken;
    }

    public double getDamageTaken() {
        return damageTaken;
    }

    public void setDamageTaken(double damageTaken) {
        this.damageTaken = damageTaken;
    }


}
