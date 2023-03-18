package seedu.recipe.model.recipe.unit;

/**
 * Represents the unit of measure of Recipe Durations, i.e. "hours", "minutes"
 */
public class TimeUnit extends Unit {
    public TimeUnit(String unit) {
        super(unit);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimeUnit // instanceof handles nulls
                && unit.equals(((TimeUnit) other).unit)); // state check
    }
}
