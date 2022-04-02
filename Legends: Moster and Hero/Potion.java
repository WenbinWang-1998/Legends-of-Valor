import java.util.List;

public class Potion extends Item {

    private double value;
    private List<String> attributes;

    public Potion(String name, int price, int requiredLevel) {
        super(name, price, requiredLevel);
    }

    public Potion(String name, int price, int requiredLevel, double value, List<String> attributes) {
        this(name, price, requiredLevel);
        this.value = value;
        this.attributes = attributes;
    }

    public String toString() {
        return "Potion: " + name + ", price is " + price + ", required level is " + requiredLevel + ", property value is " +
                value + ", provided " + attributes;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

}
