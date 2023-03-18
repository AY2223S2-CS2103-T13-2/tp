package seedu.recipe.model.recipe;

import org.junit.jupiter.api.Test;
import seedu.recipe.model.recipe.exceptions.RecipeDurationInvalidArgumentLengthException;
import seedu.recipe.model.recipe.exceptions.RecipeDurationInvalidDurationException;
import seedu.recipe.model.recipe.unit.TimeUnit;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeDurationTest {
    private static final String VALID_SIMPLE = "1 hour";
    private static final String VALID_PLURAL = "2 hours";
    private static final String DECIMAL_PLURAL_END = "1.5 hours";
    private static final String DECIMAL_LT_ONE = "0.005 hours";
    private static final String VALID_DECIMAL = "10.000 hours";
    private static final String SECONDS = "30 seconds";
    private static final String MINUTES = "25 minutes";
    private static final String DAY = "1 day";

    private static final String SINGLE_PLURAL_END = "1 hours";
    private static final String INVALID_UNIT = "1 run";
    private static final String INVALID_FRACTION = "1/2 hour";
    private static final String NO_NUMBER = "hour";
    private static final String ZERO_DECIMAL = "0.000 hours";
    private static final String NO_WHITESPACE = "1hour";
    private static final String LONG_DECIMAL = "1.0000 hours";
    private static final String LONG_DECIMAL_2 = "2.0000 hours";
    private static final String LARGE_NUM_2 = "2000 hours";
    private static final String LARGE_NUM = "1000 hours";
    private static final String NON_ONE_NON_PLURAL = "10 hour";

    //Future support is intended for this, but the recipe duration should be a fixed estimate.
    private static final String RANGE_UNIT = "2 - 3 hours";

    @Test
    public void test_null_constructor() {
        assertThrows(NullPointerException.class, () -> new RecipeDuration(0, null));
    }

    @Test
    public void test_regex() {
        assertFalse(RecipeDuration.isValidRecipeDuration(INVALID_UNIT));
        assertFalse(RecipeDuration.isValidRecipeDuration(SINGLE_PLURAL_END));
        assertFalse(RecipeDuration.isValidRecipeDuration(RANGE_UNIT));
        assertFalse(RecipeDuration.isValidRecipeDuration(INVALID_FRACTION));
        assertFalse(RecipeDuration.isValidRecipeDuration(NO_NUMBER));
        assertFalse(RecipeDuration.isValidRecipeDuration(ZERO_DECIMAL));
        assertFalse(RecipeDuration.isValidRecipeDuration(NO_WHITESPACE));
        assertFalse(RecipeDuration.isValidRecipeDuration(LONG_DECIMAL));
        assertFalse(RecipeDuration.isValidRecipeDuration(LONG_DECIMAL_2));
        assertFalse(RecipeDuration.isValidRecipeDuration(LARGE_NUM));
        assertFalse(RecipeDuration.isValidRecipeDuration(LARGE_NUM_2));
        assertFalse(RecipeDuration.isValidRecipeDuration(NON_ONE_NON_PLURAL));

        assertTrue(RecipeDuration.isValidRecipeDuration(VALID_DECIMAL));
        assertTrue(RecipeDuration.isValidRecipeDuration(VALID_SIMPLE));
        assertTrue(RecipeDuration.isValidRecipeDuration(VALID_PLURAL));
        assertTrue(RecipeDuration.isValidRecipeDuration(DECIMAL_PLURAL_END));
        assertTrue(RecipeDuration.isValidRecipeDuration(DECIMAL_LT_ONE));
        assertTrue(RecipeDuration.isValidRecipeDuration(DAY));
        assertTrue(RecipeDuration.isValidRecipeDuration(MINUTES));
        assertTrue(RecipeDuration.isValidRecipeDuration(SECONDS));
    }

    @Test
    public void test_wrong_arguments_constructor() {
        assertThrows(IllegalArgumentException.class, () -> new RecipeDuration(0.00, new TimeUnit("hour")));
        assertThrows(IllegalArgumentException.class, () -> new RecipeDuration(-1.00, new TimeUnit("hour")));
    }

    @Test
    public void test_toString() {
        assertEquals("1.0 hour", new RecipeDuration(1, new TimeUnit("hour")).toString());
    }

    @Test
    public void testFactory() {
        //Too few arguments
        assertThrows(RecipeDurationInvalidArgumentLengthException.class,
                () -> RecipeDuration.of(NO_WHITESPACE)
        );
        //Too many arguments
        assertThrows(RecipeDurationInvalidArgumentLengthException.class,
                () -> RecipeDuration.of("13 days in the winter")
        );
        //Invalid duration
        assertThrows(RecipeDurationInvalidDurationException.class,
                () -> RecipeDuration.of(INVALID_FRACTION));

        assertEquals(
                RecipeDuration.of(VALID_PLURAL).toString(),
                new RecipeDuration(2, new TimeUnit("hours")).toString());
    }

    @Test
    public void test_hashCode() {
        TimeUnit t = new TimeUnit("hours");
        assertEquals(Objects.hash(2.0, t), new RecipeDuration(2.0, t).hashCode());
    }

    @Test
    public void test_getTime() {
        assertEquals(2.0, RecipeDuration.of("2 hours").getTime());
    }

    @Test
    public void test_timeUnit() {
        TimeUnit t = new TimeUnit("hour");
        assertEquals(t, new RecipeDuration(1, t).getTimeUnit());
    }
}
