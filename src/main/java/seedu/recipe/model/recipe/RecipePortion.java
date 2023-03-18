package seedu.recipe.model.recipe;

import static seedu.recipe.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.recipe.model.recipe.exceptions.RecipePortionInvalidArgumentException;
import seedu.recipe.model.recipe.unit.PortionUnit;

/**
 * Represents the Portion Size of a Recipe, i.e. "1-2 servings"
 */
public class RecipePortion {
    public static final String MESSAGE_CONSTRAINTS =
            "Recipe Portion's upper and lower ranges should only contain numbers, and its unit should only contain "
            + "alphanumeric characters, and it should not be blank";

    private static final String TOKENIZE_REGEX =
            "([\\s-]+to[\\s-]+|[\\s-]+-?[\\s-]*)";

    //{digit}(- OR to){digit} {alphabetic unit}
    private static final String VALIDATION_REGEX =
            "^1\\s+[A-Za-z]*[A-RT-Za-rt-z]$|^1(\\s+to\\s+|\\s*\\-\\s*)\\d{1,3}\\s+[A-Za-z]+$|"
            + "^[02-9]\\d{0,2}((\\s+to\\s+|\\s*-\\s*)\\d{1,3})?\\s+[A-Za-z]+s$";

    private final int lowerRange;
    private final int upperRange;
    private final PortionUnit portionUnit;

    /**
     * Generates and returns an instance of a RecipePortion object, if the provided parameters are valid.
     * @param lowerRange The non-negative lower range amount
     * @param upperRange The non-negative upper range amount, if present. Else, a negative value.
     * @param portionUnit The portion unit instance
     */
    public RecipePortion(int lowerRange, int upperRange, PortionUnit portionUnit) {
        if (upperRange < 0) {
            checkArgument(
                    isValidRecipePortion(String.format("%s %s", lowerRange, portionUnit.toString())),
                    MESSAGE_CONSTRAINTS);
        } else {
            checkArgument(
                    isValidRecipePortion(
                            String.format("%s-%s %s", lowerRange, upperRange, portionUnit.toString())),
                    MESSAGE_CONSTRAINTS);
        }
        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
        this.portionUnit = portionUnit;
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

    /**
     * Checks if the provided String is formatted properly and can be split into the appropriate tokens to
     * generate a RecipePortion instance.
     * @param test The string to test.
     * @return The boolean indicating if it is valid.
     */
    public static boolean isValidRecipePortion(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        String[] tokens = test.split(TOKENIZE_REGEX);
        if (tokens.length != 3) {
            String[] singularTokens = test.split("\\s+");
            int lower = Integer.parseInt(singularTokens[0]);
            return lower > 0;
        }
        int lower = Integer.parseInt(tokens[0]);
        int upper = Integer.parseInt(tokens[1]);
        return upper > lower;
    }

    /**
     * Checks if the provided String is formatted properly and can be split into the appropriate tokens to
     * generate a RecipePortion instance. Returns a RecipePortion instance if the candidate is valid.
     * @param candidate The string to construct around.
     * @return The generated RecipePortion instance.
     */
    public static RecipePortion of(String candidate) {
        if (!isValidRecipePortion(candidate)) {
            throw new RecipePortionInvalidArgumentException(candidate);
        }

        String[] tokens = candidate.split(TOKENIZE_REGEX);
        if (tokens.length != 3) {
            String[] singularToken = candidate.split("\\s+");

            int lower = Integer.parseInt(singularToken[0]);
            return new RecipePortion(lower, Integer.MIN_VALUE, new PortionUnit(singularToken[1]));
        }
        int lower = Integer.parseInt(tokens[0]);
        int upper = Integer.parseInt(tokens[1]);

        return new RecipePortion(lower, upper, new PortionUnit(tokens[2]));
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
