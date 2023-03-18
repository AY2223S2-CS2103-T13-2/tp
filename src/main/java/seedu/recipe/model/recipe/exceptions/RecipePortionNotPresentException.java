package seedu.recipe.model.recipe.exceptions;

/**
 * Represents the exception that arises when a parameter string not containing a portion is passed into the
 * factory method {@code ::of} of the RecipePortion class.
 */
public class RecipePortionNotPresentException extends RuntimeException {
    public RecipePortionNotPresentException() {
        super("A Recipe Portion was not specified for this recipe - please provide one");
    }
}
