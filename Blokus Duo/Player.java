import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    String name;
    ArrayList<String> pieces = new ArrayList(Arrays.asList("I1", "I2", "I3", "I4", "I5", "V3", "V5", "L4", "L5", "T4", "T5", "Z4", "Z5", "O4", "N", "P", "X", "Y", "W", "U", "F"));

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPieces() {
        return this.pieces;
    }

    public void setPieces(ArrayList<String> pieces) {
        this.pieces = pieces;
    }
}