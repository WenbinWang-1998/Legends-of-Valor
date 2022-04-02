public class Monster {
    protected String name;
    protected int level;
    protected double HP;
    protected double damage;
    protected double defense;
    protected double dodge;
    protected boolean state;

    public Monster(String name, int level, double damage, double defense, double dodge) {
        this.name = name;
        this.level = level;
        this.HP = 100.0 * level;
        this.damage = damage;
        this.defense = defense;
        this.dodge = dodge;
        this.state = true;
    }

    // The monster attacked by hero.
    public void underAttack(double damage, Hero hero) {
        if(dodge * 0.01 >= Math.random()) {
            System.out.println(name + " dodged the attack from " + hero.getName());
            return;
        }
        damage = calculateDamage(damage);
        HP -= damage;
        if(HP <= 0) {
            state = false;
            System.out.println(name + " died.");
        }
        else {
            System.out.println(hero.getName() + " dealt " + damage + " damage to " + name);
        }
    }

    private double calculateDamage(double damage){
        if(damage - defense * 0.02 <= 0){
            return 0;
        }
        else{
            return damage - defense*0.02;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getDodge() {
        return dodge;
    }

    public void setDodge(double dodge) {
        this.dodge = dodge;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String toString() {
        return "Name: " + name + "\n" + "Level: " + level + "  " +  "HP: " + HP + "\n" + "Defense: " + defense + "  " + "Damage: " + damage;
    }

}
