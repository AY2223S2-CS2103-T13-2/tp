package seedu.recipe.model.recipe.exceptions;

/**
 * Represents the exception that arises when a parameter string representing an invalid portion is passed into the
 * factory method {@code ::of} of the RecipePortion class.
 */
public class RecipePortionInvalidArgumentException extends RuntimeException {
    public RecipePortionInvalidArgumentException(String s) {
        super(String.format("An invalid argument `%s` was provided for the Recipe portion.", s));
    }
}
