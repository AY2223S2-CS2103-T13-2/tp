package seedu.recipe.model.recipe.exceptions;

public class RecipeDurationInvalidDurationException extends RuntimeException {
    public RecipeDurationInvalidDurationException(String durationCandidate) {
        super(String.format("An invalid amount of time was provided for the Recipe Duration: `%s`", durationCandidate)
            + "\nEnsure it is a valid number/decimal"
        );
    }
}
