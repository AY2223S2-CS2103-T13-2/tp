package seedu.recipe.model.recipe.exceptions;

/**
 * Represents the exception that arises when a parameter string of invalid length is passed into the
 * factory method {@code ::of} of the RecipeDuration class.
 */
public class RecipeDurationInvalidArgumentLengthException extends RuntimeException {
    /**
     * Constructs and returns an instance of this exception with the formatted message.
     */
    public RecipeDurationInvalidArgumentLengthException() {
        super("An argument list of invalid length was passed for a Recipe Duration."
                + "\nEnsure it is of the following format: '{number OR decimal} {duration}; "
                + "\nExample: `1 minute`, `1.5 hours`");
    }
}
