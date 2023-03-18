package seedu.recipe.model.recipe.exceptions;

/**
 * Represents the exception that arises when a parameter string representing an invalid duration is passed into the
 * factory method {@code ::of} of the RecipeDuration class.
 */
public class RecipeDurationInvalidDurationException extends RuntimeException {
    /**
     * Constructs and returns an instance of this exception with the formatted message.
     */
    public RecipeDurationInvalidDurationException(String durationCandidate) {
        super(String.format("An invalid amount of time was provided for the Recipe Duration: `%s`", durationCandidate)
            + "\nEnsure it is a valid number/decimal"
        );
    }
}
