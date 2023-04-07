package seedu.recipe.commons.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IllegalValueExceptionTest {
    @Test
    public void constructor_validParams_initialiseSuccess() {
        String testMessage = "Test message";
        assertEquals(testMessage, new IllegalValueException(testMessage).getMessage());

        Exception wrappedException = new Exception(testMessage);
        assertEquals(testMessage, new IllegalValueException(testMessage, wrappedException).getMessage());
        assertEquals(wrappedException, new IllegalValueException(testMessage, wrappedException).getCause());
    }
}
