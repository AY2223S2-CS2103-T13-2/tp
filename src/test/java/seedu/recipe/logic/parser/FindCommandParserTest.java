package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.commands.FindCommand;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    //    @Test
    //    public void parse_validArgs_returnsFindCommand() {
    //        // no leading and trailing whitespaces
    //        FindCommand expectedFindCommand =
    //            new FindCommand(new PropertyNameContainsKeywordsPredicate<Name>(Arrays.asList("Alice", "Bob"),
    //                FindUtil.getNameFromRecipe,
    //                FindUtil.getNameString));
    //        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);
    //
    //        // multiple whitespaces between keywords
    //        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    //    }

}
