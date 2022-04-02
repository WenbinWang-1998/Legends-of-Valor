//This class is the superclass of Inaccessible, Common space and market.
public abstract class Cell {

    protected String icon; // The sign showed on the map

    Cell(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
