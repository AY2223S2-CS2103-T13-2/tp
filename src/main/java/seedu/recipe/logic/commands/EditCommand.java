package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_PORTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.recipe.model.Model.PREDICATE_SHOW_ALL_RECIPE;

import java.util.List;

import seedu.recipe.commons.core.Messages;
import seedu.recipe.commons.core.index.Index;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.logic.util.RecipeDescriptor;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.Recipe;

/**
 * Edits the details of an existing recipe in the recipe book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the recipe identified "
            + "by the index number used in the displayed recipe list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DURATION + "DURATION] "
            + "[" + PREFIX_PORTION + "PORTION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "[" + PREFIX_INGREDIENT + "INGREDIENT]...\n"
            + "[" + PREFIX_STEP + "STEP]...\n"

            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Cacio e Pepe Pasta "
            + PREFIX_PORTION + "1 - 2 portions "
            + PREFIX_DURATION + "15 minutes "
            + PREFIX_TAG + "Italian "
            + PREFIX_INGREDIENT + "3 eggs "
            + PREFIX_INGREDIENT + "parmesan cheese "
            + PREFIX_INGREDIENT + "125g spaghetti noodles ";

    public static final String MESSAGE_EDIT_RECIPE_SUCCESS = "Edited Recipe: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the recipe book.";

    private final Index index;
    private final RecipeDescriptor recipeDescriptor;

    /**
     * @param index            of the recipe in the filtered recipe list to edit
     * @param recipeDescriptor details to edit the recipe with
     */
    public EditCommand(Index index, RecipeDescriptor recipeDescriptor) {
        requireNonNull(index);
        requireNonNull(recipeDescriptor);

        this.index = index;
        this.recipeDescriptor = new RecipeDescriptor(recipeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToEdit = lastShownList.get(index.getZeroBased());
        Recipe editedRecipe = recipeDescriptor.toRecipe(recipeToEdit);

        // TODO: ensure that these model methods work properly
        if (!recipeToEdit.isSameRecipe(editedRecipe) && model.hasRecipe(editedRecipe)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        model.setRecipe(recipeToEdit, editedRecipe);
        model.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPE);
        return new CommandResult(String.format(MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
            && recipeDescriptor.equals(e.recipeDescriptor);
    }

    @Override
    public String toString() {
        return String.format("%s|%s", index.getOneBased(), recipeDescriptor.toRecipe());
    }
}
