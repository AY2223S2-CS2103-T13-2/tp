package seedu.recipe.model.recipe;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.recipe.model.recipe.exceptions.RecipePortionInvalidArgumentException;
import seedu.recipe.model.recipe.unit.PortionUnit;

/**
 * Represents a Recipe's portion in the RecipeBook.
 */
public class RecipePortion {
    public static final String MESSAGE_CONSTRAINTS =
        "A Recipe portion's ranges must be numeric.\n"
            + "If a lower and upper range is provided, they must be separated by \"-\" or \"to\".\n"
            + "Its unit should only contain alphabetic characters and spaces, and it should not be blank";
    // format: {number} {unit} OR {number} {- or to} {number} {unit}
    public static final Pattern VALIDATION_REGEX =
        Pattern.compile("(\\d+)(?:\\s*(?:-|to)\\s*(\\d+))?\\s*([A-Za-z ]+)");

    private final int lowerRange;
    private final int upperRange;
    private final PortionUnit portionUnit;

    /**
     * Generates and returns an instance of a RecipePortion object, if the provided parameters are valid.
     *
     * @param lowerRange  The non-negative lower range amount
     * @param upperRange  The non-negative upper range amount, if present. Else, a negative value.
     * @param portionUnit The portion unit instance
     */
    public RecipePortion(int lowerRange, int upperRange, PortionUnit portionUnit) {
        if (lowerRange < 0) {
            throw new RecipePortionInvalidArgumentException(String.valueOf(upperRange));
        }
        if (upperRange != 0 && upperRange < lowerRange) {
            throw new RecipePortionInvalidArgumentException(String.valueOf(upperRange));
        }
        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
        this.portionUnit = portionUnit;
    }

    /**
     * Checks if the provided String is formatted properly and can be split into the appropriate tokens to
     * generate a RecipePortion instance. Returns a RecipePortion instance if the candidate is valid.
     *
     * @param candidate The string to construct around.
     * @return The generated RecipePortion instance.
     */
    public static RecipePortion of(String candidate) {
        Matcher matcher = VALIDATION_REGEX.matcher(candidate);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }

        // 3 capture groups in the regex, so if the candidate string matches,
        // there should be 3 groups captured!
        assert matcher.groupCount() == 3;

        String lowerString = matcher.group(1);
        String upperString = matcher.group(2);
        String unitString = matcher.group(3);

        int lower = Integer.parseInt(lowerString);
        int upper = upperString == null ? 0 : Integer.parseInt(upperString);

        return new RecipePortion(lower, upper, new PortionUnit(unitString));
    }

    @Override
    public String toString() {
        if (upperRange > 0) {
            return String.format(
                "%s - %s %s",
                lowerRange, upperRange, portionUnit.toString()
                                );
        }
        return String.format("%s %s", lowerRange, portionUnit.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowerRange, upperRange, portionUnit);
    }

    public int getLowerRange() {
        return lowerRange;
    }

    public int getUpperRange() {
        return upperRange;
    }

    public PortionUnit getPortionUnit() {
        return portionUnit;
    }

    @Override
    public boolean equals(Object o) {
        return o == this
            || o instanceof RecipePortion
            && ((RecipePortion) o).portionUnit.equals(portionUnit)
            && ((RecipePortion) o).lowerRange == lowerRange
            && ((RecipePortion) o).upperRange == upperRange;
    }
}
