package seedu.recipe.storage.jsonadapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_INGREDIENTS;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.recipe.ingredient.IngredientQuantity;

public class JsonAdaptedIngredientTest {
    private static final String VALID_COMMON_NAME = "poultry";
    private static final String VALID_AMOUNT = "500 g";
    private static final String VALID_ESTIMATED_AMOUNT = "about half a pound";
    private static final List<String> VALID_REMARKS = List.of("skinless", "boneless");
    private static final List<JsonAdaptedSubstitutionIngredient> VALID_SUBSTITUTION = List.of();
    @Test
    public void toModelType_invalidIngredientName_throwsIllegalArgumentException() {
        JsonAdaptedIngredient jsonAdaptedIngredient = new JsonAdaptedIngredient(
            "", //name
            VALID_COMMON_NAME, VALID_AMOUNT, VALID_ESTIMATED_AMOUNT, VALID_REMARKS, VALID_SUBSTITUTION
        );
        assertThrows(IllegalArgumentException.class, jsonAdaptedIngredient::toModelType);
    }

    @Test
    public void toModelType_validIngredientDetails_returnsIngredientKeyValuePair() {
        HashMap<Ingredient, IngredientInformation> ingredientList = new HashMap<>();
        CACIO_INGREDIENTS.forEach(ingredientBuilder -> ingredientBuilder
            .build() //Ingredient Map
            .forEach((mainIngredient, ingredientInformation) -> {
                try {
                    ingredientList.putAll(
                        new JsonAdaptedIngredient(mainIngredient, ingredientInformation)
                            .toModelType()
                    );
                } catch (IllegalValueException e) {
                    fail(e.getMessage());
                }
            }
        ));
        assertEquals(CACIO_E_PEPE.getIngredients(), ingredientList);
    }

    @Test
    public void testJsonConstructor_nullName_error() {
        String nullName = null;
        assertThrows(NullPointerException.class, () -> new JsonAdaptedIngredient(nullName,
            VALID_COMMON_NAME, VALID_AMOUNT, VALID_ESTIMATED_AMOUNT, VALID_REMARKS, VALID_SUBSTITUTION));
    }

    @Test
    public void testModelConstructor_nullValues_error() {
        Ingredient validIngredient = Ingredient.of("Lemons");
        IngredientInformation validInformation = new IngredientInformation(
            IngredientQuantity.of("1 bunch"), null, new String[]{}, new Ingredient[]{});
        assertThrows(NullPointerException.class, () -> new JsonAdaptedIngredient(null, validInformation));
        assertThrows(NullPointerException.class, () -> new JsonAdaptedIngredient(validIngredient, null));
    }
}
