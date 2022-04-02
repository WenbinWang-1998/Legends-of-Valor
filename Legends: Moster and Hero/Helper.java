// reference: https://github.com/yuanfeif/Legends_Monsters-and-Heroes/blob/main/src/HeroList.java

//This class can help other class scan the text file.
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class Helper {
    public static List scanTextFile(String filePath) {
        List list = null;
        List<String> result;
        try {
            result = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
            if(filePath.contains("Armory")) {
                list = new ArrayList<Armor>();
                for(int i = 1; i<result.size(); i++){
                    String[] stats = result.get(i).split("\\s+");
                    list.add(new Armor(stats[0], Integer.parseInt(stats[1]), Integer.parseInt(stats[2]), Double.valueOf(stats[3])));
                }
            }
            if(filePath.contains("Weaponry")) {
                list = new ArrayList<Weapon>();
                for(int i = 1; i<result.size(); i++) {
                    String[] stats = result.get(i).split("\\s+");
                    list.add(new Weapon(stats[0], Integer.parseInt(stats[1]), Integer.parseInt(stats[2]), Double.valueOf(stats[3]), Integer.parseInt(stats[4])));
                }
            }
            if(filePath.contains("Potions")) {
                list = new ArrayList<Potion>();
                for(int i = 1; i<result.size(); i++) {
                    String[] stats = result.get(i).split("\\s+");
                    List<String> temp;
                    if (stats[4].equals("All")){
                        temp = List.of(stats[5].split("/"));
                    }
                    else{
                        temp = List.of(stats[4].split("/"));
                    }
                    list.add(new Potion(stats[0], Integer.parseInt(stats[1]), Integer.parseInt(stats[2]), Double.valueOf(stats[3]), temp));
                }
            }
            if(filePath.contains("FireSpells")) {
                list = new ArrayList<FireSpell>();
                for (int i = 1; i<result.size(); i++) {
                    String[] stats = result.get(i).split("\\s+");
                    list.add(new FireSpell(stats[0], Integer.parseInt(stats[1]), Integer.parseInt(stats[2]), Double.valueOf(stats[3]), Double.valueOf(stats[4])));
                }
            }
            if(filePath.contains("IceSpells")) {
                list = new ArrayList<IceSpell>();
                for(int i = 1; i<result.size(); i++) {
                    String[] stats = result.get(i).split("\\s+");
                    list.add(new IceSpell(stats[0], Integer.parseInt(stats[1]), Integer.parseInt(stats[2]), Double.valueOf(stats[3]), Double.valueOf(stats[4])));
                }
            }
            if(filePath.contains("LightningSpells")) {
                list = new ArrayList<LightningSpell>();
                for(int i = 1; i<result.size(); i++) {
                    String[] stats = result.get(i).split("\\s+");
                    list.add(new LightningSpell(stats[0], Integer.parseInt(stats[1]), Integer.parseInt(stats[2]), Double.valueOf(stats[3]), Double.valueOf(stats[4])));
                }
            }
            if(filePath.contains("Warriors")) {
                list = new ArrayList<Warrior>();
                for(int i = 1; i<result.size(); i++) {
                    String[] stats = result.get(i).split("\\s+");
                    list.add(new Warrior(stats[0], Double.valueOf(stats[1]), Double.valueOf(stats[2]), Double.valueOf(stats[3]), Double.valueOf(stats[4]), Integer.parseInt(stats[5]), Integer.parseInt(stats[6])));
                }
            }
            if(filePath.contains("Sorcerers")) {
                list = new ArrayList<Sorcerer>();
                for(int i = 1; i<result.size(); i++) {
                    String[] stats = result.get(i).split("\\s+");
                    list.add(new Sorcerer(stats[0], Double.valueOf(stats[1]), Double.valueOf(stats[2]), Double.valueOf(stats[3]), Double.valueOf(stats[4]), Integer.parseInt(stats[5]), Integer.parseInt(stats[6])));
                }
            }
            if(filePath.contains("Paladins")) {
                list = new ArrayList<Paladin>();
                for(int i = 1; i<result.size(); i++) {
                    String[] stats = result.get(i).split("\\s+");
                    list.add(new Paladin(stats[0], Double.valueOf(stats[1]), Double.valueOf(stats[2]), Double.valueOf(stats[3]), Double.valueOf(stats[4]), Integer.parseInt(stats[5]), Integer.parseInt(stats[6])));
                }
            }
            if(filePath.contains("Dragons")) {
                list = new ArrayList<Dragon>();
                for(int i = 1; i<result.size(); i++) {
                    String[] stats = result.get(i).split("\\s+");
                    list.add(new Dragon(stats[0], Integer.parseInt(stats[1]), Double.valueOf(stats[2]), Double.valueOf(stats[3]), Double.valueOf(stats[4])));
                }
            }
            if(filePath.contains("Exoskeletons")) {
                list = new ArrayList<Exoskeleton>();
                for(int i = 1; i<result.size(); i++) {
                    String[] stats = result.get(i).split("\\s+");
                    list.add(new Exoskeleton(stats[0], Integer.parseInt(stats[1]), Double.valueOf(stats[2]), Double.valueOf(stats[3]), Double.valueOf(stats[4])));
                }
            }
            if(filePath.contains("Spirits")) {
                list = new ArrayList<Spirit>();
                for(int i = 1; i<result.size(); i++) {
                    String[] stats = result.get(i).split("\\s+");
                    list.add(new Spirit(stats[0], Integer.parseInt(stats[1]), Double.valueOf(stats[2]), Double.valueOf(stats[3]), Double.valueOf(stats[4])));
                }
            }
        }
        catch (IOException e) {
            System.out.println("Something wrong with this path, this project can only run on Windows terminal, don't " +
                    "use IDE or MAC to run it." + filePath);
        }
        return list;
    }
}
