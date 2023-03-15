package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_PORTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.recipe.model.Model.PREDICATE_SHOW_ALL_RECIPE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.recipe.commons.core.Messages;
import seedu.recipe.commons.core.index.Index;
import seedu.recipe.commons.util.CollectionUtil;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.Address;
import seedu.recipe.model.recipe.Email;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.RecipeDuration;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.tag.Tag;

/**
 * Edits the details of an existing recipe in the address book.
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
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the address book.";

    private final Index index;
    private final EditRecipeDescriptor editRecipeDescriptor;

    /**
     * @param index                of the recipe in the filtered recipe list to edit
     * @param editRecipeDescriptor details to edit the recipe with
     */
    public EditCommand(Index index, EditRecipeDescriptor editRecipeDescriptor) {
        requireNonNull(index);
        requireNonNull(editRecipeDescriptor);

        this.index = index;
        this.editRecipeDescriptor = new EditRecipeDescriptor(editRecipeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToEdit = lastShownList.get(index.getZeroBased());
        Recipe editedRecipe = createEditedRecipe(recipeToEdit, editRecipeDescriptor);

        // TODO: ensure that these model methods work properly
        if (!recipeToEdit.isSameRecipe(editedRecipe) && model.hasRecipe(editedRecipe)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        model.setRecipe(recipeToEdit, editedRecipe);
        model.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPE);
        return new CommandResult(String.format(MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe));
    }

    /**
     * Creates and returns a {@code Recipe} with the details of {@code recipeToEdit}
     * edited with {@code editRecipeDescriptor}.
     */
    private static Recipe createEditedRecipe(Recipe recipeToEdit, EditRecipeDescriptor editRecipeDescriptor) {
        assert recipeToEdit != null;

        Name updatedName = editRecipeDescriptor.getName().orElse(recipeToEdit.getName());
        Recipe newRecipe = new Recipe(updatedName);

        RecipeDuration updatedDuration = editRecipeDescriptor.getDuration().orElse(recipeToEdit.getDurationNullable());
        newRecipe.setDuration(updatedDuration);

        RecipePortion updatedPortion = editRecipeDescriptor.getPortion().orElse(recipeToEdit.getPortionNullable());
        newRecipe.setPortion(updatedPortion);

        Tag[] updatedTags = editRecipeDescriptor.getTags().orElse(recipeToEdit.getTags()).toArray(Tag[]::new);
        newRecipe.setTags(updatedTags);

        Ingredient[] updatedIngredients = editRecipeDescriptor.getIngredients().orElse(recipeToEdit.getIngredients()).toArray(Ingredient[]::new);
        newRecipe.setIngredients(updatedIngredients);

        Step[] updatedSteps = editRecipeDescriptor.getSteps().orElse(recipeToEdit.getSteps()).toArray(Step[]::new);
        newRecipe.setSteps(updatedSteps);

        return newRecipe;
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
                && editRecipeDescriptor.equals(e.editRecipeDescriptor);
    }

    /**
     * Stores the details to edit the recipe with. Each non-empty field value will replace the
     * corresponding field value of the recipe.
     */
    public static class EditRecipeDescriptor {
        private Name name;
        private RecipeDuration duration;
        private RecipePortion portion;
        private Set<Tag> tags;
        private List<Ingredient> ingredients;
        private List<Step> steps;

        public EditRecipeDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditRecipeDescriptor(EditRecipeDescriptor toCopy) {
            setName(toCopy.name);
            setDuration(toCopy.duration);
            setPortion(toCopy.portion);
            setTags(toCopy.tags);
            setIngredients(toCopy.ingredients);
            setSteps(toCopy.steps);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, duration, portion, tags, ingredients, steps);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setDuration(RecipeDuration duration) {
            this.duration = duration;
        }

        public Optional<RecipeDuration> getDuration() {
            return Optional.ofNullable(duration);
        }

        public void setPortion(RecipePortion portion) {
            this.portion = portion;
        }

        public Optional<RecipePortion> getPortion() {
            return Optional.ofNullable(portion);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setIngredients(List<Ingredient> ingredients) {
            this.ingredients = (ingredients != null) ? new ArrayList<>(ingredients) : null;
        }

        public Optional<List<Ingredient>> getIngredients() {
            return (ingredients != null) ? Optional.of(Collections.unmodifiableList(ingredients)) : Optional.empty();
        }

        public void setSteps(List<Step> steps) {
            this.steps = (steps != null) ? new ArrayList<>(steps) : null;
        }

        public Optional<List<Step>> getSteps() {
            return (steps != null) ? Optional.of(Collections.unmodifiableList(steps)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditRecipeDescriptor)) {
                return false;
            }

            // state check
            EditRecipeDescriptor e = (EditRecipeDescriptor) other;

            return getName().equals(e.getName())
                    && getDuration().equals(e.getDuration())
                    && getPortion().equals(e.getPortion())
                    && getTags().equals(e.getTags())
                    && getIngredients().equals(e.getIngredients())
                    && getSteps().equals(e.getSteps());
        }
    }
}
