package seedu.recipe.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class ConfigTest {
    //Test base config
    private static final String USER_PREFS_FILE_PATH_STRING = "preferences.json";
    private static final Path USER_PREFS_FILE_PATH = Paths.get(USER_PREFS_FILE_PATH_STRING);
    private static final Level CONFIG_BASE_LEVEL = Level.INFO;
    private static final String DEFAULT_CONFIG_STRING = String.format("Current log level : %s\n"
        + "Preference file Location : %s", CONFIG_BASE_LEVEL, USER_PREFS_FILE_PATH_STRING);

    @Test
    public void getLogLevel_initialLevel_baseLevel() {
        Config defaultConfig = new Config();
        assertEquals(defaultConfig.getLogLevel(), CONFIG_BASE_LEVEL);
    }

    @Test
    public void setLogLevel_levelWarning_setSuccess() {
        Config defaultConfig = new Config();
        defaultConfig.setLogLevel(Level.WARNING);
        assertEquals(defaultConfig.getLogLevel(), Level.WARNING);
    }

    @Test
    public void getUserPrefsPath_initialValue_matchesConfigInitialValue() {
        assertEquals(USER_PREFS_FILE_PATH, new Config().getUserPrefsFilePath());
    }

    @Test
    public void setUserPrefsFilePath_setNewPathValue_setSuccess() {
        Path newPath = Paths.get("null path");
        Config defaultConfig = new Config();
        defaultConfig.setUserPrefsFilePath(newPath);
        assertEquals(defaultConfig.getUserPrefsFilePath(), newPath);
    }

    @Test
    public void equals() {
        Config defaultConfig = new Config();
        assertNotNull(defaultConfig);
        assertEquals(defaultConfig, defaultConfig);
    }

    @Test
    public void hashCode_initialValue_matchesInitialValuesHash() {
        assertEquals(Objects.hash(CONFIG_BASE_LEVEL, USER_PREFS_FILE_PATH), new Config().hashCode());
    }

    @Test
    public void toString_value_matchesConfigStringValue() {
        assertEquals(DEFAULT_CONFIG_STRING, new Config().toString());
    }
}
