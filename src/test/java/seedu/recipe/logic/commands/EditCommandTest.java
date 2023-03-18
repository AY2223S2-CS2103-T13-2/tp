package seedu.recipe.logic.commands;

import static seedu.recipe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.recipe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.recipe.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static seedu.recipe.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.recipe.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.recipe.testutil.TypicalRecipes.getTypicalRecipeBook;

import org.junit.jupiter.api.Test;

import seedu.recipe.model.Model;
import seedu.recipe.model.ModelManager;
import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.UserPrefs;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.testutil.EditRecipeDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        // Recipe editedRecipe = new RecipeBuilder().build();
        // EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder(editedRecipe).build();
        // EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
        // String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);
        // Model expectedModel = new ModelManager(new RecipeBook(model.getRecipeBook()), new UserPrefs());
        // expectedModel.setRecipe(model.getFilteredRecipeList().get(0), editedRecipe);
        // assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        // Index indexLastRecipe = Index.fromOneBased(model.getFilteredRecipeList().size());
        // Recipe lastRecipe = model.getFilteredRecipeList().get(indexLastRecipe.getZeroBased());
        // RecipeBuilder recipeInList = new RecipeBuilder(lastRecipe);
        // Recipe editedRecipe = recipeInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
        //         .withTags(VALID_TAG_HUSBAND).build();
        // EditCommand.EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withName(VALID_NAME_BOB)
        //         .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        // EditCommand editCommand = new EditCommand(indexLastRecipe, descriptor);
        // String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);
        // Model expectedModel = new ModelManager(new RecipeBook(model.getRecipeBook()), new UserPrefs());
        // expectedModel.setRecipe(lastRecipe, editedRecipe);

        // assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditCommand.EditRecipeDescriptor());
        Recipe editedRecipe = model.getFilteredRecipeList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new RecipeBook(model.getRecipeBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        // showRecipeAtIndex(model, INDEX_FIRST_PERSON);
        // Recipe recipeInFilteredList = model.getFilteredRecipeList().get(INDEX_FIRST_PERSON.getZeroBased());
        // Recipe editedRecipe = new RecipeBuilder(recipeInFilteredList).withName(VALID_NAME_BOB).build();
        // EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
        //         new EditRecipeDescriptorBuilder().withName(VALID_NAME_BOB).build());
        // String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);
        // Model expectedModel = new ModelManager(new RecipeBook(model.getRecipeBook()), new UserPrefs());
        // expectedModel.setRecipe(model.getFilteredRecipeList().get(0), editedRecipe);
        // assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateRecipeUnfilteredList_failure() {
        Recipe firstRecipe = model.getFilteredRecipeList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditCommand.EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder(firstRecipe).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_RECIPE);
    }

    @Test
    public void execute_duplicateRecipeFilteredList_failure() {
        showRecipeAtIndex(model, INDEX_FIRST_PERSON);

        // edit recipe in filtered list into a duplicate in address book
        Recipe recipeInList = model.getRecipeBook().getRecipeList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditRecipeDescriptorBuilder(recipeInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_RECIPE);
    }

    @Test
    public void execute_invalidRecipeIndexUnfilteredList_failure() {
        // Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        // EditCommand.EditRecipeDescriptor descriptor =
        //         new EditRecipeDescriptorBuilder().withName(VALID_NAME_BOB).build();
        // EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);
        // assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidRecipeIndexFilteredList_failure() {
        // showRecipeAtIndex(model, INDEX_FIRST_PERSON);
        // Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // // ensures that outOfBoundIndex is still in bounds of address book list
        // assertTrue(outOfBoundIndex.getZeroBased() < model.getRecipeBook().getRecipeList().size());
        // EditCommand editCommand = new EditCommand(outOfBoundIndex,
        //         new EditRecipeDescriptorBuilder().withName(VALID_NAME_BOB).build());
        // assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        // final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);
        // // same values -> returns true
        // EditCommand.EditRecipeDescriptor copyDescriptor = new EditCommand.EditRecipeDescriptor(DESC_AMY);
        // EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        // assertTrue(standardCommand.equals(commandWithSameValues));
        // // same object -> returns true
        // assertTrue(standardCommand.equals(standardCommand));
        // // null -> returns false
        // assertFalse(standardCommand.equals(null));
        // // different types -> returns false
        // assertFalse(standardCommand.equals(new ClearCommand()));
        // // different index -> returns false
        // assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));
        // // different descriptor -> returns false
        // assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

}
