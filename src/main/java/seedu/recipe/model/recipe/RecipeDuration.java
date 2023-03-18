package seedu.recipe.model.recipe;

import seedu.recipe.model.recipe.exceptions.RecipeDurationInvalidArgumentLengthException;
import seedu.recipe.model.recipe.exceptions.RecipeDurationInvalidDurationException;
import seedu.recipe.model.recipe.unit.TimeUnit;

import java.util.Objects;

import static seedu.recipe.commons.util.AppUtil.checkArgument;

public class RecipeDuration {
    public static final String MESSAGE_CONSTRAINTS =
            "A Recipe Duration should consist of a numeric/decimal portion and an alphanumeric time unit";
    private static final String VALIDATION_REGEX =
            "((^([2-9]\\d{0,2}(\\.\\d{1,3})?|[1-9][0-9]{1,2}(\\.\\d{1,3})?|0\\.\\d{1,3}[1-9]|1\\.\\d{1,3})\\s+"
            + "(second|minute|hour|day)(s)?$)|"
            + "^1\\s+(second|minute|hour|day)$)";
    private final double time;
    private final TimeUnit timeUnit;

    public RecipeDuration(double time, TimeUnit timeUnit) {
        checkArgument(isValidRecipeDuration(String.format("%s %s", time, timeUnit.toString())), MESSAGE_CONSTRAINTS);
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public static boolean isValidRecipeDuration(String test) {
        return test.matches(VALIDATION_REGEX);
    }

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
}
