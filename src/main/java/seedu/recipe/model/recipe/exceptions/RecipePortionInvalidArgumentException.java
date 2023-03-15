package seedu.recipe.model.recipe.exceptions;

public class RecipePortionInvalidArgumentException extends RuntimeException {
    public RecipePortionInvalidArgumentException(String s) {
        super(String.format("An invalid argumenr `s` was provided for the Recipe portion.", s));
    }
}
