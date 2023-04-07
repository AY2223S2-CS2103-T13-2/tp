package seedu.recipe.storage.jsonadapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Step;

//@@author alson001
public class JsonAdaptedStepTest {
    private static final String VALID_STEP = "Preheat oven to 200C";
    private static final String INVALID_STEP = "123456";

    @Test
    public void constructor_validStepName_returnsJsonAdaptedStep() {
        JsonAdaptedStep adaptedStep = new JsonAdaptedStep(VALID_STEP);
        assertEquals(VALID_STEP, adaptedStep.getStepName());
    }

    @Test
    public void constructor_validStep_returnsJsonAdaptedStep() {
        JsonAdaptedStep adaptedStep = new JsonAdaptedStep(new Step(VALID_STEP));
        assertEquals(VALID_STEP, adaptedStep.getStepName());
    }

    @Test
    public void toModelType_validStepName_success() throws IllegalValueException {
        JsonAdaptedStep adaptedStep = new JsonAdaptedStep("Preheat oven to 200C");
        Step step = adaptedStep.toModelType();
        assertEquals("Preheat oven to 200C", step.toString());
    }

    @Test
    public void toModelType_invalidStepName_throwsIllegalValueException() {
        JsonAdaptedStep adaptedStep = new JsonAdaptedStep(INVALID_STEP);
        assertThrows(IllegalValueException.class, adaptedStep::toModelType);
    }

    @Test
    public void constructorNullStep_error() {
        String nullName = null;
        Step nullStep = null;
        assertThrows(NullPointerException.class, () -> new JsonAdaptedStep(nullName));
        assertThrows(NullPointerException.class, () -> new JsonAdaptedStep(nullStep));
    }
}

