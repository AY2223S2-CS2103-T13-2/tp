package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.recipe.commons.core.Messages;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.Recipe;

/**
 * Finds and lists all recipes in recipe book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all recipes whose property contain any of "
        + "the specified keywords (case-insensitive) and displays them as a "
        + "list with index numbers.\n"
        + "Findable properties are: name, tag"
        + "If no property is specified, 'find' defaults to finding by recipe name."
        + "Parameters: [PROPERTY] KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + "tag alice bob charlie";

    private final Predicate<Recipe> predicate;

    public FindCommand(Predicate<Recipe> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRecipeList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_RECIPES_LISTED_OVERVIEW, model.getFilteredRecipeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindCommand // instanceof handles nulls
            && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
