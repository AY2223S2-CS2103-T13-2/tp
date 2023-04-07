package seedu.recipe.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ArgumentMultiMapTest {
    private static final Prefix TEST_PREFIX = new Prefix("");
    private static final Prefix ALPHA_PREFIX = new Prefix("alpha/");

    @Test
    public void contains_insertValues_indicatesContains() {
        ArgumentMultimap map = new ArgumentMultimap();
        assertFalse(map.containsKey(TEST_PREFIX));
        map.put(TEST_PREFIX, "");
        assertTrue(map.containsKey(TEST_PREFIX));
    }

    @Test
    public void putArguments_getAllValues_returnsAll() {
        ArgumentMultimap map = new ArgumentMultimap();
        List<String> values = List.of("string one", "string two");
        assertDoesNotThrow(() -> map.put(ALPHA_PREFIX, values.get(0)));
        assertDoesNotThrow(() -> map.put(ALPHA_PREFIX, values.get(1)));

        //Test if values were all inserted
        assertEquals(map.getAllValues(ALPHA_PREFIX), values);
    }

    @Test
    public void getValue_getsLastValue() {
        ArgumentMultimap map = new ArgumentMultimap();
        map.put(ALPHA_PREFIX, "value 1");
        map.put(ALPHA_PREFIX, "value 2");
        map.put(ALPHA_PREFIX, "value 3");
        assertFalse(map.getValue(ALPHA_PREFIX).isEmpty());
        assertEquals(map.getValue(ALPHA_PREFIX).get(), "value 3");
    }

    @Test
    public void getPreamble() {
        ArgumentMultimap map = new ArgumentMultimap();
        map.put(TEST_PREFIX, "add");
        map.put(new Prefix("n/"), "Recipe Name");
        assertEquals(map.getPreamble(), "add");
    }
}
