package seedu.recipe.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Step;

/**
 * Jackson-friendly version of {@link Step}.
 */
@JsonInclude(Include.NON_NULL)
class JsonAdaptedStep {

    private final String stepName;

    /**
     * Constructs a {@code JsonAdaptedStep} with the given {@code stepName}.
     */
    @JsonCreator
    public JsonAdaptedStep(String stepName) {
        this.stepName = stepName;
    }

    /**
     * Converts a given {@code Step} into this class for Jackson use.
     */
    public JsonAdaptedStep(Step source) {
        stepName = source.toString();
    }

    @JsonValue
    public String getStepName() {
        return stepName;
    }

    /**
     * Converts this Jackson-friendly adapted step object into the model's {@code Step} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted step.
     */
    public Step toModelType() throws IllegalValueException {
        try {
            return new Step(stepName);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(Step.MESSAGE_CONSTRAINTS);
        }
    }
}
