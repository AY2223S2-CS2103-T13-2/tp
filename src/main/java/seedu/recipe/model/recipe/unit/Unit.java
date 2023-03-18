package seedu.recipe.model.recipe.unit;

import static java.util.Objects.requireNonNull;

public abstract class Unit {

    public final String unit;

    public Unit(String unit) {
        requireNonNull(unit);
        this.unit = unit;
    }

    @Override
    public String toString() {
        return this.unit;
    }

    public String getUnit() {
        return this.unit;
    }

    @Override
    public boolean equals(Object o) {
        return o == this
                || o instanceof Unit
                && ((Unit) o).unit.equals(this.unit);
    }
}
