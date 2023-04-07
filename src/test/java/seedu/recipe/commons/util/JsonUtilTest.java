package seedu.recipe.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.recipe.testutil.TypicalRecipes.getTypicalRecipeBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.DataConversionException;
import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.storage.JsonSerializableRecipeBook;
import seedu.recipe.testutil.SerializableTestClass;
import seedu.recipe.testutil.TestUtil;

/**
 * Tests JSON Read and Write
 */
public class JsonUtilTest {

    private static final Path SERIALIZATION_FILE = TestUtil.getFilePathInSandboxFolder("serialize.json");
    private static final Path TYPICAL_RECIPE_BOOK = Paths.get("src", "test", "data",
        "JsonSerializableRecipeBookTest", "typicalRecipeRecipeBook.json");

    @Test
    public void serializeObjectToJsonFile_noExceptionThrown() throws IOException {
        SerializableTestClass serializableTestClass = new SerializableTestClass();
        serializableTestClass.setTestValues();

        JsonUtil.serializeObjectToJsonFile(SERIALIZATION_FILE, serializableTestClass);

        assertEquals(FileUtil.readFromFile(SERIALIZATION_FILE), SerializableTestClass.JSON_STRING_REPRESENTATION);
    }

    @Test
    public void deserializeObjectFromJsonFile_noExceptionThrown() throws IOException {
        FileUtil.writeToFile(SERIALIZATION_FILE, SerializableTestClass.JSON_STRING_REPRESENTATION);

        SerializableTestClass serializableTestClass = JsonUtil
                .deserializeObjectFromJsonFile(SERIALIZATION_FILE, SerializableTestClass.class);

        assertEquals(serializableTestClass.getName(), SerializableTestClass.getNameTestValue());
        assertEquals(serializableTestClass.getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(serializableTestClass.getMapOfIntegerToString(), SerializableTestClass.getHashMapTestValues());
    }

    @Test
    public void readJsonFile() {
        //Test read valid file
        try {
            Optional<JsonSerializableRecipeBook> serializableRecipeBook =
                JsonUtil.readJsonFile(TYPICAL_RECIPE_BOOK, JsonSerializableRecipeBook.class);
            assertEquals(getTypicalRecipeBook(), serializableRecipeBook.get().toModelType());
        } catch (DataConversionException | IllegalValueException e) {
            fail();
        }
        //Test null
        assertThrows(NullPointerException.class, () -> JsonUtil.readJsonFile(null, Object.class));

        //Test does not exist
        try {
            assertTrue(JsonUtil.readJsonFile(Paths.get("doesNotExist"), Object.class).isEmpty());
        } catch (DataConversionException ignored) { // Will never reach here, as file does not exist.
            fail();
        }
    }
}
