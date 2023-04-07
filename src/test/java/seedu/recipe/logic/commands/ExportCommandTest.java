package seedu.recipe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.core.Config;
import seedu.recipe.logic.Logic;
import seedu.recipe.logic.LogicManager;
import seedu.recipe.model.ModelManager;
import seedu.recipe.storage.JsonRecipeBookStorage;
import seedu.recipe.storage.JsonUserPrefsStorage;
import seedu.recipe.storage.Storage;
import seedu.recipe.storage.StorageManager;

/**
 * Validates the constructor and initial behavior of this Command.
 * As Ubuntu environments do not work with TestFX, we are unable to mock the
 * behavior of this Command with Stage instances in the CI environment.
 */
public class ExportCommandTest {
    private static final Logic logic;
    static {
        Config config = new Config();

        //Initialise Model
        Storage storage = new StorageManager(new JsonRecipeBookStorage(Paths.get("stubPath")),
            new JsonUserPrefsStorage(config.getUserPrefsFilePath()));
        logic = new LogicManager(new ModelManager(), storage);
    }

    @Test
    public void constructor_nullLogic_nullPointerExceptionThrown() {
        assertThrows(NullPointerException.class, () -> new ExportCommand(null, null));
    }

    @Test
    public void constructor_validParams_doesNotThrow() {
        assertDoesNotThrow(() -> new ExportCommand(null, logic));
    }
}
