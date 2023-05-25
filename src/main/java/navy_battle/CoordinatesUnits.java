package navy_battle;

import java.util.Objects;

public class CoordinatesUnits {
    private final int x;
    private final int y;

    public CoordinatesUnits(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CoordinatesUnits(String code) {
        if (code.length() < 2 || code.length() > 3 || !code.matches("^[A-J]([1-9]|10)$")) {
            throw new IllegalArgumentException("Le code " + code + " est invalide !");
        }

        y = Integer.parseInt(code.substring(1)) - 1;
        x = code.charAt(0) - 'A';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatesUnits that = (CoordinatesUnits) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return Character.toString('A' + x) + (y + 1);
    }
}
