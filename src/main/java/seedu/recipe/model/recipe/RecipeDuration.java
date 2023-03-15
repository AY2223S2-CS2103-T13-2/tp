package seedu.recipe.model.recipe;

import seedu.recipe.model.recipe.exceptions.RecipeDurationInvalidArgumentLengthException;
import seedu.recipe.model.recipe.exceptions.RecipeDurationInvalidDurationException;
import seedu.recipe.model.recipe.exceptions.RecipePortionNotPresentException;
import seedu.recipe.model.recipe.unit.TimeUnit;

import java.util.Objects;

public class RecipeDuration {

    private final double time;
    private final TimeUnit unit;

    private static final String VALIDATION_REGEX =
            "^\\d+(.\\d+)?\\s+[A-Za-z]*$";

    public static final String MESSAGE_CONSTRAINTS =
            "A Recipe Duration should consist of a numeric/decimal portion and an alphanumeric time unit";

    private RecipeDuration(double time, TimeUnit unit){
        this.time = time;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.time, this.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, unit);
    }

    public static boolean isValidRecipeDuration(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            String[] tokens = test.split("\\s+");
            if (tokens.length < 2) {
                return false;
            }
            try {
                double timeAmount = Double.parseDouble(tokens[0]);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static RecipeDuration of(String duration) {
        String[] tokens = duration.split("\\s+");
        if (tokens.length < 2) {
            throw new RecipeDurationInvalidArgumentLengthException();
        }
        try {
            double timeAmount = Double.parseDouble(tokens[0]);
            return new RecipeDuration(timeAmount, new TimeUnit(tokens[1]));
        } catch (NumberFormatException e) {
            throw new RecipeDurationInvalidDurationException(tokens[0]);
        }

    }
}
