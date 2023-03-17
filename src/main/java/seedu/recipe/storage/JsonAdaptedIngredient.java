package seedu.recipe.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Ingredient;

import java.util.Optional;

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
        //WIP
        return new Ingredient(ingredientName);
    }

    public Optional<Ingredient> toModelTypeOptional() {
        try {
            Ingredient out = this.toModelType();
            return Optional.ofNullable(out);
        } catch (IllegalValueException e) {
            //log
            return Optional.empty();
        }
    }

}
