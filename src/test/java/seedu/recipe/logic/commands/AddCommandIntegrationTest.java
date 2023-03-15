package seedu.recipe.logic.commands;

import static seedu.recipe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.recipe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.recipe.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.recipe.model.Model;
import seedu.recipe.model.ModelManager;
import seedu.recipe.model.UserPrefs;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Recipe validRecipe = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getRecipeBook(), new UserPrefs());
        expectedModel.addRecipe(validRecipe);

        assertCommandSuccess(new AddCommand(validRecipe), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validRecipe), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Recipe recipeInList = model.getRecipeBook().getRecipeList().get(0);
        assertCommandFailure(new AddCommand(recipeInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
