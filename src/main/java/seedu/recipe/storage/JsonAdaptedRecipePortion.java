package seedu.recipe.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.RecipePortion;

/**
 * Jackson-friendly version of {@link Name}.
 */
@JsonInclude(Include.NON_NULL)
class JsonAdaptedRecipePortion {

    private final int lowerRange;

    private final int upperRange;

    private final JsonAdaptedPortionUnit portionUnit;

    /**
     * Constructs a {@code JsonAdaptedName} with the given {@code NameName}.
     */
    @JsonCreator
    public JsonAdaptedRecipePortion(@JsonProperty("lowerRange") int lowerRange,
                                    @JsonProperty("upperRange") int upperRange,
                                    @JsonProperty("portionUnit") JsonAdaptedPortionUnit portionUnit) {
        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
        this.portionUnit = portionUnit;
    }

    /**
     * Converts a given {@code Name} into this class for Jackson use.
     */
    public JsonAdaptedRecipePortion(RecipePortion source) {
        this.lowerRange = source.getLowerRange();
        this.upperRange = source.getUpperRange();
        this.portionUnit = new JsonAdaptedPortionUnit(source.getPortionUnit());
    }

    @JsonGetter("lowerRange")
    public int getLowerRange() {
        return lowerRange;
    }

    @JsonGetter("upperRange")
    public int getUpperRange() {
        return upperRange;
    }

    @JsonGetter("portionUnit")
    public JsonAdaptedPortionUnit getPortionUnit() {
        return portionUnit;
    }

    /**
     * Converts this Jackson-friendly adapted Name object into the model's {@code Name} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Name.
     */
    public RecipePortion toModelType() throws IllegalValueException {
        //WIP
        try {
            return new RecipePortion(lowerRange, upperRange, portionUnit.toModelType());
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(RecipePortion.MESSAGE_CONSTRAINTS);
        }
    }
}
