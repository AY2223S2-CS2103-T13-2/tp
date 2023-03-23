package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.recipe.logic.commands.FindCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.PropertyContainsKeywordsPredicate;
import seedu.recipe.model.recipe.Recipe;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        PropertyContainsKeywordsPredicate<?> predicate;

        switch (nameKeywords[0].toLowerCase()) {
        case "name":
            nameKeywords = Arrays.copyOfRange(nameKeywords, 1, nameKeywords.length);
            // fallthrough
        default: // if no property is specified, assume we are finding by Name
            predicate = new PropertyContainsKeywordsPredicate<Name>(
                Arrays.asList(nameKeywords), Recipe::getName, (name) -> name.recipeName);
        }

        return new FindCommand(predicate);
    }

}
