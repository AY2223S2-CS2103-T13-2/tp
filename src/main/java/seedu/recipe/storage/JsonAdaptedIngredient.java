package seedu.recipe.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Ingredient;

/**
 * Jackson-friendly version of {@link Ingredient}.
 */
@JsonInclude(Include.NON_NULL)
class JsonAdaptedIngredient {

    private final String ingredientName;

    /**
     * Constructs a {@code JsonAdaptedIngredient} with the given {@code ingredientName}.
     */
    @JsonCreator
    public JsonAdaptedIngredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    /**
     * Converts a given {@code Ingredient} into this class for Jackson use.
     */
    public JsonAdaptedIngredient(Ingredient source) {
        ingredientName = source.name;
    }

    @JsonValue
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Converts this Jackson-friendly adapted ingredient object into the model's {@code Ingredient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ingredient.
     */
    public Ingredient toModelType() throws IllegalValueException {
        try {
            return new Ingredient(ingredientName);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(Ingredient.MESSAGE_CONSTRAINTS);
        }
    }
}
