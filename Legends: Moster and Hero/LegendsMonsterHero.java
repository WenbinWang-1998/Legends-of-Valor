import java.util.Scanner;

public class LegendsMonsterHero {
    private Map map;
    private Player player;
    public void start() {
        System.out.println("--------Legends: Monsters and Heroes--------");
        System.out.println("How many heros you want in you team? 1-3");
        Scanner scanner = new Scanner(System.in);
        String s = null;
        String s1 = "[123]";
        while((s = scanner.next()) != null) {
            if(!s.matches(s1)){
                System.out.println("Invalid input. Please try again");
            }
            else{
                break;
            }

        }
        player = new Player(Integer.parseInt(s));
        map = new Map(8);
        map.show();
        Scanner scanner2 = new Scanner(System.in);
        String regex = "[WwAaSsDdQqIiGg]";
        String temp = null;
        while((temp = scanner2.next()) != null) {
            if(!temp.matches(regex)) {
                System.out.println("Invalid input. Please try again");
                continue;
            }
            else {
                if(temp.matches("[WwAaSsDd]")) {
                    player.move(temp, map);
                    map.show();
                }
                if(temp.matches("[Qq]")) {
                    System.out.println("Game over");
                    System.exit(0);
                }
                if(temp.matches("[Ii]")){
                    player.showPeace();
                    map.show();
//                    System.out.println("W/w: move up  A/a: move left  S/s: move down  D/d: move right  Q/q: quit game  I/i: show information");
                }
            }
        }
    }
}
