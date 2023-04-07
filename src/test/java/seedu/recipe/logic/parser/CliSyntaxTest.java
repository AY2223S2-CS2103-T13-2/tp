package seedu.recipe.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Serves as a validation point for the Prefixes this application accepts, in the context of the RecipeBook.
 * Should a developer wish to modify those Prefixes, these test cases should also be fixed as well.
 */
public class CliSyntaxTest {
    private static final String TRAILING_TOKEN = "/";
    private static final String PREFIX_NAME = "n" + TRAILING_TOKEN;
    private static final String PREFIX_DURATION = "d" + TRAILING_TOKEN;
    private static final String PREFIX_PORTION = "p" + TRAILING_TOKEN;
    private static final String PREFIX_TAG = "t" + TRAILING_TOKEN;
    private static final String PREFIX_INGREDIENT = "i" + TRAILING_TOKEN;
    private static final String PREFIX_STEP = "s" + TRAILING_TOKEN;

    @Test
    public void namePrefix() {
        assertEquals(CliSyntax.PREFIX_NAME.getPrefix(), PREFIX_NAME);
    }

    @Test
    public void durationPrefix() {
        assertEquals(CliSyntax.PREFIX_DURATION.getPrefix(), PREFIX_DURATION);
    }

    @Test
    public void portionPrefix() {
        assertEquals(CliSyntax.PREFIX_PORTION.getPrefix(), PREFIX_PORTION);
    }

    @Test
    public void tagPrefix() {
        assertEquals(CliSyntax.PREFIX_TAG.getPrefix(), PREFIX_TAG);
    }

    @Test
    public void ingredientPrefix() {
        assertEquals(CliSyntax.PREFIX_INGREDIENT.getPrefix(), PREFIX_INGREDIENT);
    }

    @Test
    public void stepPrefix() {
        assertEquals(CliSyntax.PREFIX_STEP.getPrefix(), PREFIX_STEP);
    }
}
