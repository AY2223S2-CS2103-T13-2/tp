package seedu.recipe.model.recipe;

import seedu.recipe.model.recipe.exceptions.RecipePortionInvalidArgumentException;
import seedu.recipe.model.recipe.unit.PortionUnit;

import java.util.Objects;

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
            "^\\d+([\\s-]+to[\\s-]+\\d+|[\\s-]+-?[\\s-]*\\d+)?\\s+[A-Za-z0-9]+$";

    private final int lowerRange;
    private final int upperRange;
    private final PortionUnit portionUnit;

    public RecipePortion(int lowerRange, int upperRange, PortionUnit portionUnit){
        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
        this.portionUnit = portionUnit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(lowerRange).append("-").append(upperRange).append(" ").append(portionUnit);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowerRange, upperRange, portionUnit);
    }

    public static boolean isValidRecipePortion(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            String[] tokens = test.split(TOKENIZE_REGEX);
            if (tokens.length != 3) {
                return false;
            }
            try {
                int lower = Integer.parseInt(tokens[0]);
                int upper = Integer.parseInt(tokens[1]);
                if (upper < lower) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static RecipePortion of(String candidate) {
        String[] tokens = candidate.split(TOKENIZE_REGEX);
        if (tokens.length != 3) {
            throw new RecipePortionInvalidArgumentException(candidate);
        }
        try {
            int lower = Integer.parseInt(tokens[0]);
            int upper = Integer.parseInt(tokens[1]);
            if (upper < lower) {
                throw new RecipePortionInvalidArgumentException(candidate);
            }
            return new RecipePortion(lower, upper, new PortionUnit(tokens[2]));
        } catch (NumberFormatException e) {
            throw new RecipePortionInvalidArgumentException(candidate);
        }
    }
}
