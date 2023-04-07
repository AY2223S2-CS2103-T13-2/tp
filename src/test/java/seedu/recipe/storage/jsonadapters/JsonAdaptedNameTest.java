package seedu.recipe.storage.jsonadapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_NAME;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Name;

//@@author alson001
public class JsonAdaptedNameTest {
    private static final String VALID_NAME = "Cheeseburger";
    private static final String INVALID_NAME = "Burger#Fried";
    private static final String NULL_NAME = null;

    @Test
    public void constructor_validStringName_returnsJsonAdaptedName() {
        JsonAdaptedName jsonAdaptedName = new JsonAdaptedName(VALID_NAME);
        assertEquals(VALID_NAME, jsonAdaptedName.getName());
    }

    @Test
    public void constructor_validName_returnsJsonAdaptedName() {
        JsonAdaptedName jsonAdaptedName = new JsonAdaptedName(CACIO_E_PEPE.getName());
        assertEquals(CACIO_NAME.toString(), jsonAdaptedName.getName());
    }

    @Test
    public void toModelType_validName_returnsName() throws IllegalValueException {
        JsonAdaptedName jsonAdaptedName = new JsonAdaptedName(VALID_NAME);
        assertEquals(new Name(VALID_NAME), jsonAdaptedName.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedName jsonAdaptedName = new JsonAdaptedName(INVALID_NAME);
        assertThrows(IllegalValueException.class, jsonAdaptedName::toModelType);
    }

    @Test
    public void constructorNullName_error() {
        assertThrows(NullPointerException.class, () -> new JsonAdaptedName(NULL_NAME));
    }

    @Test
    public void constructorNullRecipeName_error() {
        Name nullName = null;
        assertThrows(NullPointerException.class, () -> new JsonAdaptedName(nullName));
    }
}
