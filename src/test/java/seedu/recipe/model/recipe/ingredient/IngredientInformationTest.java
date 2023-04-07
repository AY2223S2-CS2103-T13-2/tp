package seedu.recipe.model.recipe.ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IngredientInformationTest {

    private static final String VALID_INGREDIENT_INFORMATION_EMPTY_ESTIMATE = "{Q: 1 gram; E: <>; S: [soy sauce]; "
            + "R: [store in a cool, dark place]}";

    @Test
    public void toString_validIngredientInformation() {
        IngredientInformation information = new IngredientInformation(
                IngredientQuantity.of("1 gram"), "",
                new String[]{"store in a cool, dark place"}, new Ingredient[]{Ingredient.of("soy sauce")});
        assertEquals(VALID_INGREDIENT_INFORMATION_EMPTY_ESTIMATE, information.toString());
    }


}
