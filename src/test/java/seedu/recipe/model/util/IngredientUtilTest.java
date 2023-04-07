package seedu.recipe.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.recipe.ingredient.IngredientQuantity;

public class IngredientUtilTest {

    @Test
    public void ingredientKeyValuePairToString() {
        Ingredient salt = Ingredient.of("salt");
        IngredientInformation saltInfo = new IngredientInformation(
                IngredientQuantity.of("1 gram"), "half a pinch",
                new String[]{}, new Ingredient[]{Ingredient.of("soy sauce"),
                Ingredient.of("seaweed flakes")});
        String keyValuePair = IngredientUtil.ingredientKeyValuePairToString(salt, saltInfo);
        String expectedPair = "1 gram (half a pinch) salt  Substitutions: soy sauce, seaweed flakes ";
        assertEquals(expectedPair, keyValuePair);
    }

}
