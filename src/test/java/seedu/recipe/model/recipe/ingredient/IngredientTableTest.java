package seedu.recipe.model.recipe.ingredient;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class IngredientTableTest {
    @Test
    public void testEq() {
        assertThrows(NullPointerException.class, (
            ) -> CACIO_E_PEPE.setIngredients((HashMap<Ingredient, IngredientInformation>) null));
    }
}
