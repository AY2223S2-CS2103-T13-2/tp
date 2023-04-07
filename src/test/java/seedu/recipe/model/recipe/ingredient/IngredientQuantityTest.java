package seedu.recipe.model.recipe.ingredient;

import static seedu.recipe.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.recipe.model.recipe.exceptions.RecipeQuantityInvalidArgumentException;

public class IngredientQuantityTest {

    private static final String INVALID_RANGE = "2-1 portions";

    @Test
    public void isValidRecipeQuantity() {
        assertThrows(RecipeQuantityInvalidArgumentException.class, () -> IngredientQuantity.of(INVALID_RANGE));
    }
}
