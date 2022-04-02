public class IceSpell extends Spell{

    IceSpell(String name, int price, int reqLevel, double baseDamage, double mana) {
        super(name, price, reqLevel, baseDamage, mana);
    }

    public String toString() {
        return "IceSpell: " + name + ", price is " + price + ", required level is " + requiredLevel + ", damage is " +
                damage + ", required " + mana +" mana";
    }

    @Override
    public void character(Monster monster) {
        monster.setDamage(monster.getDamage() * 0.9);
        System.out.println(monster.getName() + "'s base damage was weakened.");
    }
}
