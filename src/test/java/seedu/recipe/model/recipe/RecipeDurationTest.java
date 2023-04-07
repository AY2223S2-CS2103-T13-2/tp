package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.recipe.model.recipe.unit.TimeUnit;

public class RecipeDurationTest {
    private static final String VALID_INTEGER = "1 hour";
    private static final String VALID_DECIMAL = "0.00012 years";
    private static final String VALID_FRACTION = "13/213 days";

    private static final String VALID_MULTIPLE_WHITESPACE_BETWEEN = "38.7        units";
    private static final String VALID_MULTIPLE_WORDS_UNIT = "321/22 Word onE twO three";

    private static final String INVALID_NO_TIME = "years";
    private static final String INVALID_NO_UNIT = "908.2098";
    private static final String INVALID_DECIMAL_WHITESPACE = "0 . 1233 units";
    private static final String INVALID_FRACTION_WHITESPACE = "1321 /   123     sample tesTing";

    private static final String INVALID_INTEGER_NO_WHITESPACE = "1hour";
    private static final String INVALID_DECIMAL_NO_WHITESPACE = "0.00012years";
    private static final String INVALID_FRACTION_NO_WHITESPACE = "312/32891798timeunits";

    private static final String INVALID_WHITESPACE_IN_UNIT = "321 example\ntime units";
    private static final String INVALID_CHARACTERS_IN_UNIT = "213.10000 Cycles of_the Earth's Rotation";

    @Test
    public void of_validInputs_recipeDurationCreated() {
        assertEquals(RecipeDuration.of(VALID_INTEGER), new RecipeDuration(1, new TimeUnit("hour")));
        assertEquals(RecipeDuration.of(VALID_DECIMAL), new RecipeDuration(0.00012, new TimeUnit("years")));
        assertEquals(RecipeDuration.of(VALID_FRACTION), new RecipeDuration(((double) 13) / 213, new TimeUnit("days")));

        assertEquals(RecipeDuration.of(VALID_MULTIPLE_WHITESPACE_BETWEEN),
            new RecipeDuration(38.7, new TimeUnit("units")));
        assertEquals(RecipeDuration.of(VALID_MULTIPLE_WORDS_UNIT),
            new RecipeDuration(((double) 321) / 22, new TimeUnit("Word onE twO three")));
    }

    @Test
    public void of_invalidInputs_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> RecipeDuration.of(INVALID_NO_TIME));
        assertThrows(IllegalArgumentException.class, () -> RecipeDuration.of(INVALID_NO_UNIT));
        assertThrows(IllegalArgumentException.class, () -> RecipeDuration.of(INVALID_DECIMAL_WHITESPACE));
        assertThrows(IllegalArgumentException.class, () -> RecipeDuration.of(INVALID_FRACTION_WHITESPACE));

        assertThrows(IllegalArgumentException.class, () -> RecipeDuration.of(INVALID_INTEGER_NO_WHITESPACE));
        assertThrows(IllegalArgumentException.class, () -> RecipeDuration.of(INVALID_DECIMAL_NO_WHITESPACE));
        assertThrows(IllegalArgumentException.class, () -> RecipeDuration.of(INVALID_FRACTION_NO_WHITESPACE));

        assertThrows(IllegalArgumentException.class, () -> RecipeDuration.of(INVALID_WHITESPACE_IN_UNIT));
        assertThrows(IllegalArgumentException.class, () -> RecipeDuration.of(INVALID_CHARACTERS_IN_UNIT));
    }

    @Test
    public void test_toString() {
        assertEquals("1.0 hour", new RecipeDuration(1, new TimeUnit("hour")).toString());
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
