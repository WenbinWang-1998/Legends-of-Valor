public class Paladin extends Hero{

    Paladin(String name, double mana, double strength, double dexterity, double agility, int money, int exp) {
        super(name , mana, strength, dexterity, agility, money, exp);
    }

    @Override
    public void skillUpgrade() {
        agility *= 1.05;
        dodge = agility * 0.002;
    }

    @Override
    public void skillUpgrade2() {
        strength *= 1.1;
        dexterity*= 1.1;
    }

}
