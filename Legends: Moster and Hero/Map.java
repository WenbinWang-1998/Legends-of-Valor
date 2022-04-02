import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    private int length;
    private int[] playerPos;
    private Cell[][] cells;

    public Map(int n) {
        length = n;
        playerPos = new int[2];
        cells = new Cell[n][n];
        initializeMap();
        initialPlayerPos();
    }

    public void initializeMap() {
        // number of inaccessible
        int a = (int) (length * length * 0.2);
        // number of market
        int b = (int) (length * length * 0.3);
        List<Integer> list = new ArrayList<>();
        Random rd = new Random();
        // Adding to numList until it reach to the inaccessible + market
        while(list.size() < a + b) {
            int num = rd.nextInt(length*length);
            if(!list.contains(num)) {
                list.add(num);
            }
        }
        for(int i = 0; i< length*length; i++) {
            int row = i / length;
            int column = i % length;
            if(!list.contains(i)){
                cells[row][column] = new CommonSpace();
            }
        }
        for(int i = 0; i<list.size(); i++) {
            int index = list.get(i);
            int row = index / length;
            int column = index % length;
            String prefix = System.getProperty("user.dir");
            if(i < b){
                List<Spell> spellList = new ArrayList<>();
                spellList.addAll(Helper.scanTextFile(prefix + "/Legends_Monsters_and_Heroes/FireSpells.txt"));
                spellList.addAll(Helper.scanTextFile(prefix + "/Legends_Monsters_and_Heroes/IceSpells.txt"));
                spellList.addAll(Helper.scanTextFile(prefix + "/Legends_Monsters_and_Heroes/LightningSpells.txt"));
                cells[row][column] = new Market(Helper.scanTextFile(prefix + "/Legends_Monsters_and_Heroes/Weaponry.txt"), Helper.scanTextFile(prefix + "/Legends_Monsters_and_Heroes/Armory.txt"), spellList, Helper.scanTextFile(prefix + "/Legends_Monsters_and_Heroes/Potions.txt"));
            }
            else{
                cells[row][column] = new Inaccessible();
            }
        }
    }


    public void initialPlayerPos() {
        Random random = new Random();
        int row = random.nextInt(length);
        int column = random.nextInt(length);
        while(!(cells[row][column] instanceof CommonSpace)) {
            row = random.nextInt(length);
            column = random.nextInt(length);
        }
        playerPos[0] = row;
        playerPos[1] = column;
    }


    public void update(int row, int column) {
        playerPos[0] = row;
        playerPos[1] = column;
    }


    public void show() {
        System.out.println("I" + ":Inaccessible " + "M"  + ":Market  " + "#" +":Heroes Team");
        for(int i = 0; i< length; i++) {
            for(int j = 0; j< length; j++){
                System.out.print("+---");
            }
            System.out.println("+");
            for(int k = 0; k< length; k++){
                if(playerPos[0] == k && playerPos[1] == i){
                    System.out.print("| "  + "#"  + " ");
                }

                else{
                    System.out.format("| %s ", cells[k][i].getIcon());
                }
            }
            System.out.println("|");
        }
        for(int i = 0; i< length; i++){
            System.out.print("+---");
        }
        System.out.println("+");
        System.out.println("W/w: move up  A/a: move left  S/s: move down  D/d: move right  Q/q: quit game  I/i: show information");
    }

    public int[] getPlayerPos() {return playerPos;}

    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    public int getLength() {return length;}

}
