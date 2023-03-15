package seedu.recipe.logic.parser;

import seedu.recipe.commons.core.index.Index;
import seedu.recipe.logic.commands.EditCommand;
import seedu.recipe.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.tag.Tag;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_PORTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DURATION, PREFIX_PORTION, PREFIX_TAG, PREFIX_INGREDIENT, PREFIX_STEP);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditRecipeDescriptor editRecipeDescriptor = new EditRecipeDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editRecipeDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            editRecipeDescriptor.setDuration(ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get()));
        }
        if (argMultimap.getValue(PREFIX_PORTION).isPresent()) {
            editRecipeDescriptor.setPortion(ParserUtil.parsePortion(argMultimap.getValue(PREFIX_PORTION).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editRecipeDescriptor::setTags);
        parseIngredientsForEdit(argMultimap.getAllValues(PREFIX_INGREDIENT)).ifPresent(editRecipeDescriptor::setIngredients);
        parseStepsForEdit(argMultimap.getAllValues(PREFIX_STEP)).ifPresent(editRecipeDescriptor::setSteps);

        if (!editRecipeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editRecipeDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    private Optional<List<Ingredient>> parseIngredientsForEdit(Collection<String> ingredients) throws ParseException {
        assert ingredients != null;

        if (ingredients.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> ingredientList = ingredients.size() == 1 && ingredients.contains("") ? Collections.emptyList() : ingredients;
        return Optional.of(ParserUtil.parseIngredients(ingredientList));
    }

    private Optional<List<Step>> parseStepsForEdit(Collection<String> steps) throws ParseException {
        assert steps != null;

        if (steps.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> stepsList = steps.size() == 1 && steps.contains("") ? Collections.emptyList() : steps;
        return Optional.of(ParserUtil.parseSteps(stepsList));
    }
}
