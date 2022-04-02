import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// This class is to implement how the heroes and monsters fight.
public class Fight {
    private boolean winner; // true: Heroes  false: monsters
    private List<Hero> heroes;
    private List<Monster> monsters;

    public Fight(List<Hero> heroes) {
        this.heroes = heroes;
        this.monsters = new ArrayList<Monster>();
    }

    private void heroTurn(){
        for(int i = 0; i<heroes.size(); i++) {
            if(heroes.get(i).getState()) {
                System.out.println(heroes.get(i).getName() + "'s turn.");
                fightOption(heroes.get(i));
            }
            if(isEnd()){
                return;
            }
        }
    }
// Start fight.
    public void start() {
        fillMonster();
        while(!isEnd()) {
            showFight();
            // Hero's always go first;
            for(int i = 0; i<heroes.size(); i++) {
                if(heroes.get(i).getState()) {
                    System.out.println(heroes.get(i).getName() + "'s turn.");
                    fightOption(heroes.get(i));
                }
            }
            for(Monster monster: monsters) {
                if(monster.getState()) {
                    System.out.println(monster.getName() + "'s turn.");
                    heroes.get(attackHero(monster)).underAttack(monster.getDamage(), monster);
                }

            }
            for(int i = 0;i <heroes.size(); i++){
                if(heroes.get(i).getState()){
                    heroes.get(i).recover();
                }
            }

        }
        System.out.println("Fight over.");
        if(winner == true) {
            System.out.println("You win!");
            int money = monsters.get(0).getLevel() * 100;
            for(int i = 0; i<heroes.size(); i++) {
                if(heroes.get(i).getState()) {
                    heroes.get(i).setStartMoney(heroes.get(i).getStartMoney() + money);
                    heroes.get(i).getExp(2);
                }
                else{
                    heroes.get(i).reborn();
                }
            }
        }
        else {
            System.out.println("You lose!");
            for(int i = 0; i<heroes.size(); i++) {
                heroes.get(i).reborn();
                heroes.get(i).setStartMoney(heroes.get(i).getStartMoney() / 2);
            }
        }

    }

    // Determine if the battle is end
    public boolean isEnd() {
        boolean end1 = true;
        boolean end2 = true;
        for(Monster monster: monsters){
            if(monster.getState()) {
                end1 = false;
                break;
            }
        }
        for(Hero hero: heroes){
            if(hero.getState()) {
                end2 = false;
                break;
            }
        }

        if(end1){
            winner = true;
        }
        if(end2){
            winner = false;
        }

        return end1||end2;
    }

    public void fightOption(Hero hero) {
        showOption(hero);
        Scanner input = new Scanner(System.in);
        String s1 = "[12345]";
        String s2 = "[0~" + (hero.getSpells().size()-1) + "]";
        String s3 = "[0~" + (hero.getPotions().size()-1) + "]";
        String str = null;
        while((str = input.next()) != null) {
            if(!str.matches(s1)) {
                System.out.println("Please input 1/2/3/4/5.");
                continue;
            }
            else {
                if(str.equals("1")) {
                    monsters.get(attackMonster(hero)).underAttack(hero.dealDamage(), hero);
                    break;
                }
                if(str.equals("2")) {
                    if(hero.getSpells().size() == 0) {
                        System.out.println(hero.getName() + " does not have spells.");
                        continue;
                    }
                    System.out.println("Choose spell:( Mana :"+ hero.getMana() + ")");
                    for(int i = 0; i < hero.getSpells().size(); i++){
                        System.out.println(i + ": " + hero.getSpells().get(i));
                    }
                    String temp = null;
                    while((temp = input.next()) != null) {
                        if(temp.matches(s2)) {
                            hero.spellAttack(Integer.parseInt(temp), monsters.get(attackMonster(hero)));
                            break;
                        }
                        else{
                            System.out.println("Unvalid. Please input a valid number. From 0" + " to "+ (hero.getSpells().size() - 1));
                        }
                    }
                    break;
                }

                if(str.equals("3")) {
                    if(hero.getPotions().size() == 0) {
                        System.out.println(hero.getName() + " does not have potion.");
                        continue;
                    }
                    System.out.println("Choose potion:");
                    for(int i = 0; i < hero.getPotions().size(); i++){
                        System.out.println(i + ": " + hero.getPotions().get(i));
                    }
                    String temp = null;
                    while((temp = input.next()) != null) {
                        if(temp.matches(s3)) {
                            hero.usePotion(Integer.parseInt(temp));
                            break;
                        }
                        else
                            System.out.println("Unvalid. Please input a valid number. From 0" + " to "+ (hero.getPotions().size() - 1));
                    }
                    break;
                }

                if(str.equals("4")) {
                    List<Item> inventory = hero.getInventory();
                    List<Item> weaponsAndArmors = new ArrayList<>();
                    if(inventory.size() == 0){
                        System.out.println("Inventory is Empty");
                        continue;
                    }
                    for(Item item: inventory) {
                        if(item instanceof Weapon || item instanceof Armor){
                            weaponsAndArmors.add(item);
                        }
                    }

                    if(weaponsAndArmors.size() == 0){
                        System.out.println("No Weapon or Armor.");
                        continue;
                    }

                    System.out.println("Please input the name of armor or weapon");
                    for(int i = 0; i < weaponsAndArmors.size(); i++){
                        System.out.println(weaponsAndArmors.get(i).getName());
                    }

                    String nameOfEquip = null;
                    while((nameOfEquip = input.next()) != null) {
                        boolean flag = false;
                        for(Item i: weaponsAndArmors) {
                            if (i.getName().equals(nameOfEquip)) {
                                if(i instanceof Weapon){
                                    hero.swapWeapon((Weapon) i);
                                }
                                if(i instanceof Armor){
                                    hero.swapArmor((Armor) i);
                                }
                                flag = true;
                                break;
                            }
                        }
                        if(!flag){
                            System.out.println("Invalid. Input again!");
                        }
                        else{
                            break;
                        }
                    }
                    break;
                }
                if(str.equals("5")) {
                    showInfo();
                    showOption(hero);
                    continue;
                }
            }
        }
    }

    private void showOption(Hero hero){
        System.out.println("1: Regular attack");
        System.out.println("2: Cast a spell");
        System.out.println("3: Use a potion");
        System.out.println("4: Change armor/weapon");
        System.out.println("5: Show information");
        System.out.println(hero.getName() + "'s Turn!!!");
    }


    public void showFight() {
        System.out.println("---------------------------------------------------------------");
        for(int i = 0; i<heroes.size(); i++){
            drawHvsM(heroes.get(i).getName(), monsters.get(i).getName());
        }
        System.out.println("---------------------------------------------------------------");
    }


    //Show the information during fight.
    public void showInfo() {
        System.out.println("---------------------------------------------------------------");
        System.out.println();
        System.out.println("Hero: ");
        System.out.println();
        for(int i = 0; i < heroes.size(); i++){
            System.out.println();
            System.out.println(heroes.get(i).showInfoFight());
        }
        System.out.println();
        System.out.println();
        System.out.println("Monster: ");
        System.out.println();
        for(int i = 0; i < monsters.size(); i++){
            System.out.println();
            System.out.println(monsters.get(i));
        }
        System.out.println("---------------------------------------------------------------");
    }

// Draw the hero vs monster
    public void drawHvsM(String heroName, String monsterName) {
        System.out.println();
        System.out.println();
        System.out.print("            ");
        System.out.print(heroName);
        System.out.print("                  ");
        System.out.print(monsterName);
        System.out.println();
        System.out.println();
    }

    private int attackMonster(Hero hero) {
        int m = heroes.indexOf(hero);
        if(monsters.get(m).getState()){
            return m;
        }
        else {
            for(int i = 0; i<monsters.size(); i++) {
                if(monsters.get(i).getState()) {
                    m = i;
                    break;
                }
            }
        }
        return m;
    }

    private int attackHero(Monster monster) {
        int h = monsters.indexOf(monster);
        if(heroes.get(h).getState())
            return h;
        else {
            for(int i = 0; i<heroes.size(); i++) {
                if(heroes.get(i).getState()) {
                    h = i;
                    break;
                }
            }
        }
        return h;
    }

    private void fillMonster() {
        List<Monster> list = getRandomMonsters();
        Random rd = new Random();
        for(int i = 0; i<heroes.size(); i++)
            monsters.add(list.get(rd.nextInt(list.size())));
    }

    private List<Monster> getRandomMonsters(){
        int max = Integer.MIN_VALUE;
        for(Hero hero: heroes) {
            if(hero.getLevel() > max){
                max = hero.getLevel();
            }
        }
        int level = max;
        List<Monster> list = new ArrayList<>();
        for(Monster monster: (List<Monster>) Helper.scanTextFile(System.getProperty("user.dir") + "/Legends_Monsters_and_Heroes/Dragons.txt")){
            if(monster.getLevel() == level)
                list.add(monster);
        }
        for(Monster monster: (List<Monster>) Helper.scanTextFile(System.getProperty("user.dir") + "/Legends_Monsters_and_Heroes/Exoskeletons.txt")){
            if(monster.getLevel() == level)
                list.add(monster);
        }
        for(Monster monster: (List<Monster>) Helper.scanTextFile(System.getProperty("user.dir") + "/Legends_Monsters_and_Heroes/Spirits.txt")){
            if(monster.getLevel() == level)
                list.add(monster);
        }
        return list;
    }

}
