package seedu.recipe.model.recipe.ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.parser.Prefix;

public class IngredientParserTest {

    @Test
    public void test_prefixPosition_toString() {
        IngredientParser ip = new IngredientParser();
        IngredientParser.PrefixPosition prefixPosition = new IngredientParser.PrefixPosition(new Prefix("/n"), 2);
        String expectedString = "<2>[/n]";
        assertEquals(expectedString, prefixPosition.toString());
    }
}