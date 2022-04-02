public class LightningSpell extends Spell{

    LightningSpell(String name, int price, int reqLevel, double baseDamage, double mana) {
        super(name, price, reqLevel, baseDamage, mana);
    }

    public String toString() {
        return "LightingSpell: " + name + ", price is " + price + ", required level is " + requiredLevel + ", damage is " +
                damage + ", required " + mana +" mana";
    }

    @Override
    public void character(Monster monster) {
        monster.setDodge(monster.getDodge() * 0.9);
        System.out.println(monster.getName() + "'s dodge chance was weakened.");
    }
}
