package seedu.recipe.model.recipe.unit;

/**
 * Represents the unit of measure of Recipe Portions, i.e. "servings", "portions"
 */
public class PortionUnit extends Unit {
    public PortionUnit(String unit) {
        super(unit);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PortionUnit // instanceof handles nulls
                && unit.equals(((PortionUnit) other).unit)); // state check
    }
}

