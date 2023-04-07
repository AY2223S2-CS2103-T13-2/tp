package seedu.recipe.model.recipe.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.recipe.model.recipe.ingredient.IngredientQuantity;

public class RecipeQuantityInvalidArgumentExceptionTest {

    @Test
    public void validRecipeQuantityInvalidArgumentException() {
        Exception exception = assertThrows(RecipeQuantityInvalidArgumentException.class, () ->
               IngredientQuantity.of("0"));
        String expectedMessage = "An invalid quantity expression \"0\" was passed for this ingredient.\n"
               + "The quantity field for a Recipe Ingredient should consist of this format: "
               + "`{amount} {unit}`, where the amount can either be a non-zero decimal, i.e. `A/a` or `One/one`, "
               + "\nand the unit should comprise of alphabetic characters, with minimal use of "
               + "trailing periods ('.') and hyphens."
               + "\ni.e. `1 gram`, `1.5 L`, `A pinch of`, `One oz.`";;
        assertEquals(expectedMessage, exception.getMessage());
    }
}
