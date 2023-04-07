package seedu.recipe.commons.exceptions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DataConversionExceptionTest {
    @Test
    public void constructor_nullParams_nullPointerExceptionThrown() {
        //Null value
        assertDoesNotThrow(() -> new DataConversionException(null));
    }

    @Test
    public void constructor_validParams_initialisesSuccessfully() {
        //Construct with test message, test Message and Cause
        String testMessage = "Test message";
        Exception wrappedException = new Exception(testMessage);
        DataConversionException e = new DataConversionException(wrappedException);
        assertEquals(String.format("%s: %s", wrappedException.getClass().getCanonicalName(), testMessage),
                e.getMessage());
        assertEquals(wrappedException, e.getCause());
    }
}
