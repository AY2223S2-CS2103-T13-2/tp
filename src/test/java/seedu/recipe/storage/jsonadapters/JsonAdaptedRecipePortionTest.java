package seedu.recipe.storage.jsonadapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.unit.PortionUnit;

//@@author alson001
public class JsonAdaptedRecipePortionTest {
    private static final JsonAdaptedPortionUnit JSON_ADAPTED_PORTION_UNIT = new JsonAdaptedPortionUnit("people");

    @Test
    public void constructor_validRecipePortion_returnsJsonAdaptedRecipePortion() throws Exception {
        JsonAdaptedRecipePortion adaptedRecipePortion = new JsonAdaptedRecipePortion(CACIO_E_PEPE.getPortion());
        assertEquals(CACIO_E_PEPE.getPortion().getLowerRange(), adaptedRecipePortion.getLowerRange());
        assertEquals(CACIO_E_PEPE.getPortion().getUpperRange(), adaptedRecipePortion.getUpperRange());
        assertEquals(CACIO_E_PEPE.getPortion().getPortionUnit(), adaptedRecipePortion.getPortionUnit().toModelType());
    }

    @Test
    public void constructor_validRangeAndJsonAdaptedPortionUnit_returnsJsonAdaptedRecipePortion() {
        JsonAdaptedRecipePortion adaptedRecipePortion =
            new JsonAdaptedRecipePortion(1, 2, JSON_ADAPTED_PORTION_UNIT);
        assertEquals(1, adaptedRecipePortion.getLowerRange());
        assertEquals(2, adaptedRecipePortion.getUpperRange());
        assertEquals(JSON_ADAPTED_PORTION_UNIT, adaptedRecipePortion.getPortionUnit());
    }

    @Test
    public void toModelType_validRecipePortion_returnsRecipePortion() throws Exception {
        RecipePortion recipePortion = new RecipePortion(2, 4, new PortionUnit("people"));
        JsonAdaptedRecipePortion adapted = new JsonAdaptedRecipePortion(recipePortion);
        assertEquals(recipePortion, adapted.toModelType());
    }

    @Test
    public void toModelType_invalidLowerRange_throwsIllegalValueException() {
        JsonAdaptedRecipePortion adapted =
            new JsonAdaptedRecipePortion(-1, 2, new JsonAdaptedPortionUnit("serving"));
        assertThrows(IllegalValueException.class, adapted::toModelType);
    }

    @Test
    public void toModelType_invalidUpperRange_throwsIllegalValueException() {
        JsonAdaptedRecipePortion adapted =
            new JsonAdaptedRecipePortion(4, 2, new JsonAdaptedPortionUnit("serving"));
        assertThrows(IllegalValueException.class, adapted::toModelType);
    }

    @Test
    public void constructor_nullPortion_error() {
        assertThrows(NullPointerException.class, () -> new JsonAdaptedRecipePortion(4, 2, null));
        assertThrows(NullPointerException.class, () -> new JsonAdaptedRecipePortion(null));
    }
}
