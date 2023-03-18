package seedu.recipe.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.unit.TimeUnit;

/**
 * Jackson-friendly version of {@link TimeUnit}.
 */
@JsonInclude(Include.NON_NULL)
class JsonAdaptedTimeUnit {

    private final String unit;

    /**
     * Constructs a {@code JsonAdaptedTimeUnit} with the given {@code TimeUnitTimeUnit}.
     */
    @JsonCreator
    public JsonAdaptedTimeUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Converts a given {@code TimeUnit} into this class for Jackson use.
     */
    public JsonAdaptedTimeUnit(TimeUnit source) {
        unit = source.getUnit();
    }

    @JsonValue
    public String getUnit() {
        return unit;
    }

    /**
     * Converts this Jackson-friendly adapted TimeUnit object into the model's {@code TimeUnit} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted TimeUnit.
     */
    public TimeUnit toModelType() throws IllegalValueException {
        try {
            return new TimeUnit(unit);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(TimeUnit.class.getSimpleName());
        }
    }
}
