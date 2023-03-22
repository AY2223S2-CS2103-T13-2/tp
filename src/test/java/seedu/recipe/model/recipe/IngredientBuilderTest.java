package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IngredientBuilderTest {
    private static final String VALID_INTEGER = "1 watermelon";
    private static final String VALID_DECIMAL = "13.5 g molasses extract";
    private static final String VALID_INTEGER_CONCAT_UNIT = "300g rice, washed";
    private static final String VALID_DECIMAL_CONCAT_UNIT = "10.35oz. powder (can be substituted with flour)";
    private static final String VALID_ALPHA = "butter";
    private static final String TRAILING_WHITESPACE = "watermelon juice ";
    private static final String WHITESPACE = "  ";
    private static final String LEADING_WHITESPACE = " juice of 1 carrot";
    private static final String SLASH_UNIT = "1/3 cup milk";

    @Test
    public void null_name() {
        assertThrows(NullPointerException.class, () -> new IngredientBuilder(null));
    }

    @Test
    public void constructor_invalidArgs() {
        assertThrows(IllegalArgumentException.class, () -> new IngredientBuilder(TRAILING_WHITESPACE));
        assertThrows(IllegalArgumentException.class, () -> new IngredientBuilder(WHITESPACE));
        assertThrows(IllegalArgumentException.class, () -> new IngredientBuilder(LEADING_WHITESPACE));
    }

    @Test
    public void test_toString() {
        assertEquals(VALID_INTEGER_CONCAT_UNIT, new IngredientBuilder(VALID_INTEGER_CONCAT_UNIT).toString());
    }

    @Test
    public void constructor_invalidIngredient_throwsIllegalArgumentException() {
        String invalidIngredient = "";
        assertThrows(IllegalArgumentException.class, () -> new IngredientBuilder(invalidIngredient));
    }

    @Test
    public void ingredient_name_regex() {
        assertTrue(IngredientBuilder.isValidIngredient(VALID_INTEGER));
        assertTrue(IngredientBuilder.isValidIngredient(VALID_DECIMAL));
        assertTrue(IngredientBuilder.isValidIngredient(VALID_INTEGER_CONCAT_UNIT));
        assertTrue(IngredientBuilder.isValidIngredient(VALID_DECIMAL_CONCAT_UNIT));
        assertTrue(IngredientBuilder.isValidIngredient(VALID_ALPHA));
        assertTrue(IngredientBuilder.isValidIngredient(SLASH_UNIT));

        assertFalse(IngredientBuilder.isValidIngredient(TRAILING_WHITESPACE));
        assertFalse(IngredientBuilder.isValidIngredient(WHITESPACE));
        assertFalse(IngredientBuilder.isValidIngredient(LEADING_WHITESPACE));
    }

    @Test
    public void test_equals() {
        //Referential Equality
        IngredientBuilder test = new IngredientBuilder(VALID_DECIMAL_CONCAT_UNIT);
        assertEquals(test, test);

        //Same name
        assertEquals(new IngredientBuilder(VALID_INTEGER_CONCAT_UNIT),
                new IngredientBuilder(VALID_INTEGER_CONCAT_UNIT));

        //Diff name
        assertNotEquals(new IngredientBuilder(VALID_INTEGER_CONCAT_UNIT),
                new IngredientBuilder(VALID_DECIMAL_CONCAT_UNIT));

        //Diff type
        assertNotEquals(new IngredientBuilder(VALID_DECIMAL), "hello");

        //null
        assertNotEquals(new IngredientBuilder(VALID_DECIMAL), null);
    }

    @Test
    public void test_hashCode() {
        int expected = VALID_DECIMAL_CONCAT_UNIT.hashCode();
        assertEquals(expected, new IngredientBuilder(VALID_DECIMAL_CONCAT_UNIT).hashCode());
    }
}
