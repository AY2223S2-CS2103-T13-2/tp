package seedu.recipe.model.recipe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.recipe.testutil.Assert.assertThrows;

public class StepTest {
    private static final String EMPTY = "";
    private static final String WHITESPACE = " ";
    private static final String NON_VALID_CHAR_SOLO = "^";
    private static final String NON_VALID_CHAR = "Oven-roasted chicken*";
    private static final String NUMBER_ONLY = "7896";
    private static final String HYPHEN_ONLY = "- New Recipe";
    private static final String ONE_TOKEN_ALPHA = "lasagna tre";
    private static final String MULTI_TOKEN_ALPHA = "pan fried beef slices";
    private static final String SINGLE_NUMBER_LEADING = "5 minute stew";
    private static final String NUMBER_CONCAT_ALPHA = "5-minute stew";
    private static final String ALPHA_CONCAT_ALPHA = "Pan-fried steak";
    private static final String CAPITAL_LETTERS = "Beef Stroganoff";
    private static final String LONG_TOKEN = "Mozzarella Sandwich with pesto aioli and oven-roasted beef";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Step(null));
    }

    @Test
    public void isValidStep() {
        // null name
        assertThrows(NullPointerException.class, () -> Step.isValidStep(null));

        // invalid name
        assertFalse(Step.isValidStep(EMPTY));
        assertFalse(Step.isValidStep(WHITESPACE));
        assertFalse(Step.isValidStep(NON_VALID_CHAR_SOLO));
        assertFalse(Step.isValidStep(NON_VALID_CHAR));
        assertFalse(Step.isValidStep(NUMBER_ONLY));
        assertFalse(Step.isValidStep(HYPHEN_ONLY));

        // valid name
        assertTrue(Step.isValidStep(ONE_TOKEN_ALPHA));
        assertTrue(Step.isValidStep(MULTI_TOKEN_ALPHA));
        assertTrue(Step.isValidStep(SINGLE_NUMBER_LEADING));
        assertTrue(Step.isValidStep(NUMBER_CONCAT_ALPHA));
        assertTrue(Step.isValidStep(ALPHA_CONCAT_ALPHA));
        assertTrue(Step.isValidStep(CAPITAL_LETTERS));
        assertTrue(Step.isValidStep(LONG_TOKEN));
    }

    @Test
    public void constructor_invalidStep_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Step(EMPTY));
    }

    @Test
    public void test_toString() {
        assertEquals(LONG_TOKEN, new Step(LONG_TOKEN).toString());
    }

    @Test
    public void test_equals() {
        //Referential Equality
        Step test = new Step(LONG_TOKEN);
        assertEquals(test, test);

        //Same name
        assertNotEquals(new Step(LONG_TOKEN), new Step(LONG_TOKEN));

        //Diff name
        assertNotEquals(new Step(LONG_TOKEN), new Step(ONE_TOKEN_ALPHA));

        //Diff type
        assertNotEquals(new Step(LONG_TOKEN), "hello");

        //null
        assertNotEquals(new Step(LONG_TOKEN), null);
    }

    @Test
    public void test_hashCode() {
        int expected = LONG_TOKEN.hashCode();
        assertEquals(expected, new Step(LONG_TOKEN).hashCode());
    }
}
