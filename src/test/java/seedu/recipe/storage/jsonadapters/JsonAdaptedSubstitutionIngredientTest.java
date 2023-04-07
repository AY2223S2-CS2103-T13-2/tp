package seedu.recipe.storage.jsonadapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.recipe.model.recipe.ingredient.Ingredient;

//@@author alson001
public class JsonAdaptedSubstitutionIngredientTest {
    private static final String VALID_INGREDIENT_NAME = "Sugar";
    private static final String INVALID_INGREDIENT_NAME = "";

    @Test
    public void constructor_validIngredientName_returnsJsonAdaptedSubstitutionIngredient() {
        JsonAdaptedSubstitutionIngredient ingredient = new JsonAdaptedSubstitutionIngredient(VALID_INGREDIENT_NAME);
        assertEquals(VALID_INGREDIENT_NAME, ingredient.getIngredientName());
    }

    @Test
    public void constructor_validIngredient_returnsJsonAdaptedSubstitutionIngredient() {
        Set<Ingredient> ingredientSet = CACIO_E_PEPE.getIngredientList();
        ingredientSet.stream().forEach(ingredient -> {
            JsonAdaptedSubstitutionIngredient adaptedSubstitutionIngredient =
                new JsonAdaptedSubstitutionIngredient(ingredient);
            assertEquals(ingredient.getName(), adaptedSubstitutionIngredient.getIngredientName());
        });
    }

    @Test
    public void toModelType_validIngredientName_returnsIngredient() {
        JsonAdaptedSubstitutionIngredient ingredient = new JsonAdaptedSubstitutionIngredient(VALID_INGREDIENT_NAME);
        assertEquals(Ingredient.of(VALID_INGREDIENT_NAME), ingredient.toModelType());
    }

    @Test
    public void toModelType_emptyIngredientName_throwsIllegalArgumentException() {
        JsonAdaptedSubstitutionIngredient ingredient = new JsonAdaptedSubstitutionIngredient(INVALID_INGREDIENT_NAME);
        assertThrows(IllegalArgumentException.class, ingredient::toModelType);
    }

    @Test
    public void constructor_nullName_error() {
        String nullString = null;
        Ingredient nullIngredient = null;
        assertThrows(NullPointerException.class, () -> new JsonAdaptedSubstitutionIngredient(nullString));
        assertThrows(NullPointerException.class, () -> new JsonAdaptedSubstitutionIngredient(nullIngredient));
    }
}
