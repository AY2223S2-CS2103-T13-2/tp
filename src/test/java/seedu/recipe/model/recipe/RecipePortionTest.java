package seedu.recipe.model.recipe;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.recipe.model.recipe.exceptions.RecipePortionInvalidArgumentException;
import seedu.recipe.model.recipe.unit.PortionUnit;
import static seedu.recipe.testutil.Assert.assertThrows;

public class RecipePortionTest {
    private static final String RANGE_CONCAT = "1-2 servings";
    private static final String RANGE_WHITESPACE = "1  - 2 servings";
    private static final String TO_WHITESPACE = "1 to 2 servings";
    private static final String SIMPLE_LOWER_RANGE = "1 serving";
    private static final String SIMPLE_LOWER_RANGE_PLURAL = "2 servings";

    private static final String EMPTY = "";
    private static final String SINGLE_PLURAL_ADDEND = "1 servings";
    private static final String PLURAL_NO_ADDEND = "2 portion";
    private static final String TO_NO_WHITESPACE = "1to2 servings";
    private static final String INVALID_RANGE = "3 to 1 servings";

    @Test
    public void test_null_constructor() {
        assertThrows(NullPointerException.class, () -> new RecipePortion(0, 0, null));
    }

    @Test
    public void isValidRecipePortion() {
        assertFalse(RecipePortion.isValidRecipePortion(EMPTY));
        assertFalse(RecipePortion.isValidRecipePortion(SINGLE_PLURAL_ADDEND));
        assertFalse(RecipePortion.isValidRecipePortion(PLURAL_NO_ADDEND));
        assertFalse(RecipePortion.isValidRecipePortion(TO_NO_WHITESPACE));
        assertFalse(RecipePortion.isValidRecipePortion(INVALID_RANGE));

        assertTrue(RecipePortion.isValidRecipePortion(RANGE_CONCAT));
        assertTrue(RecipePortion.isValidRecipePortion(RANGE_WHITESPACE));
        assertTrue(RecipePortion.isValidRecipePortion(TO_WHITESPACE));
        assertTrue(RecipePortion.isValidRecipePortion(SIMPLE_LOWER_RANGE));
        assertTrue(RecipePortion.isValidRecipePortion(SIMPLE_LOWER_RANGE_PLURAL));
    }

    @Test
    public void testConstructorValid() {
        //Redirection if invalid params
        assertThrows(IllegalArgumentException.class, () -> new RecipePortion(0, 0, new PortionUnit("min")));

        assertDoesNotThrow(() -> new RecipePortion(1, -1, new PortionUnit("serving")));
        assertDoesNotThrow(() -> new RecipePortion(1, 3, new PortionUnit("portions")));
    }

    @Test
    public void testFactory() {
        //Redirection if regex fail
        assertThrows(RecipePortionInvalidArgumentException.class, () -> RecipePortion.of(TO_NO_WHITESPACE));

        assertDoesNotThrow(() -> RecipePortion.of(RANGE_CONCAT));
        assertDoesNotThrow(() -> RecipePortion.of(SIMPLE_LOWER_RANGE));
    }

    @Test
    public void testToString() {
        assertEquals(
                new RecipePortion(1, -1, new PortionUnit("portion")).toString(),
                "1 portion"
        );
        assertEquals(
                new RecipePortion(1, 3, new PortionUnit("portions")).toString(),
                "1 - 3 portions"
        );
    }

    @Test
    public void test_hashCode() {
        PortionUnit p = new PortionUnit("portions");
        assertEquals(Objects.hash(1, 3, p), new RecipePortion(1, 3, p).hashCode());
    }

    @Test
    public void test_getters() {
        PortionUnit p = new PortionUnit("servings");
        assertEquals(new RecipePortion(1, 3, p).getPortionUnit(), p);
        assertEquals(new RecipePortion(1, 3, p).getUpperRange(), 3);
        assertEquals(new RecipePortion(1, 3, p).getLowerRange(), 1);
        assertEquals(new RecipePortion(1, -1, new PortionUnit("portion")).getLowerRange(), 1);
        assertEquals(new RecipePortion(1, -1, new PortionUnit("portion")).getUpperRange(), -1);
    }
}
