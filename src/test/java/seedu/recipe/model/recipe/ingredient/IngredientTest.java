package seedu.recipe.model.recipe.ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IngredientTest {

    @Test
    public void toString_validIngredientWithCommonName() {
        Ingredient polishedRice = Ingredient.of("polished rice");
        polishedRice.setCommonName("rice");
        String expectedString = "polished rice (aka rice)";
        assertEquals(expectedString, polishedRice.toString());
    }
}
