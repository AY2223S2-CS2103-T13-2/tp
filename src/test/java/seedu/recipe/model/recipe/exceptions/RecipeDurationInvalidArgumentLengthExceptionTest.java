package seedu.recipe.model.recipe.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.recipe.model.recipe.RecipeDuration;

public class RecipeDurationInvalidArgumentLengthExceptionTest {

    private static final String EXPECTED_MESSAGE = "An argument list of invalid length was passed "
        + "for a Recipe Duration." + "\nEnsure it is of the following format: '{number OR decimal} {duration}'; "
        + "\nExample: `1 minute`, `1.5 hours`";

    @Test
    public void validRecipeDurationInvalidArgumentLengthException() {
        Exception exception = assertThrows(RecipeDurationInvalidArgumentLengthException.class, () ->
                RecipeDuration.of("15"));
        assertEquals(EXPECTED_MESSAGE, exception.getMessage());
    }
}
