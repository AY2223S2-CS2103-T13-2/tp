package seedu.recipe.model.recipe;

import static seedu.recipe.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.recipe.model.recipe.exceptions.RecipeDurationInvalidArgumentLengthException;
import seedu.recipe.model.recipe.exceptions.RecipeDurationInvalidDurationException;
import seedu.recipe.model.recipe.unit.TimeUnit;

/**
 * Represents the Duration that a Recipe is estimated to take to complete.
 */
public class RecipeDuration {
    public static final String MESSAGE_CONSTRAINTS =
            "A Recipe Duration should consist of a numeric/decimal portion and an alphanumeric time unit";
    private static final String VALIDATION_REGEX =
            "^(([2-9]\\d{0,2}|1\\d{1,2})(\\.\\d{1,3})?|[01]\\.\\d{0,2}[1-9])\\s+((hour|minute|second|day)s|min|h)$|"
            + "^(1|1.0)\\s+(hour|minute|day|second|min|h)$";

    private final double time;
    private final TimeUnit timeUnit;

    /**
     * Generates and returns an instance of a RecipeDuration object, if the provided parameters are valid.
     * @param time The non-negative time amount
     * @param timeUnit The time unit instance
     */
    public RecipeDuration(double time, TimeUnit timeUnit) {
        checkArgument(isValidRecipeDuration(String.format("%s %s", time, timeUnit.toString())), MESSAGE_CONSTRAINTS);
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public static boolean isValidRecipeDuration(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Generates an instance of a RecipeDuration Object, provided the parameter string is formatted correctly.
     * @param duration The parameter string to input the time and unit into this factory.
     * @return The generated RecipeDuration instance, if the parameter is valid.
     */
    public static RecipeDuration of(String duration) {
        String[] tokens = duration.split("\\s+");
        if (tokens.length != 2) {
            throw new RecipeDurationInvalidArgumentLengthException();
        }
        if (!isValidRecipeDuration(duration)) {
            throw new RecipeDurationInvalidDurationException(duration);
        }
        //No format exception will occur, thanks to regex
        double timeAmount = Double.parseDouble(tokens[0]);
        return new RecipeDuration(timeAmount, new TimeUnit(tokens[1]));
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.time, this.timeUnit.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, timeUnit);
    }

    public double getTime() {
        return time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    public boolean equals(Object o) {
        return o == this
                || o instanceof RecipeDuration
                && ((RecipeDuration) o).time == this.time
                && ((RecipeDuration) o).timeUnit.equals(this.timeUnit);
    }
}
