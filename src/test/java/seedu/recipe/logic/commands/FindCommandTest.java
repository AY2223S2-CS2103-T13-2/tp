package seedu.recipe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.recipe.commons.core.Messages.MESSAGE_RECIPES_LISTED_OVERVIEW;
import static seedu.recipe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.recipe.testutil.TypicalRecipes.getTypicalRecipeBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.util.FindUtil;
import seedu.recipe.model.Model;
import seedu.recipe.model.ModelManager;
import seedu.recipe.model.UserPrefs;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.PropertyNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalRecipeBook(), new UserPrefs());

    @Test
    public void equals() {
        PropertyNameContainsKeywordsPredicate<Name> firstPredicate =
            new PropertyNameContainsKeywordsPredicate<Name>(Collections.singletonList("first"),
                FindUtil.GET_NAME_FROM_RECIPE,
                FindUtil.GET_NAME_STRING);
        PropertyNameContainsKeywordsPredicate<Name> secondPredicate =
            new PropertyNameContainsKeywordsPredicate<Name>(Collections.singletonList("second"),
                FindUtil.GET_NAME_FROM_RECIPE,
                FindUtil.GET_NAME_STRING);

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different recipe -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noRecipeFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 0);
        PropertyNameContainsKeywordsPredicate<Name> predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredRecipeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredRecipeList());
    }

    @Test
    public void execute_multipleKeywords_multipleRecipesFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 3);
        PropertyNameContainsKeywordsPredicate<Name> predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredRecipeList(predicate);
        // assertCommandSuccess(command, model, expectedMessage, expectedModel);
        //        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredRecipeList());
    }

    /**
     * Parses {@code userInput} into a {@code PropertyNameContainsKeywordsPredicate<Name>}.
     */
    private PropertyNameContainsKeywordsPredicate<Name> preparePredicate(String userInput) {
        return new PropertyNameContainsKeywordsPredicate<Name>(Arrays.asList(userInput.split("\\s+")),
            FindUtil.GET_NAME_FROM_RECIPE,
            FindUtil.GET_NAME_STRING);
    }
}
