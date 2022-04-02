package CS611OOP;

// This class can help the map to define random position.
import java.util.Objects;

public class Pair {
    int row;
    int column;
    CellType type;

    public Pair(int row, int column, CellType type){
        this.row = row;
        this.column = column;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return row == pair.row && column == pair.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
