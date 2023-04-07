package seedu.recipe.storage.filemanagers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.recipe.commons.core.Config;
import seedu.recipe.logic.Logic;
import seedu.recipe.logic.LogicManager;
import seedu.recipe.model.Model;
import seedu.recipe.model.ModelManager;
import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.UserPrefs;
import seedu.recipe.storage.JsonRecipeBookStorage;
import seedu.recipe.storage.JsonUserPrefsStorage;
import seedu.recipe.storage.Storage;
import seedu.recipe.storage.StorageManager;
import seedu.recipe.testutil.TypicalRecipes;

public class ExportManagerTest {
    //Set up storage folder
    public static final Path TEMP_FOLDER = Paths.get("src", "test", "data", "exportManagerTest");
    private static final Path TEMP_DATA_FILE = TEMP_FOLDER.resolve("testBook.json");

    //Set up RecipeBook Logic using the TypicalRecipeBook
    private static final RecipeBook recipeBook = TypicalRecipes.getTypicalRecipeBook();
    private static final UserPrefs userPrefs = new UserPrefs();

    //Set up app config
    private static final Logic logic;
    private static final Storage storage;
    //Set up Logic
    static {
        Config config = new Config();

        //Initialise Model
        Model model = new ModelManager(recipeBook, userPrefs);
        storage = new StorageManager(new JsonRecipeBookStorage(TEMP_DATA_FILE),
                new JsonUserPrefsStorage(config.getUserPrefsFilePath()));

        logic = new LogicManager(model, storage);
    }
    private static final ExportManager MANAGER = new ExportManager(null, logic);

    //As TestFX GUI FxRobot cannot interact with the system file dialog,
    //the behavior of the following methods cannot be mocked:
    //- ExportManager::createFile
    //- ExportManager::execute
    @Test
    public void test_constructorNullLogicInstance() {
        Logic nullLogic = null;
        assertThrows(IllegalArgumentException.class, () -> new ExportManager(null, nullLogic));
    }

    /**
     * Tests the export logic when the current RecipeBook file does not yet exist.
     */
    @Test
    public void test_writeToFileUsingLogic() {
        try {
            assertFalse(TEMP_DATA_FILE.toFile().exists());
            MANAGER.writeToFileUsingLogic(TEMP_DATA_FILE.toFile());
            assertTrue(TEMP_DATA_FILE.toFile().exists());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests the export logic using a selected File descriptor.
     * This usually results in one of two outcomes:
     * 1) The File exists -> writeToFile is used
     * 2) The File does not exist -> writeToFileUsingLogic is used.
     */
    @Test
    public void test_writeToFile() {
        //Test Setup
        assertFalse(TEMP_DATA_FILE.toFile().exists());
        //If false, the other path will get activated
        if (!userPrefs.getRecipeBookFilePath().toFile().exists()) {
            try {
                storage.saveRecipeBook(recipeBook);
            } catch (IOException e) {
                fail("Testcase could not be set up");
            }
        }
        try {
            if (!TEMP_DATA_FILE.toFile().exists()) {
                boolean created = TEMP_DATA_FILE.toFile().createNewFile();
                if (!created) {
                    fail("File '" + TEMP_DATA_FILE + "' could not be created");
                }
            }
            MANAGER.writeToFile(TEMP_DATA_FILE.toFile());
            assertTrue(TEMP_DATA_FILE.toFile().exists());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_redirectCreateUsingLogic() {
        //Test Setup
        //1. Delete current book
        try {
            if (userPrefs.getRecipeBookFilePath().toFile().exists()) {
                Files.delete(userPrefs.getRecipeBookFilePath());
            }
        } catch (IOException e) {
            fail("Testcase could not be set up");
        }

        //Actual test
        try {
            assertFalse(TEMP_DATA_FILE.toFile().exists());
            MANAGER.writeToFile(TEMP_DATA_FILE.toFile());
            assertTrue(TEMP_DATA_FILE.toFile().exists());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Create the parent directory to host these test files.
     */
    @BeforeEach
    public void createDir() {
        try {
            Files.createDirectory(TEMP_FOLDER);
        } catch (IOException e) {
            //do nothing
        }
    }

    /**
     * Clear all created files, so that each test is deterministic.
     */
    @AfterEach
    public void clearFiles() {
        try {
            Files.delete(TEMP_DATA_FILE);
            Files.delete(TEMP_FOLDER);
        } catch (IOException e) {
            //Do nothing
        }
    }
}
