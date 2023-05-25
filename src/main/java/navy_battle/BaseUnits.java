package navy_battle;

import java.util.Optional;

public class BaseUnits<Unit> {
    private Optional<Unit> unit = Optional.empty();

    public void set(Unit obj) {
        unit = Optional.ofNullable(obj);
    }

    public boolean isEmpty() {
        return unit.isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public Unit get() {
        return unit.orElseThrow(() -> new RuntimeException("Unit is null"));
    }
}
