public class Warrior extends Hero{

    Warrior(String name, double mana, double strength, double dexterity, double agility, int money, int exp) {
        super(name , mana, strength, dexterity, agility, money, exp);
    }

    @Override
    public void skillUpgrade() {
        dexterity *= 1.05;
    }

    @Override
    public void skillUpgrade2() {
        strength *= 1.1;
        agility *= 1.1;
        dodge = agility * 0.002;
    }
}
