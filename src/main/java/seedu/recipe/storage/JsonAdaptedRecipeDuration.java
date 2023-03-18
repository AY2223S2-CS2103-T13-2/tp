package seedu.recipe.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.RecipeDuration;

/**
 * Jackson-friendly version of {@link Name}.
 */
@JsonInclude(Include.NON_NULL)
class JsonAdaptedRecipeDuration {


    private final double time;

    private final JsonAdaptedTimeUnit timeUnit;

    /**
     * Constructs a {@code JsonAdaptedName} with the given {@code NameName}.
     */
    @JsonCreator
    public JsonAdaptedRecipeDuration(@JsonProperty("time") double time,
                                     @JsonProperty("timeUnit") JsonAdaptedTimeUnit timeUnit) {
        this.time = time;
        this.timeUnit = timeUnit;
    }

    /**
     * Converts a given {@code Name} into this class for Jackson use.
     */
    public JsonAdaptedRecipeDuration(RecipeDuration source) {
        this.time = source.getTime();
        this.timeUnit = new JsonAdaptedTimeUnit(source.getTimeUnit());
    }

    @JsonGetter("time")
    public double getTime() {
        return time;
    }

    @JsonGetter("timeUnit")
    public JsonAdaptedTimeUnit getTimeUnit() {
        return timeUnit;
    }

    /**
     * Converts this Jackson-friendly adapted Name object into the model's {@code Name} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Name.
     */
    public RecipeDuration toModelType() throws IllegalValueException {
        try {
            return new RecipeDuration(time, timeUnit.toModelType());
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(RecipeDuration.MESSAGE_CONSTRAINTS);
        }
    }
}
