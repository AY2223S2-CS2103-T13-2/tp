package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_PORTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.recipe.logic.commands.AddCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.logic.parser.functional.TryUtil;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.RecipeDuration;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PORTION, PREFIX_DURATION,
                        PREFIX_TAG, PREFIX_INGREDIENT, PREFIX_STEP);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Recipe recipe = new Recipe(name);

        // 1. Parse Duration
        argMultimap.getValue(PREFIX_DURATION).ifPresent(durationString -> {
            Optional<RecipeDuration> recipeDuration =
                TryUtil.safeCompute(ParserUtil::parseDuration, durationString);
            recipeDuration.ifPresent(recipe::setDuration);
        });

        // 2. Parse Portion
        argMultimap.getValue(PREFIX_PORTION).ifPresent(portionString -> {
            Optional<RecipePortion> recipeDuration =
                    TryUtil.safeCompute(ParserUtil::parsePortion, portionString);
            recipeDuration.ifPresent(recipe::setPortion);
        });

        // 3. Parse Tags
        Tag[] tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG))
                .toArray(Tag[]::new);
        recipe.setTags(tags);

        // 4. Parse Ingredients
        Ingredient[] ingredients = ParserUtil.parseIngredients(argMultimap.getAllValues(PREFIX_INGREDIENT))
                .toArray(Ingredient[]::new);
        recipe.setIngredients(ingredients);

        // 5. Parse Steps
        Step[] steps = ParserUtil.parseSteps(argMultimap.getAllValues(PREFIX_STEP))
                .toArray(Step[]::new);
        recipe.setSteps(steps);

        return new AddCommand(recipe);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
