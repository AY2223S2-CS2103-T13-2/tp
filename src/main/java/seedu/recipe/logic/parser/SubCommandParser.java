package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.recipe.logic.commands.SubCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.ingredient.Ingredient;

/**
 * Parses input arguments and creates a new SubCommand object
 */
public class SubCommandParser implements Parser<SubCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SubCommand
     * and returns a SubCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public SubCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SubCommand.MESSAGE_USAGE));
        }

        if (!Ingredient.isValidIngredientName(trimmedArgs)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SubCommand.MESSAGE_USAGE));
        }

        Ingredient queryIngredient = Ingredient.of(trimmedArgs);

        return new SubCommand(queryIngredient);
    }
}
