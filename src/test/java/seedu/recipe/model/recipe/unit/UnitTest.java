package seedu.recipe.model.recipe.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class UnitTest {
    private static final Unit STUB = new Unit("unitName") {
    };

    @Test
    public void testToString() {
        assertEquals("unitName", STUB.toString());
    }

    @Test
    public void testGetUnit() {
        assertEquals("unitName", STUB.getUnit());
    }

    @Test
    public void equals() {
        // same object -> return true
        assertEquals(STUB, STUB);

        // null -> return false
        assertNotEquals(null, STUB);

        Unit matchingStub = new Unit("unitName") {};
        assertEquals(STUB, matchingStub);
    }
}
