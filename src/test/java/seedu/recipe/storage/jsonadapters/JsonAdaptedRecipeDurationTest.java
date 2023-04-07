package seedu.recipe.storage.jsonadapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.RecipeDuration;
import seedu.recipe.model.recipe.unit.TimeUnit;

//@@author alson001
public class JsonAdaptedRecipeDurationTest {
    private static final RecipeDuration RECIPE_DURATION = new RecipeDuration(30, new TimeUnit("minutes"));
    private static final double TIME = 30;
    private static final TimeUnit TIME_UNIT = new TimeUnit("minutes");
    private static final JsonAdaptedTimeUnit JSON_ADAPTED_TIME_UNIT = new JsonAdaptedTimeUnit(TIME_UNIT);

    @Test
    public void constructor_validRecipeDuration_returnsJsonAdaptedRecipeDuration() throws Exception {
        JsonAdaptedRecipeDuration adaptedDuration = new JsonAdaptedRecipeDuration(RECIPE_DURATION);
        assertEquals(TIME, adaptedDuration.getTime());
        assertEquals(TIME_UNIT, adaptedDuration.getTimeUnit().toModelType());
    }

    @Test
    public void constructor_validTimeAndJsonAdaptedTimeUnit_returnsJsonAdaptedRecipeDuration() throws Exception {
        JsonAdaptedRecipeDuration adaptedDuration = new JsonAdaptedRecipeDuration(TIME, JSON_ADAPTED_TIME_UNIT);
        assertEquals(TIME, adaptedDuration.getTime());
        assertEquals(TIME_UNIT, adaptedDuration.getTimeUnit().toModelType());
    }

    @Test
    public void toModelType_validJsonAdaptedRecipeDuration_returnsRecipeDuration() throws Exception {
        JsonAdaptedRecipeDuration adaptedDuration = new JsonAdaptedRecipeDuration(CACIO_E_PEPE.getDuration());
        assertEquals(CACIO_E_PEPE.getDuration(), adaptedDuration.toModelType());
    }

    @Test
    public void toModelType_invalidJsonAdaptedRecipeDuration_throwsIllegalValueException() {
        JsonAdaptedRecipeDuration adaptedDuration = new JsonAdaptedRecipeDuration(-1, JSON_ADAPTED_TIME_UNIT);
        assertThrows(IllegalValueException.class, adaptedDuration::toModelType);
    }

    @Test
    public void constructor_nullDuration_error() {
        JsonAdaptedTimeUnit nullUnit = null;
        RecipeDuration nullDuration = null;
        assertThrows(NullPointerException.class, () -> new JsonAdaptedRecipeDuration(0, nullUnit));
        assertThrows(NullPointerException.class, () -> new JsonAdaptedRecipeDuration(nullDuration));
    }
}
