public class FireSpell extends Spell{

    FireSpell(String name, int price, int reqLevel, double damage, double mana) {
        super(name, price, reqLevel, damage, mana);
    }

    public String toString() {
        return "FireSpell: " + name + ", price is " + price + ", required level is " + requiredLevel + ", damage is " +
                damage + ", required " + mana +" mana";
    }

    @Override
    public void character(Monster monster) {
        monster.setDefense(monster.getDefense() * 0.9);
    }

}
