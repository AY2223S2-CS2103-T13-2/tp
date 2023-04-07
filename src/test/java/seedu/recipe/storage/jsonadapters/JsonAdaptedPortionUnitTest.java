package seedu.recipe.storage.jsonadapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.unit.PortionUnit;

//@@author alson001
public class JsonAdaptedPortionUnitTest {
    private static final String VALID_UNIT = "grams";
    private static final String INVALID_UNIT = "";
    private static final String NULL_UNIT = null;

    @Test
    public void constructor_validUnit_returnsJsonAdaptedPortionUnitTest() {
        PortionUnit modelUnit = new PortionUnit(VALID_UNIT);
        JsonAdaptedPortionUnit adaptedUnit = new JsonAdaptedPortionUnit(modelUnit);
        assertEquals(VALID_UNIT, adaptedUnit.getUnit());
    }

    @Test
    public void toModelType_validUnit_success() throws IllegalValueException {
        JsonAdaptedPortionUnit adaptedUnit = new JsonAdaptedPortionUnit(VALID_UNIT);
        PortionUnit modelUnit = adaptedUnit.toModelType();
        assertEquals(VALID_UNIT, modelUnit.getUnit());
    }

    @Test
    public void toModelType_invalidUnit_error() {
        assertThrows(IllegalValueException.class, () ->
            new JsonAdaptedPortionUnit(INVALID_UNIT).toModelType());
    }

    @Test
    public void constructor_nullUnit_error() {
        PortionUnit nullUnit = null;
        assertThrows(NullPointerException.class, () ->
            new JsonAdaptedPortionUnit(NULL_UNIT));
        assertThrows(NullPointerException.class, () ->
            new JsonAdaptedPortionUnit(nullUnit));
    }
}
