package seedu.recipe.model.recipe;

import seedu.recipe.model.recipe.exceptions.RecipePortionInvalidArgumentException;
import seedu.recipe.model.recipe.unit.PortionUnit;

import java.util.Objects;

import static seedu.recipe.commons.util.AppUtil.checkArgument;

/**
 *
 */
public class RecipePortion {
    public static final String MESSAGE_CONSTRAINTS =
            "Recipe Portion's upper and lower ranges should only contain numbers, and its unit should only contain " +
                    "alphanumeric characters, and it should not be blank";

    private static final String TOKENIZE_REGEX =
            "([\\s-]+to[\\s-]+|[\\s-]+-?[\\s-]*)";

    //{digit}(- OR to){digit} {alphabetic unit}
    private static final String VALIDATION_REGEX =
            "^1\\s+[A-Za-z]*[A-RT-Za-rt-z]$|^1(\\s+to\\s+|\\s*\\-\\s*)\\d{1,3}\\s+[A-Za-z]+$|" +
                    "^[02-9]\\d{0,2}((\\s+to\\s+|\\s*-\\s*)\\d{1,3})?\\s+[A-Za-z]+s$";

    private final int lowerRange;
    private final int upperRange;
    private final PortionUnit portionUnit;

    public RecipePortion(int lowerRange, int upperRange, PortionUnit portionUnit){
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

    public static boolean isValidRecipePortion(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        String[] tokens = test.split(TOKENIZE_REGEX);
        if (tokens.length != 3) {
            String[] singular_token = test.split("\\s+");
            int lower = Integer.parseInt(tokens[0]);
            return lower > 0;
        }
        int lower = Integer.parseInt(tokens[0]);
        int upper = Integer.parseInt(tokens[1]);
        return upper > lower;
    }

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
}
