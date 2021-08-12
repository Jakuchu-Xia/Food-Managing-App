package model;

import java.util.Arrays;

// Represent an unit
public enum Unit {
    ML, L, G, KG, NONE;

    // EFFECTS: convert the given string to unit
    public static Unit parseUnit(String string) {
        switch (string) {
            case "ML": return ML;
            case "L": return L;
            case "G": return G;
            case "KG": return KG;
            default: return NONE;
        }
    }

    // EFFECTS: convert the given unit to standard unit
    public static Unit convertUnit(Unit unit) {
        if (unit == Unit.L) {
            return Unit.ML;
        } else if (unit == Unit.KG) {
            return Unit.G;
        } else {
            return unit;
        }
    }

    // EFFECTS: convert the amount based on its standard unit
    public static double convertAmount(double amount, Unit unit) {
        if (unit == Unit.L || unit ==  Unit.KG) {
            return amount * 1000;
        } else {
            return amount;
        }
    }

    // EFFECTS: return a list contains all the elements in this enum
    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
