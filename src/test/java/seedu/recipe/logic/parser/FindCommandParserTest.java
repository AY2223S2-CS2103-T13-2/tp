package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_EMPTY_KEYWORDS_FIND;
import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.commands.FindCommand;
import seedu.recipe.logic.util.FindUtil;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.PropertyCollectionContainsKeywordsPredicate;
import seedu.recipe.model.recipe.PropertyNameContainsKeywordsPredicate;
import seedu.recipe.model.tag.Tag;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_noPropertyEmptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_namePropertyEmptyArg_throwsParseException() {
        assertParseFailure(parser, "name     ", MESSAGE_EMPTY_KEYWORDS_FIND);
    }

    @Test
    public void parse_tagsPropertyEmptyArg_throwsParseException() {
        assertParseFailure(parser, "tag     ", MESSAGE_EMPTY_KEYWORDS_FIND);
    }

    @Test
    public void parse_noPropertyValidArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
            new FindCommand(new PropertyNameContainsKeywordsPredicate<Name>(Arrays.asList("Alice", "Bob"),
                FindUtil.GET_NAME_FROM_RECIPE,
                FindUtil.GET_NAME_STRING));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_namePropertyValidArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
            new FindCommand(new PropertyNameContainsKeywordsPredicate<Name>(Arrays.asList("Alice", "Bob"),
                FindUtil.GET_NAME_FROM_RECIPE,
                FindUtil.GET_NAME_STRING));
        assertParseSuccess(parser, "name Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "name \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_tagsPropertyValidArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
            new FindCommand(new PropertyCollectionContainsKeywordsPredicate<Tag>(Arrays.asList("Indian", "Italian"),
                FindUtil.GET_TAGS_FROM_RECIPE,
                FindUtil.GET_TAG_STRING));
        assertParseSuccess(parser, "tag Indian Italian", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "tag \n Indian \n \t Italian  \t", expectedFindCommand);
    }
}
