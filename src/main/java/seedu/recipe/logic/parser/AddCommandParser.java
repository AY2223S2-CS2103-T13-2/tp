package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_PORTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.recipe.logic.commands.AddCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.logic.parser.functional.TryUtil;
import seedu.recipe.logic.util.RecipeDescriptor;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
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

        RecipeDescriptor recipeDescriptor = new RecipeDescriptor();

        // we call Optional<Name>::get here without checking ifPresent
        // this is safe since we already check if a Name argument is present
        //noinspection OptionalGetWithoutIsPresent
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        recipeDescriptor.setName(name);

        // 1. Parse Duration
        argMultimap.getValue(PREFIX_DURATION)
                .flatMap(s -> TryUtil.safeCompute(ParserUtil::parseDuration, s))
                .ifPresent(recipeDescriptor::setDuration);

        // 2. Parse Portion
        argMultimap.getValue(PREFIX_PORTION)
                .flatMap(s -> TryUtil.safeCompute(ParserUtil::parsePortion, s))
                .ifPresent(recipeDescriptor::setPortion);

        // 3. Parse Tags
        Set<Tag> tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        recipeDescriptor.setTags(tags);

        // 4. Parse Ingredients
        List<IngredientBuilder> ingredients = ParserUtil.parseIngredients(argMultimap.getAllValues(PREFIX_INGREDIENT));
        HashMap<Ingredient, IngredientInformation> ingredientTable = new HashMap<>();
        ingredients.forEach(ingredientBuilder -> ingredientTable.putAll(ingredientBuilder.build()));
        recipeDescriptor.setIngredients(ingredientTable);

        // 5. Parse Steps
        List<Step> steps = ParserUtil.parseSteps(argMultimap.getAllValues(PREFIX_STEP));
        recipeDescriptor.setSteps(steps);

        return new AddCommand(recipeDescriptor);
    }

}
