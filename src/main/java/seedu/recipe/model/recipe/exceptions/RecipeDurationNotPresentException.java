package seedu.recipe.model.recipe.exceptions;

/**
 * Represents the exception that arises when a parameter string not containing a duration is passed into the
 * factory method {@code ::of} of the RecipeDuration class.
 */
public class RecipeDurationNotPresentException extends RuntimeException {
    public RecipeDurationNotPresentException() {
        super("A recipe duration has not yet been specified for this recipe - Please populate one");
    }
}
