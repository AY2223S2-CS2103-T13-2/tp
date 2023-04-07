package seedu.recipe.storage.jsonadapters;

import static java.util.Objects.requireNonNull;

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

    /**
     * Constructs an instance of this class around a String obtained from the JSON file.
     * @param ingredientName The Ingredient Name to populate this instance with.
     */
    @JsonCreator
    public JsonAdaptedSubstitutionIngredient(String ingredientName) {
        requireNonNull(ingredientName);
        this.ingredientName = ingredientName;
    }

    /**
     * Constructs an instance of this class around an Ingredient obtained from a Recipe instance.
     * @param ingredient The substitution Ingredient used in the Recipe instance.
     */
    public JsonAdaptedSubstitutionIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
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
