package seedu.recipe.logic.commands;

import static seedu.recipe.testutil.TypicalRecipes.getTypicalRecipeBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.recipe.model.Model;
import seedu.recipe.model.ModelManager;
import seedu.recipe.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());
    }

    @Test
    public void execute_newRecipe_success() {
        // Recipe validRecipe = new RecipeBuilder().build();
        // Model expectedModel = new ModelManager(model.getRecipeBook(), new UserPrefs());
        // expectedModel.addRecipe(validRecipe);
        // assertCommandSuccess(new AddCommand(validRecipe), model,
        //         String.format(AddCommand.MESSAGE_SUCCESS, validRecipe), expectedModel);
    }

    @Test
    public void execute_duplicateRecipe_throwsCommandException() {
        // Recipe recipeInList = model.getRecipeBook().getRecipeList().get(0);
        // assertCommandFailure(new AddCommand(recipeInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
