// The commonspace will not have any icon.
import java.util.Random;

public class CommonSpace extends Cell{
    //If there has a fight on this cell.
    boolean fight;
    public CommonSpace() {
        super(" ");
    }

    public boolean getFight() {
        Random random = new Random();
        if(random.nextInt(10) >= 5){
            fight = true;
        }
        return fight;
    }

}
