package seedu.recipe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * Validates the constructor and initial behavior of this Command.
 * As Ubuntu environments do not work with TestFX, we are unable to mock the
 * behavior of this Command with Stage instances in the CI environment.
 */
public class ImportCommandTest {
    @Test
    public void constructor_noError() {
        assertDoesNotThrow(() -> new ImportCommand(null));
    }

    @Test
    public void isDuplicate_initial_isFalse() {
        assertFalse(new ImportCommand(null).isDuplicate());
    }
}
