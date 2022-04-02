import java.util.*;

public class Market extends Cell{
    private List<Weapon> weapons;
    private List<Armor> armors;
    private List<Spell> spells;
    private List<Potion> potions;
    private List<Item> items;

    public Market(List<Weapon> weapons, List<Armor> armors, List<Spell> spells, List<Potion> potions) {
        super("M" );
        this.weapons = weapons;
        this.armors = armors;
        this.spells = spells;
        this.potions = potions;
        items = new ArrayList<Item>();
        items.addAll(weapons);
        items.addAll(armors);
        items.addAll(spells);
        items.addAll(potions);
    }


    public void showOption() {
        System.out.println("1: Buy items");
        System.out.println("2: Sell items");
        System.out.println("3: Quit");
    }


    public void showItem() {
        int i = 1;
        System.out.println("-------------------------------------------------");
        System.out.println("Weapon:");
        for(Weapon weapon: weapons) {
            System.out.println(i + "   " + weapon);
            i++;
        }
        System.out.println("Armor:");
        for(Armor armor: armors) {
            System.out.println(i + "   " + armor);
            i++;
        }
        System.out.println("Spells:");
        for(Spell spell: spells) {
            System.out.println(i + "   " + spell);
            i++;
        }
        System.out.println("Potions:");
        for(Potion potion: potions) {
            System.out.println(i + "   " + potion);
            i++;
        }
        System.out.println("-------------------------------------------------");
    }


    public void enterToMarket(Player player) {
        showItem();
        showOption();
        Scanner scanner = new Scanner(System.in);
        String s1 = "[123]";
        String s2 = "[0-" + (player.getHeroes().size()-1) + "]-" + "[0-9]\\d{0,1}";
        String next = null;
        while((next = scanner.next()) != null) {
            if(!next.matches(s1)) {
                System.out.println("Please input 1/2/3");
                continue;
            }
            else {
                if(next.equals("1")) {
                    player.showHeros();
//                    System.out.println("Then input the item you want");
                    System.out.println("Input the hero number and item number, using - between them.  For example : 1-12" );
                    String temp = null;
                    while ((temp = scanner.next()) != null) {
                        if(!temp.matches(s2)) {
                            System.out.println("Unvalid input. Please try again.");
                            continue;
                        }
                        else {
                            String[] num = temp.split("-");
                            int hero = Integer.parseInt(num[0]);
                            int item = Integer.parseInt(num[1]);
                            if(item - 1 >= items.size()) {
                                System.out.println("No this item, try again");
                                continue;
                            }
                            buyItem(player.getHeroes().get(hero), item - 1);
                            break;
                        }
                    }
                    showItem();
                    showOption();
                    continue;
                }
                if(next.equals("2")) {
                    List<Hero> heroList = player.getHeroes();
                    List<Item> items = new ArrayList<Item>();
                    for(Hero hero: heroList){
                        items.addAll(hero.getAllItem());
                    }
                    if(items.size() == 0) {
                        System.out.println("No items can be sold");
                        continue;
                    }
                    for(int i = 0; i<heroList.size(); i++) {
                        System.out.print((i + 1) + " " + heroList.get(i).getName() + " :  ");
                        heroList.get(i).showAllItem();
                    }
                    System.out.println("Please input the name of item");
                    String name = scanner.next();
                    while(!sellItem(player, name)) {
                        System.out.println("Invalid input. Please input again.");
                        name = scanner.next();
                    }
                    showItem();
                    showOption();
                    continue;
                }
                if(next.equals("3")) {
                    break;
                }
            }
        }

    }

    public void buyItem(Hero hero, int index) {
        Item item = items.get(index);
        if (hero.getLevel() < item.getRequiredLevel()) {
            System.out.println("This item's required level is higher than your current level.");
            return;
        }
        if(hero.getStartMoney() < item.getPrice()){
            System.out.println("No enough money.");
        }
        else {
            if(item instanceof Weapon){
                weapons.remove(item);
            }
            else if(item instanceof Armor){
                armors.remove(item);
            }

            else if(item instanceof Spell){
                spells.remove(item);
            }
            else if(item instanceof Potion){
                potions.remove(item);
            }
            items.remove(item);
            System.out.println(hero.showInfoPeace());
            hero.getEquipment(item);
            hero.setStartMoney(hero.getStartMoney() - item.getPrice());
        }
    }

    public boolean sellItem(Player player, String name) {
        List<Hero> list = player.getHeroes();
        Item item = null;
        Hero hero = null;
        for(int i = 0; i<list.size(); i++) {
            hero = list.get(i);
            item = hero.getItem(name);
            if(item != null){
                break;
            }
        }
        if(item != null) {
            if(item instanceof Weapon){
                weapons.add((Weapon) item);
            }
            else if(item instanceof Armor){
                armors.add((Armor) item);
            }
            else if(item instanceof Spell){
                spells.add((Spell) item);
            }
            else if(item instanceof Potion){
                potions.add((Potion) item);
            }
            List<Item> items = new ArrayList<Item>();
            items.addAll(weapons);
            items.addAll(armors);
            items.addAll(spells);
            items.addAll(potions);
            this.items = items;
            hero.setStartMoney(hero.getStartMoney() + item.getPrice() / 2);
            hero.removeItem(item.getName());
            System.out.println(hero.showInfoPeace());
            return true;
        }
        return false;
    }

}
