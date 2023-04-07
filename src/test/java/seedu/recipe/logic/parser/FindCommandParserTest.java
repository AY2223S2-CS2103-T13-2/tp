package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_EMPTY_KEYWORDS_FIND;
import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.commands.FindCommand;
import seedu.recipe.logic.util.FindUtil;
import seedu.recipe.model.recipe.PropertyCollectionContainsKeywordsPredicate;
import seedu.recipe.model.recipe.PropertyNameContainsKeywordsPredicate;

public class FindCommandParserTest {
    private static final String WHITESPACE = "     ";
    private static final String TRAILING_WHITESPACE_NAME = "name     ";
    private static final String TRAILING_WHITESPACE_TAG = "tag     ";
    private static final String TRAILING_WHITESPACE_INGREDIENT = "ingredient     ";
    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_noPropertyEmptyArg_throwsParseException() {
        assertParseFailure(parser, WHITESPACE,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_namePropertyEmptyArg_throwsParseException() {
        assertParseFailure(parser, TRAILING_WHITESPACE_NAME, MESSAGE_EMPTY_KEYWORDS_FIND);
    }

    @Test
    public void parse_tagsPropertyEmptyArg_throwsParseException() {
        assertParseFailure(parser, TRAILING_WHITESPACE_TAG, MESSAGE_EMPTY_KEYWORDS_FIND);
    }

    @Test
    public void parse_ingredientsPropertyEmptyArg_throwsParseException() {
        assertParseFailure(parser, TRAILING_WHITESPACE_INGREDIENT, MESSAGE_EMPTY_KEYWORDS_FIND);
    }

    @Test
    public void parse_noPropertyValidArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(
            new PropertyNameContainsKeywordsPredicate<>(Arrays.asList("Indian", "Mexican"),
            FindUtil.GET_NAME_FROM_RECIPE,
            FindUtil.GET_NAME_STRING)
        );
        assertParseSuccess(parser, "Indian Mexican", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Indian \n \t Mexican  \t", expectedFindCommand);
    }

    @Test
    public void parse_namePropertyValidArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(
            new PropertyNameContainsKeywordsPredicate<>(Arrays.asList("Italian", "Pasta"),
            FindUtil.GET_NAME_FROM_RECIPE,
            FindUtil.GET_NAME_STRING)
        );
        assertParseSuccess(parser, "name Italian Pasta", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "name \n Italian \n \t Pasta  \t", expectedFindCommand);
    }

    @Test
    public void parse_tagsPropertyValidArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(
            new PropertyCollectionContainsKeywordsPredicate<>(Arrays.asList("Indian", "Italian"),
            FindUtil.GET_TAGS_FROM_RECIPE,
            FindUtil.GET_TAG_STRING)
        );
        assertParseSuccess(parser, "tag Indian Italian", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "tag \n Indian \n \t Italian  \t", expectedFindCommand);
    }

    @Test
    public void parse_ingredientsPropertyValidArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(
            new PropertyCollectionContainsKeywordsPredicate<>(Arrays.asList("pecorino", "pepper"),
            FindUtil.GET_INGREDIENTS_FROM_RECIPE,
            FindUtil.GET_INGREDIENT_STRING)
        );
        assertParseSuccess(parser, "ingredient pecorino pepper", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "ingredient \n pecorino \n \t pepper  \t", expectedFindCommand);
    }
}
