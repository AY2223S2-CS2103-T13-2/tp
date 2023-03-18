package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {
    private static final String EMPTY = "";
    private static final String WHITESPACE = " ";
    private static final String NON_VALID_CHAR_SOLO = "^";
    private static final String NON_VALID_CHAR = "Oven-roasted chicken*";
    private static final String NUMBER_ONLY = "7896";
    private static final String HYPHEN_ONLY = "- New Recipe";
    private static final String ONE_TOKEN_ALPHA = "lasagna";
    private static final String MULTI_TOKEN_ALPHA = "pan fried beef slices";
    private static final String SINGLE_NUMBER_LEADING = "5 minute stew";
    private static final String NUMBER_CONCAT_ALPHA = "5-minute stew";
    private static final String ALPHA_CONCAT_ALPHA = "Pan-fried steak";
    private static final String CAPITAL_LETTERS = "Beef Stroganoff";
    private static final String LONG_TOKEN = "Mozzarella Sandwich with pesto aioli and oven-roasted beef";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName(EMPTY));
        assertFalse(Name.isValidName(WHITESPACE));
        assertFalse(Name.isValidName(NON_VALID_CHAR_SOLO));
        assertFalse(Name.isValidName(NON_VALID_CHAR));
        assertFalse(Name.isValidName(NUMBER_ONLY));
        assertFalse(Name.isValidName(HYPHEN_ONLY));

        // valid name
        assertTrue(Name.isValidName(ONE_TOKEN_ALPHA));
        assertTrue(Name.isValidName(MULTI_TOKEN_ALPHA));
        assertTrue(Name.isValidName(SINGLE_NUMBER_LEADING));
        assertTrue(Name.isValidName(NUMBER_CONCAT_ALPHA));
        assertTrue(Name.isValidName(ALPHA_CONCAT_ALPHA));
        assertTrue(Name.isValidName(CAPITAL_LETTERS));
        assertTrue(Name.isValidName(LONG_TOKEN));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Name(EMPTY));
    }

    @Test
    public void test_toString() {
        assertEquals(LONG_TOKEN, new Name(LONG_TOKEN).toString());
    }

    @Test
    public void test_equals() {
        //Referential Equality
        Name test = new Name(LONG_TOKEN);
        assertEquals(test, test);

        //Same name
        assertEquals(new Name(LONG_TOKEN), new Name(LONG_TOKEN));

        //Diff name
        assertNotEquals(new Name(LONG_TOKEN), new Name(ONE_TOKEN_ALPHA));

        //Diff type
        assertNotEquals(new Name(LONG_TOKEN), "hello");

        //null
        assertNotEquals(new Name(LONG_TOKEN), null);
    }

    @Test
    public void test_hashCode() {
        int expected = LONG_TOKEN.hashCode();
        assertEquals(expected, new Name(LONG_TOKEN).hashCode());
    }
}
