public class Sorcerer extends Hero{

    Sorcerer(String name, double mana, double strength, double dexterity, double agility, int money, int exp) {
        super(name , mana, strength, dexterity, agility, money, exp);
    }

    @Override
    public void skillUpgrade() {
        strength *= 1.05;
    }

    @Override
    public void skillUpgrade2() {
        dexterity *= 1.1;
        agility *= 1.1;
        dodge = agility * 0.002;
    }
}
