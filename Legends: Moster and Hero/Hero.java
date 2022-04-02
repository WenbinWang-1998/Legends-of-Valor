import java.util.ArrayList;
import java.util.List;

// The hero class includes all the property of hero
public abstract class Hero {
//Name/mana/strength/agility/dexterity/starting money/starting experience
    protected double defense;
    protected String name;
    protected double mana;
    protected double strength;
    protected double agility;
    protected double dexterity;
    protected int startMoney;
    protected int startExperience;
    protected int level;
    protected double HP;
    protected double limitHP; // The upper limit of HP of a hero.
    protected int experienceNeeded; // How many experience needed to level up.
    protected boolean state; // The hero's state, if false, faint.
    protected double dodge;
    protected Weapon weapon;
    protected Armor armor;
    protected List<Spell> spells;
    protected List<Potion> potions;
    protected String heroType;

    protected Inventory inventory; // unequipped weapons and armors are stored in the inventory

    public Hero(String name, double mana, double strength, double dexterity, double agility, int startMoney, int startExperience) {
        this.name = name;
        this.level = 1;
        this.limitHP = 100.0;
        this.HP = 100.0;
        this.mana = mana;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.defense = 0;
        this.startMoney = startMoney;
        this.startExperience = startExperience;
        this.experienceNeeded = 10;
        this.state = true;
        this.dodge = agility * 0.002;
        this.weapon = null;
        this.armor = null;
        this.spells = new ArrayList<Spell>();
        this.potions = new ArrayList<Potion>();
        this.inventory = new Inventory();
        this.heroType = getClass().toString().split("\s")[1];
    }
    
    public abstract void skillUpgrade();
    //Because heros have their own specific skill, which is better than others.
    public abstract void skillUpgrade2();

    public boolean ifLevelUp() {
        if(startExperience >= experienceNeeded) {
            updateData();
            return true;
        }
        else{
            return false;
        }
    }

    public void getExp(int experience) {
        startExperience += experience;
    }

    // each turn, the hero can recover hp and mana
    public void recover() {
        if(HP < limitHP){
            if(HP + 0.1*limitHP < limitHP){
                HP += 0.1 * limitHP;
            }
            else{
                HP = limitHP;
            }
        }
        mana += 0.1 * mana;
    }


    public void reborn() {
        state = true;
        HP = limitHP / 2;
    }


    public double dealDamage() {
        if(weapon == null)
            return 0.05 * strength;
        else
            return 0.05 * (strength + weapon.getDamage());
    }


    public void getEquipment(Item item) {
        if(item instanceof Spell) {
            spells.add((Spell) item);
            System.out.println(name + " got a spell called " + item + ".");
        }
        else if(item instanceof Potion) {
            potions.add((Potion) item);
            System.out.println(name + " got a potion called " + item.getName() + ".");
        }
        else {
            inventory.add(item);
            System.out.println(name + " got " + item + ".");
        }
    }

    public void swapWeapon(Weapon weapon) {
        if(weapon == null) return;
        Weapon old = this.weapon;
        inventory.add(this.weapon);
        this.weapon = weapon;
        System.out.println(name + " change the weapon from " + old.getName() + " to " + weapon.getName() + ".");
    }


    public void swapArmor(Armor armor) {
        if(armor == null) return;

        Armor old = this.armor;
        inventory.add(this.armor);
        this.armor = armor;
        System.out.println(name + " change the armor from " + old.getName() + " to " + armor.getName() + ".");
    }

    public void removeItem(String name) {
        if(weapon != null && weapon.getName().equals(name)) {
            weapon = null;
            return;
        }
        if(armor != null && armor.getName().equals(name)) {
            armor = null;
            return;
        }
        if(spells.size() > 0){
            for(Spell s: spells) {
                if(s.getName().equals(name)) {
                    spells.remove(s);
                    return;
                }
            }
        }
        if(potions.size() > 0){
            for(Potion p: potions) {
                if(p.getName().equals(name)) {
                    potions.remove(p);
                    return;
                }
            }
        }
        if(inventory.getSize() > 0){
            for(Item i: inventory.getItems()) {
                if(i.getName().equals(name)) {
                    inventory.removeItem(i);
                    return;
                }
            }
        }
    }



    public void spellAttack(int i, Monster monster) {
        double temp = spells.get(i).getMana();
        if(mana < temp)
            System.out.println(name + " has " + mana + " mana, " + "using this spell needs "+ temp);
        else {
            monster.underAttack(spells.get(i).getDamage() * (1 + dexterity / 10000.0), this);
            spells.get(i).character(monster);
            mana -= temp;
        }
    }

    public void underAttack(double damage, Monster monster) {
        // If dodge larger than a random number between 0 and 1.
        if(dodge >= Math.random()) {
            System.out.println(name + " dodged the attack from " + monster.getName());
            return;
        }
        damage = calculateDamage(damage);
        HP -= damage;
        if(HP <= 0) {
            // This hero will faint.
            state = false;
            System.out.println(this.name + " faint.");
        }
        else{
            System.out.println(monster.getName() + " dealt " + damage + " damage to " + this.name);
        }

    }


    public void usePotion(int i) {
        double increase = potions.get(i).getValue();
        potions.remove(i);
        List<String> attributes = potions.get(i).getAttributes();
        increaseSkill(attributes, increase);
        System.out.println(name + " has used potion.");
    }


    public void usePotion2(String name) {
        for(int i = 0; i<potions.size(); i++) {
            if(potions.get(i).getName().equals(name)){
                usePotion(i);
                break;
            }
        }
    }


    public void showAllItem() {
        StringBuffer sb = new StringBuffer();
        if(weapon != null){
            sb.append("Weapon: " + weapon.getName() + "  ");
        }
        if(armor != null){
            sb.append("Armor: " + armor.getName() + "  ");
        }

        if(potions.size() != 0) {
            sb.append("Potions:");
            for(Potion p: potions){
                sb.append(" " + p.getName());
            }
        }
        if(spells.size() != 0) {
            sb.append("Spells:");
            for(Spell s: spells){
                sb.append(" " + s.getName());
            }
        }
        sb.append("\n");
        if(inventory.getSize() != 0) {
            sb.append("Inventory:");
            for(Item e: inventory.getItems()){
                sb.append(e.getName() + "  ");
            }
        }
        if(sb.length() == 0){
            System.out.println("No items.");
        }
        else{
            System.out.println(sb);
        }
    }


    public Item getItem(String name) {
        if(inventory.getSize() > 0){
            for(Item item : inventory.getItems()){
                if(item.getName().equals(name))
                    return item;
            }
        }
        if(weapon != null && weapon.getName().equals(name)){
            return weapon;
        }

        if(armor != null && armor.getName().equals(name)){
            return armor;
        }
        if(spells.size() > 0){
            for(Spell s: spells){
                if(s.getName().equals(name))
                    return s;
            }
        }
        if(potions.size() > 0){
            for(Potion p: potions){
                if(p.getName().equals(name))
                    return p;
            }
        }
        return null;
    }


    public List<Item> getAllItem() {
        List<Item> list = new ArrayList<>();
        if(weapon != null)  list.add(weapon);
        if(armor != null)   list.add(armor);
        if(spells.size() != 0){
            for(Spell spell: spells){
                list.add(spell);
            }
        }

        if(potions.size() != 0){
            for(Potion potion: potions){
                list.add(potion);
            }
        }
        if(inventory.getSize() != 0){
            for(Item item : inventory.getItems()){
                list.add(item);
            }
        }
        return list;
    }


    public String showInfoPeace() {
        System.out.println();
        return name + ":" + "\nType: " + heroType  + "\nMana: " + mana + "  " + "\nStrength: " + strength + "  " + "\nAgility: " + agility +
                "\nDexterity: " + dexterity + "  " + "\nMoney: " + startMoney  + "\nExperience: " + startExperience + "  " +
                "\nLevel: " + level + "  " + "\nHP: " + HP;
    }

    // Show the information during fight.
    public String showInfoFight() {
        String result = "Name: " + name + "\n" + "Level: " + level + "  " + "HP: " + HP + "  " + "Mana: " + mana + "\n";
        if(weapon == null)  result += "Weapon: None";
        else result += "Weapon: " + weapon.getName() + "  ";
        if(armor == null) result += "Armor: None";
        else result += "Armor: " + armor.getName();
        return result;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getAgility() {
        return agility;
    }

    public void setAgility(double agility) {
        this.agility = agility;
    }

    public double getDexterity() {
        return dexterity;
    }

    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }

    public int getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(int startMoney) {
        this.startMoney = startMoney;
    }

    public int getStartExperience() {
        return startExperience;
    }

    public void setStartExperience(int startExperience) {
        this.startExperience = startExperience;
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

    public double getLimitHP() {
        return limitHP;
    }

    public void setLimitHP(double limitHP) {
        this.limitHP = limitHP;
    }

    public int getExperienceNeeded() {
        return experienceNeeded;
    }

    public void setExperienceNeeded(int experienceNeeded) {
        this.experienceNeeded = experienceNeeded;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public double getDodge() {
        return dodge;
    }

    public void setDodge(double dodge) {
        this.dodge = dodge;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public List<Potion> getPotions() {
        return potions;
    }

    public void setPotions(List<Potion> potions) {
        this.potions = potions;
    }

    public String getHeroType() {
        return heroType;
    }

    public void setHeroType(String heroType) {
        this.heroType = heroType;
    }

    public List<Item> getInventory() {return inventory.getItems();}

    public boolean getState() {
        return state;
    }

    // Update all the data that this hero after level up
    private void updateData(){
        while(startExperience >= experienceNeeded) {
            skillUpgrade();
            skillUpgrade2();
            level++;
            startExperience -= experienceNeeded;
            experienceNeeded = level * 10;
            mana = 1.1 * mana;
        }
        HP = 100 * level;
        limitHP = 100 * level;
    }
    private void removeInventory(String name){
        if(inventory.getSize() > 0){
            for(Item i: inventory.getItems()) {
                if(i.getName().equals(name)) {
                    inventory.removeItem(i);
                    return;
                }
            }
        }
    }

    private double calculateDamage(double damage){
        if(armor != null) {
            if(damage - defense - armor.getDamageTaken() >= 0){
                return damage - defense - armor.getDamageTaken();
            }
        }
        else{
            if(damage - defense >= 0){
                return damage - defense;
            }
        }
        return 0;
    }

    private void increaseSkill(List<String> attributes, double increase){
        for(String attribute: attributes) {
            switch (attribute) {
                case "Health":
                    HP += increase;
                    break;
                case "Mana":
                    mana += increase;
                    break;
                case "Defense":
                    defense += increase;
                    break;
                case "Strength":
                    strength += increase;
                    break;
                case "Dexterity":
                    dexterity += increase;
                    break;
                case "Agility":
                    agility += increase;
                    dodge = agility * 0.002;
                    break;
            }
        }
    }

}
