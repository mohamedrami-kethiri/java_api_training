package navy_battle;

import navy_battle.BoatOrientation;
import navy_battle.ResultOfFire;
import navy_battle.GameCell;

import java.util.*;

public class MapUnits {
    private final Integer[] BOATS = {5, 4, 3, 3, 2};
    private final GameCell[][] map = new GameCell[10][10];
    private final List<List<CoordinatesUnits>> boats = new ArrayList<>();

    public MapUnits(boolean fill) {
        for (GameCell[] gameCells : map) {
            Arrays.fill(gameCells, GameCell.EMPTY);
        }
        if (fill) {
            buildMap();
        }
    }

    public int getHeight() {
        return map[0].length;
    }

    public int getWidth() {
        return map.length;
    }

    private void buildMap() {
        var random = new Random();
        var boats = new ArrayList<>(Arrays.asList(BOATS));
        Collections.shuffle(boats);
        while (!boats.isEmpty()) {
            int boat = boats.get(0);
            int x = Math.abs(random.nextInt()) % getWidth();
            int y = Math.abs(random.nextInt()) % getHeight();
            var orientation = random.nextBoolean() ? BoatOrientation.HORIZONTAL : BoatOrientation.VERTICAL;
            if (!canFit(boat, x, y, orientation))
                continue;
            addBoat(boat, x, y, orientation);
            boats.remove(0);
        }
    }

    private boolean canFit(int length, int x, int y, BoatOrientation orientation) {
        if (x >= getWidth() || y >= getHeight() || getCell(x, y) != GameCell.EMPTY)
            return false;
        if (length == 0)
            return true;
        return switch (orientation) {
            case HORIZONTAL -> canFit(length - 1, x + 1, y, orientation);
            case VERTICAL -> canFit(length - 1, x, y + 1, orientation);
        };
    }

    public GameCell getCell(CoordinatesUnits coordinates) {
        return getCell(coordinates.getX(), coordinates.getY());
    }

    public GameCell getCell(int x, int y) {
        if (x >= 10 || y >= 10)
            throw new RuntimeException("Invalid coordinates!");
        return map[x][y];
    }

    public void setCell(CoordinatesUnits coordinates, GameCell newStatus) {
        map[coordinates.getX()][coordinates.getY()] = newStatus;
    }

    public void addBoat(int length, int x, int y, BoatOrientation orientation) {
        var coordinates = new ArrayList<CoordinatesUnits>();
        while (length > 0) {
            map[x][y] = GameCell.BOAT;
            length--;
            coordinates.add(new CoordinatesUnits(x, y));
            switch (orientation) {
                case HORIZONTAL -> x++;
                case VERTICAL -> y++;
            }
        }
        boats.add(coordinates);
    }

    public boolean hasShipLeft() {
        for (var row : map) {
            if (Arrays.stream(row).anyMatch(s -> s == GameCell.BOAT)) return true;
        }
        return false;
    }

    public CoordinatesUnits getNextPlaceToHit() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (getCell(i, j) == GameCell.EMPTY)
                return new CoordinatesUnits(i, j);
            }
        }
        throw new RuntimeException("Error");
    }

    public ResultOfFire hit(CoordinatesUnits coordinates) {
        if (getCell(coordinates) != GameCell.BOAT) return ResultOfFire.MISS;
        var first = boats.stream().filter(s -> s.contains(coordinates)).findFirst();
        assert (first.isPresent());
        first.get().remove(coordinates);
        setCell(coordinates, GameCell.SUCCESSFUL_FIRE);
        return first.get().isEmpty() ? ResultOfFire.SUNK : ResultOfFire.HIT;
    }
}                 
