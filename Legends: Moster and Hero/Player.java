import java.util.*;

public class Player {

    private String icon = "#";
    private List<Hero> heroes = new ArrayList<>();
    private int size;
    private List<Warrior> warriorList = Helper.scanTextFile(System.getProperty("user.dir") + "/Legends_Monsters_and_Heroes/Warriors.txt");
    private List<Sorcerer> sorcererList = Helper.scanTextFile(System.getProperty("user.dir") + "/Legends_Monsters_and_Heroes/Sorcerers.txt");
    private List<Paladin> paladinList = Helper.scanTextFile(System.getProperty("user.dir") + "/Legends_Monsters_and_Heroes/Paladins.txt");

    Set<Integer> set = new HashSet<>();

    public Player(int size) {
        this.size = size;
        set.add(0);
        set.add(1);
        set.add(2);
        RandomPick(size);
    }

    // Random pick specific number of heroes.
    public void RandomPick(int n){
        Random random = new Random();
        if(n == 1){
            int temp = random.nextInt(3);
            helper(temp);
        }
        else if(n == 2){
            int temp = random.nextInt(3);
            set.remove(temp);
            for(int i : set){
                helper(i);
            }
        }
        else{
            for(int i : set){
                helper(i);
            }
        }
    }
    private void helper(int n){
        Random random = new Random();
        switch (n){
            case 0:
                Warrior warrior = warriorList.get(random.nextInt(warriorList.size()));
                heroes.add(warrior);
                System.out.println(warrior.getName() + " joined your team.");
                break;
            case 1:
                Sorcerer sorcerer = sorcererList.get(random.nextInt(sorcererList.size()));
                heroes.add(sorcerer);
                System.out.println(sorcerer.getName() + " joined your team.");
                break;
            case 2:
                Paladin paladin = paladinList.get(random.nextInt(paladinList.size()));
                heroes.add(paladin);
                System.out.println(paladin.getName() + " joined your team.");
                break;
        }
    }



    public void move(String s, Map map) {
        if(s.equals("W") || s.equals("w")) {
            if(map.getPlayerPos()[1] - 1 < 0) {
                System.out.println("Cannot go up!!!");
                return;
            }
            else {
                update(map.getPlayerPos()[0], map.getPlayerPos()[1] - 1, map);
            }
        }
        else if (s.equals("S") || s.equals("s")) {
            if(map.getPlayerPos()[1] + 1 >= map.getLength()) {
                System.out.println("Cannot go down!!!");
                return;
            }
            else {
                update(map.getPlayerPos()[0], map.getPlayerPos()[1] + 1, map);
            }
        }
        else if(s.equals("A") || s.equals("a")) {
            if(map.getPlayerPos()[0] - 1 < 0) {
                System.out.println("Cannot go left!!!");
                return;
            }
            else {
                update(map.getPlayerPos()[0] - 1, map.getPlayerPos()[1], map);
            }
        }
        else if(s.equals("D") || s.equals("d")) {
            if(map.getPlayerPos()[0] + 1 >= map.getLength()) {
                System.out.println("Cannot go right!!!");
                return;
            }
            else {
                update(map.getPlayerPos()[0] + 1, map.getPlayerPos()[1], map);
            }
        }
    }


    public void update(int row, int column, Map map) {
        Cell c = map.getCell(row, column);
        if(c.getClass() == Inaccessible.class){
            System.out.println("Inaccessible!");
        }
        if(c.getClass() == Market.class){
            map.update(row, column);
            ((Market) c).enterToMarket(this);
        }
        if(c.getClass() == CommonSpace.class){
            map.update(row, column);
            if(((CommonSpace) c).getFight()){
                Fight temp = new Fight(heroes);
                temp.start();
            }
        }
    }


    public void showHeros() {
        System.out.println("Heroes: ");
        for(int i = 0; i< heroes.size(); i++) {
            System.out.println(i + ": " + heroes.get(i).getName());
        }
        System.out.println();
    }

    public void showPeace() {
        for(Hero hero: heroes) {
            System.out.println(hero.showInfoPeace());
        }
    }


    public List<Hero> getHeroes() {return heroes;}

}
