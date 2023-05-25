package navy_battle;

import java.util.HashMap;
import java.util.Map;

public enum ResultOfFire {
    MISS("miss"), HIT("hit"), SUNK("sunk");

    private final String apiString;
    private static final Map<String, ResultOfFire> apiStringMap = new HashMap<>();

    static {
        for (ResultOfFire result : ResultOfFire.values()) {
            apiStringMap.put(result.apiString, result);
        }
    }

    ResultOfFire(String res) {
        this.apiString = res;
    }

    public static ResultOfFire fromAPI(String value) {
        ResultOfFire result = apiStringMap.get(value);
        if (result == null) {
            throw new IllegalArgumentException("Invalid value! very sad ;( ");
        }
        return result;
    }

    public String toAPI() {
        return apiString;
    }
}
