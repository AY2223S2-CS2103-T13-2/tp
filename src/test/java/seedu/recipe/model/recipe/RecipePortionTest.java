package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.recipe.testutil.Assert.assertThrows;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.recipe.model.recipe.unit.PortionUnit;

public class RecipePortionTest {
    // valid cases
    private static final String DASH_CONCAT = "1-2 servings";
    private static final String TO_CONCAT = "1-2 servings";
    private static final String DASH_WHITESPACE = "1  - 2 servings";
    private static final String TO_WHITESPACE = "1 to   2 people";
    private static final String SIMPLE_LOWER_RANGE = "13 serving sizes";

    // invalid cases
    private static final String INVALID_EMPTY = "";
    private static final String INVALID_NO_RANGE = "people pleasers";
    private static final String INVALID_NO_UNIT = "3 to 5";
    private static final String INVALID_RANGE = "3 to 1 servings";
    private static final String INVALID_SIMPLE = "3.123 servings";
    private static final String INVALID_DASH = "1.5 - 3321 servings";
    private static final String INVALID_TO = "2    to 33.21 servings";
    private static final String INVALID_UNIT = "13 - 15 of Granny's Friends";

    @Test
    public void of_validInputs_recipePortionCreated() {
        assertEquals(RecipePortion.of(DASH_CONCAT), new RecipePortion(1, 2, new PortionUnit("servings")));
        assertEquals(RecipePortion.of(TO_CONCAT), new RecipePortion(1, 2, new PortionUnit("servings")));
        assertEquals(RecipePortion.of(DASH_WHITESPACE), new RecipePortion(1, 2, new PortionUnit("servings")));
        assertEquals(RecipePortion.of(TO_WHITESPACE), new RecipePortion(1, 2, new PortionUnit("people")));
        assertEquals(RecipePortion.of(SIMPLE_LOWER_RANGE), new RecipePortion(13, 0, new PortionUnit("serving sizes")));
    }

    @Test
    public void of_invalidInputs_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> RecipePortion.of(INVALID_EMPTY));
        assertThrows(IllegalArgumentException.class, () -> RecipePortion.of(INVALID_NO_RANGE));
        assertThrows(IllegalArgumentException.class, () -> RecipePortion.of(INVALID_NO_UNIT));
        assertThrows(IllegalArgumentException.class, () -> RecipePortion.of(INVALID_RANGE));
        assertThrows(IllegalArgumentException.class, () -> RecipePortion.of(INVALID_SIMPLE));
        assertThrows(IllegalArgumentException.class, () -> RecipePortion.of(INVALID_EMPTY));
        assertThrows(IllegalArgumentException.class, () -> RecipePortion.of(INVALID_DASH));
        assertThrows(IllegalArgumentException.class, () -> RecipePortion.of(INVALID_TO));
        assertThrows(IllegalArgumentException.class, () -> RecipePortion.of(INVALID_UNIT));

    }

    @Test
    public void testToString() {
        assertEquals(
            new RecipePortion(1, 0, new PortionUnit("portion")).toString(), "1 portion");
        assertEquals(new RecipePortion(1, 3, new PortionUnit("portions")).toString(), "1 - 3 portions");
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
        assertEquals(new RecipePortion(1, 0, new PortionUnit("portion")).getLowerRange(), 1);
        assertEquals(new RecipePortion(1, 0, new PortionUnit("portion")).getUpperRange(), 0);
    }
}
