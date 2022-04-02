public abstract class Spell extends Item {

    protected double damage;
    protected double mana;
    public double getDamage() {
        return damage;
    }

    public double getMana() {
        return mana;
    }

    public Spell(String name, int price, int requiredLevel) {
        super(name, price, requiredLevel);
    }

    public Spell(String name, int price, int requiredLevel, double damage, double mana) {
        this(name, price, requiredLevel);
        this.damage = damage;
        this.mana = mana;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public abstract void character(Monster monster);

    @Override
    public String toString() {
        return name;
    }
}
