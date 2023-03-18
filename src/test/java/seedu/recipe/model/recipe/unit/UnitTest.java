package seedu.recipe.model.recipe.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UnitTest {
    private static final Unit STUB = new Unit("unitName") {};

    @Test
    public void testToString() {
        assertEquals("unitName", STUB.toString());
    }

    @Test
    public void testGetUnit() {
        assertEquals("unitName", STUB.getUnit());
    }
}
