package seedu.recipe.logic.commands;

import static seedu.recipe.logic.commands.CommandTestUtil.DESC_CHICKEN;
import static seedu.recipe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.recipe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.recipe.testutil.TypicalRecipes.getTypicalRecipeBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.recipe.model.Model;
import seedu.recipe.model.ModelManager;
import seedu.recipe.model.UserPrefs;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.testutil.EditRecipeDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
//@@author alson001
public class AddCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());
    }

    @Test
    public void execute_newRecipe_success() {
        Recipe validRecipe = DESC_CHICKEN.toRecipe();
        Model expectedModel = new ModelManager(model.getRecipeBook(), new UserPrefs());
        expectedModel.addRecipe(validRecipe);
        assertCommandSuccess(new AddCommand(DESC_CHICKEN), model,
            String.format(AddCommand.MESSAGE_SUCCESS, validRecipe.getName()), expectedModel);
    }

    @Test
    public void execute_duplicateRecipe_throwsCommandException() {
        Recipe recipeInList = model.getRecipeBook().getRecipeList().get(0);
        EditRecipeDescriptorBuilder editRecipeDescriptorBuilder = new EditRecipeDescriptorBuilder(recipeInList);
        assertCommandFailure(new AddCommand(editRecipeDescriptorBuilder.build()),
            model, AddCommand.MESSAGE_DUPLICATE_RECIPE);
    }

}
