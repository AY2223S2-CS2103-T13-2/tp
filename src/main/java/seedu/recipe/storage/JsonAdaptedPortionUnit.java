package seedu.recipe.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.unit.PortionUnit;

/**
 * Jackson-friendly version of {@link PortionUnit}.
 */
@JsonInclude(Include.NON_NULL)
class JsonAdaptedPortionUnit {

    private final String unit;

    /**
     * Constructs a {@code JsonAdaptedPortionUnit} with the given {@code PortionUnitPortionUnit}.
     */
    @JsonCreator
    public JsonAdaptedPortionUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Converts a given {@code PortionUnit} into this class for Jackson use.
     */
    public JsonAdaptedPortionUnit(PortionUnit source) {
        unit = source.getUnit();
    }

    @JsonValue
    public String getUnit() {
        return unit;
    }

    /**
     * Converts this Jackson-friendly adapted PortionUnit object into the model's {@code PortionUnit} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted PortionUnit.
     */
    public PortionUnit toModelType() throws IllegalValueException {
        try {
            return new PortionUnit(unit);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(PortionUnit.class.getSimpleName());
        }
    }
}
