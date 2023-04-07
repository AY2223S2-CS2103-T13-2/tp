package seedu.recipe.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.recipe.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.core.GuiSettings;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setRecipeBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setRecipeBookFilePath(null));
    }

    @Test
    public void equals() {
        //Referential Equality
        UserPrefs userPrefs = new UserPrefs();
        assertEquals(userPrefs, userPrefs);
        //Different type
        assertNotEquals(userPrefs, "Hello");
    }

    @Test
    public void hashcode() {
        UserPrefs userPref = new UserPrefs();
        GuiSettings guiSettings = new GuiSettings();
        Path filePath = Paths.get("data", "recipebook.json");
        int expected = Objects.hash(guiSettings, filePath);
        assertEquals(expected, userPref.hashCode());
    }
}
