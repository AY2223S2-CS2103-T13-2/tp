package seedu.recipe.storage.jsonadapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.recipe.ingredient.IngredientQuantity;


/**
 * Jackson-friendly version of {@link IngredientBuilder}.
 */
@JsonInclude(Include.NON_NULL)
public class JsonAdaptedIngredient {
    @JsonProperty("ingredientName")
    private final String ingredientName;

    @JsonProperty("commonName")
    private final String commonName;

    @JsonProperty("ingredientQuantity")
    private final String ingredientQuantity;

    @JsonProperty("estimatedQuantity")
    private final String estimatedQuantity;

    @JsonProperty("remarks")
    private final List<String> remarks = new ArrayList<>();

    @JsonProperty("substitutions")
    private final List<JsonAdaptedSubstitutionIngredient> substitutions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedIngredient} with the given {@code ingredientName}.
     */
    @JsonCreator
    public JsonAdaptedIngredient(
        @JsonProperty("ingredientName") String ingredientName,
        @JsonProperty("commonName") String commonName,
        @JsonProperty("ingredientQuantity") String ingredientQuantity,
        @JsonProperty("estimatedQuantity") String estimatedQuantity,
        @JsonProperty("remarks") List<String> remarks,
        @JsonProperty("substitutions") List<JsonAdaptedSubstitutionIngredient> substitutions
    ) {
        this.ingredientName = ingredientName;
        this.commonName = commonName;
        this.ingredientQuantity = ingredientQuantity;
        this.estimatedQuantity = estimatedQuantity;
        if (remarks != null) {
            this.remarks.addAll(remarks);
        }
        if (substitutions != null) {
            this.substitutions.addAll(substitutions);
        }
    }

    /**
     * Converts a given {@code IngredientBuilder} into this class for Jackson use.
     */
    public JsonAdaptedIngredient(Ingredient ingredient, IngredientInformation info) {
        ingredientName = ingredient.getName();
        commonName = Optional.of(ingredient.getCommonName()).filter(i -> i.length() != 0).orElse(null);
        ingredientQuantity = info.getQuantity().map(Object::toString).orElse(null);
        estimatedQuantity = info.getEstimatedQuantity().orElse(null);
        remarks.addAll(info.getRemarks());
        substitutions.addAll(info.getSubstitutions().stream()
                .map(JsonAdaptedSubstitutionIngredient::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted ingredient object into the model's {@code HashMap} mapping of
     * {@code Ingredient}-{@code IngredientInformation} key-value pairs.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ingredient.
     */
    public HashMap<Ingredient, IngredientInformation> toModelType() throws IllegalValueException {
        HashMap<Ingredient, IngredientInformation> ingredientKeyValuePair = new HashMap<>();
        //Validate Ingredient
        Ingredient mainIngredient = Ingredient.of(ingredientName);
        if (commonName != null) {
            mainIngredient.setCommonName(commonName);
        }

        //Validate quantity
        IngredientQuantity quantity = null;
        if (ingredientQuantity != null) {
            quantity = IngredientQuantity.of(ingredientQuantity);
        }

        // Validate substitutions
        IngredientInformation info = new IngredientInformation(
            quantity,
            estimatedQuantity,
            remarks.toArray(String[]::new),
            substitutions.stream()
                .map(JsonAdaptedSubstitutionIngredient::toModelType)
                .toArray(Ingredient[]::new)
        );
        ingredientKeyValuePair.put(mainIngredient, info);
        return ingredientKeyValuePair;
    }
}
