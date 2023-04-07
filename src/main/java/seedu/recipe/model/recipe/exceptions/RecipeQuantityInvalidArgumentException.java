package seedu.recipe.model.recipe.exceptions;

import seedu.recipe.model.recipe.ingredient.IngredientQuantity;

/**
 * Represents the exception that arises when a parameter string not containing a portion is passed into the
 * factory method {@code ::of} of the IngredientQuantity class.
 */
public class RecipeQuantityInvalidArgumentException extends IllegalArgumentException {
    /**
     * Generates and returns an instance of this Exception, with the provided argument string.
     *
     * @param s The invalid argument that was entered into the IngredientQuantity {@code ::of} method.
     */
    public RecipeQuantityInvalidArgumentException(String s) {
        super(
            String.format(
                "An invalid quantity expression \"%s\" was passed for this ingredient.\n%s",
                s, IngredientQuantity.MESSAGE_CONSTRAINTS));
    }
}
