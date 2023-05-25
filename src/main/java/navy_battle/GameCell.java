package navy_battle;

import java.util.HashMap;
import java.util.Map;

public enum GameCell {
    EMPTY("."),
    MISSED_FIRE("-"),
    SUCCESSFUL_FIRE("X"),
    BOAT("B");

    private final String letter;
    private static final Map<String, GameCell> letterMap = new HashMap<>();

    static {
        for (GameCell cell : GameCell.values()) {
            letterMap.put(cell.letter, cell);
        }
    }

    GameCell(String letter) {
        this.letter = letter;
    }

    public static GameCell fromLetter(String value) {
        GameCell cell = letterMap.get(value);
        if (cell == null) {
            throw new IllegalArgumentException("Invalid letter! oh sh*t ");
        }
        return cell;
    }

    public String getLetter() {
        return letter;
    }
}
