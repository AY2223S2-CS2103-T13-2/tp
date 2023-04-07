package seedu.recipe.commons.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.recipe.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Contains test cases that validate the FileUtils class and its methods'
 * behavior.
 * If the developer modifies those behaviors, they are to modify these
 * test cases as well before their code is merged into the production build.
 */
public class FileUtilTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableRecipeBookTest");
    private static final Path VALID_PATH = TEST_DATA_FOLDER.resolve("typicalRecipeRecipeBook.json");

    private static final Path TEST_NEW_FOLDER = Paths.get("src", "test", "data", "fileUtils");
    private static final Path TEST_NEW_FILE = TEST_NEW_FOLDER.resolve("test.txt");

    @AfterEach
    public void deleteTestFiles() {
        try {
            Files.delete(TEST_NEW_FILE);
            Files.delete(TEST_NEW_FOLDER);
        } catch (IOException ignored) {
            //Pass, already deleted
        }
    }

    @Test
    public void isFileExists() {
        //Valid file exists
        assertTrue(FileUtil.isFileExists(VALID_PATH));

        //irregular file (Folder), exists
        assertFalse(FileUtil.isFileExists(TEST_DATA_FOLDER));

        //File does not exist
        assertFalse(FileUtil.isFileExists(Paths.get("doesNotExist")));
    }

    @Test
    public void isValidPath() {
        // valid path
        assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));
    }

    @Test
    public void createFile_createParentFile() {
        //New file, both parent folder and file should not error
        assertDoesNotThrow(() -> FileUtil.createFile(TEST_NEW_FILE));

        //Files should exist
        assertTrue(FileUtil.isFileExists(TEST_NEW_FILE));
    }

    @Test
    public void createIfMissing() {
        //Create, and test that it is created.
        assertDoesNotThrow(() -> FileUtil.createIfMissing(TEST_NEW_FILE));
        assertTrue(FileUtil.isFileExists(TEST_NEW_FILE));
    }

    @Test
    public void readWrite() {
        String testString = "hello world";
        assertDoesNotThrow(() -> FileUtil.createFile(TEST_NEW_FILE));

        //Writing to a valid file should work
        assertDoesNotThrow(() -> FileUtil.writeToFile(TEST_NEW_FILE, testString));

        //Validate what was written
        assertDoesNotThrow(() -> FileUtil.readFromFile(TEST_NEW_FILE));

        try {
            assertEquals(testString, FileUtil.readFromFile(TEST_NEW_FILE));
        } catch (IOException e) {
            fail();
        }
    }
}
