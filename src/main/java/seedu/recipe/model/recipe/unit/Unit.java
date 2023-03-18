package seedu.recipe.model.recipe.unit;

import static java.util.Objects.requireNonNull;

/**
 * Represents a general unit of measure.
 */
public abstract class Unit {
    public final String unit;

    /**
     * Generates and returns a Unit instance around the provided String unit parameter.
     * @param unit The String representing the unit around which to construct this Unit instance.
     */
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
