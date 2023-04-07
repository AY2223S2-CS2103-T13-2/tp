package seedu.recipe.model.recipe.unit;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.commons.util.AppUtil.checkArgument;

/**
 * Represents the unit of measurement for different data stored by a Recipe in RecipeBook.
 */
public abstract class Unit {
    public static final String MESSAGE_CONSTRAINTS = "A Unit should be made up of one or more groups of whitespace "
        + "separated alphabetic characters. '%s' does not fit this format.";
    private static final String VALIDATION_REGEX = "^[a-zA-Z]+(\\s+[a-zA-Z]+)*$";
    public final String unit;

    /**
     * Generates and returns a Unit instance around the provided String unit parameter.
     *
     * @param unit The String representing the unit around which to construct this Unit instance.
     */
    public Unit(String unit) {
        requireNonNull(unit);
        checkArgument(unit.matches(VALIDATION_REGEX), String.format(MESSAGE_CONSTRAINTS, unit));
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
