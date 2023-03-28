package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_EMPTY_KEYWORDS_FIND;
import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.recipe.logic.commands.FindCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.logic.util.FindUtil;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.PropertyCollectionContainsKeywordsPredicate;
import seedu.recipe.model.recipe.PropertyNameContainsKeywordsPredicate;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.tag.Tag;

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

        String[] allKeywords = trimmedArgs.split("\\s+");
        String command = allKeywords[0];
        String[] findKeywords = Arrays.copyOfRange(allKeywords, 1, allKeywords.length);
        Predicate<Recipe> predicate;

        switch (command.toLowerCase()) {
        case "ingredient":
            validateFindKeywords(findKeywords);
            predicate = new PropertyCollectionContainsKeywordsPredicate<Ingredient>(findKeywords,
                FindUtil.GET_INGREDIENTS_FROM_RECIPE, FindUtil.GET_INGREDIENT_STRING);
            break;
        case "tag":
            validateFindKeywords(findKeywords);
            predicate = new PropertyCollectionContainsKeywordsPredicate<Tag>(findKeywords,
                FindUtil.GET_TAGS_FROM_RECIPE, FindUtil.GET_TAG_STRING);
            break;
        case "name":
            validateFindKeywords(findKeywords);
            predicate = new PropertyNameContainsKeywordsPredicate<Name>(findKeywords, FindUtil.GET_NAME_FROM_RECIPE,
                FindUtil.GET_NAME_STRING);
            break;
        default: // if no property is specified, assume we are finding by Name
            validateFindKeywords(allKeywords);
            predicate = new PropertyNameContainsKeywordsPredicate<Name>(allKeywords, FindUtil.GET_NAME_FROM_RECIPE,
                FindUtil.GET_NAME_STRING);
        }

        return new FindCommand(predicate);
    }

    /**
     * Validates whether a list of keywords is a valid input to the find command.
     *
     * @param findKeywords A list of keywords.
     * @throws ParseException Thrown if the keyword list is empty.
     */
    public void validateFindKeywords(String[] findKeywords) throws ParseException {
        if (findKeywords.length == 0) {
            throw new ParseException(MESSAGE_EMPTY_KEYWORDS_FIND);
        }
    }

}
