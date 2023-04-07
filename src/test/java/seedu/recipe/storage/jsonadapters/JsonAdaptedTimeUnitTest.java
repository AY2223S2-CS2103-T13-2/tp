package seedu.recipe.storage.jsonadapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.unit.TimeUnit;

//@@author alson001
public class JsonAdaptedTimeUnitTest {
    private static final String VALID_UNIT = "hour";
    private static final String INVALID_UNIT = "";
    private static final String NULL_UNIT = null;

    @Test
    public void constructor_validUnitString_returnsJsonAdaptedTimeUnit() {
        JsonAdaptedTimeUnit adaptedTimeUnit = new JsonAdaptedTimeUnit(VALID_UNIT);
        assertEquals(VALID_UNIT, adaptedTimeUnit.getUnit());
    }

    @Test
    public void constructor_validTimeUnit_returnsJsonAdaptedTimeUnit() throws Exception {
        JsonAdaptedTimeUnit adaptedTimeUnit = new JsonAdaptedTimeUnit(CACIO_E_PEPE.getDuration().getTimeUnit());
        assertEquals(CACIO_E_PEPE.getDuration().getTimeUnit().toString(), adaptedTimeUnit.getUnit());
    }

    @Test
    public void toModelType_validUnit_success() throws IllegalValueException {
        JsonAdaptedTimeUnit adaptedUnit = new JsonAdaptedTimeUnit(VALID_UNIT);
        TimeUnit expectedUnit = new TimeUnit(VALID_UNIT);
        assertEquals(expectedUnit, adaptedUnit.toModelType());
    }

    @Test
    public void toModelType_invalidUnit_error() {
        assertThrows(IllegalValueException.class, () ->
            new JsonAdaptedTimeUnit(INVALID_UNIT).toModelType());
    }

    @Test
    public void constructorNullUnit_error() {
        TimeUnit nullTimeUnit = null;
        assertThrows(NullPointerException.class, () -> new JsonAdaptedTimeUnit(NULL_UNIT));
        assertThrows(NullPointerException.class, () -> new JsonAdaptedTimeUnit(nullTimeUnit));
    }
}
