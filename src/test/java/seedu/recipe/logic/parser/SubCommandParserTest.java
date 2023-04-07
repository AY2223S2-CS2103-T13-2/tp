package seedu.recipe.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.parser.exceptions.ParseException;

public class SubCommandParserTest {
    private static final SubCommandParser parser = new SubCommandParser();

    private static final String EMPTY = "";
    private static final String INVALID_INGREDIENT = "@!$Fish";
    private static final String TRAILING_WHITESPACE = "Fish  ";
    private static final String LEADING_WHITESPACE = "   Fish";
    private static final String LEADING_TRAILING_WHITESPACE = "  Fish  ";
    private static final String VALID_INGREDIENT = "Fish";

    @Test
    public void parse_empty_parseExceptionThrown() {
        assertThrows(ParseException.class, () -> parser.parse(EMPTY));
    }

    @Test
    public void parse_invalid_parseExceptionThrown() {
        assertThrows(ParseException.class, () -> parser.parse(INVALID_INGREDIENT));
    }

    @Test
    public void parse_leadingTrailingWhitespace_parseExceptionThrown() {
        //Test that trim() works
        assertDoesNotThrow(() -> parser.parse(TRAILING_WHITESPACE));
        assertDoesNotThrow(() -> parser.parse(LEADING_WHITESPACE));
        assertDoesNotThrow(() -> parser.parse(LEADING_TRAILING_WHITESPACE));
    }

    @Test
    public void parse_valid_success() {
        assertDoesNotThrow(() -> parser.parse(VALID_INGREDIENT));
    }
}
