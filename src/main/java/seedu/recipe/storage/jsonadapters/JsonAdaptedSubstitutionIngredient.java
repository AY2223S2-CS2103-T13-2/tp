package seedu.recipe.storage.jsonadapters;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.recipe.model.recipe.ingredient.Ingredient;

/**
 * Represents a Jackson-friendly simple Ingredient instance that is the substitute for
 * an Ingredient in a Recipe
 */
@JsonInclude(Include.NON_NULL)
public class JsonAdaptedSubstitutionIngredient {
    private final String ingredientName;

    @JsonCreator
    public JsonAdaptedSubstitutionIngredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public JsonAdaptedSubstitutionIngredient(Ingredient ingredient) {
        ingredientName = ingredient.getName();
    }

    @JsonValue
    public String getIngredientName() {
        return ingredientName;
    }

    public Ingredient toModelType() throws IllegalArgumentException {
        return Ingredient.of(ingredientName);
    }
}
