import java.util.ArrayList;
import java.util.List;
// The inventory of hero
public class Inventory {

    private List<Item> items;

    public Inventory() {
        items = new ArrayList<Item>();
    }

    public void add(Item item) {
        if(item == null) return;
        items.add(item);
    }

    public int getSize() {
        return items.size();
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

}
