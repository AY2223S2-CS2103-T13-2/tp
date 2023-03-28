package seedu.recipe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.recipe.commons.core.Messages.MESSAGE_RECIPES_LISTED_OVERVIEW;
import static seedu.recipe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.recipe.testutil.TypicalRecipes.BLUEBERRY_PANCAKES;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;
import static seedu.recipe.testutil.TypicalRecipes.FISH_AND_CHIPS;
import static seedu.recipe.testutil.TypicalRecipes.getTypicalRecipeBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.util.FindUtil;
import seedu.recipe.model.Model;
import seedu.recipe.model.ModelManager;
import seedu.recipe.model.UserPrefs;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.PropertyCollectionContainsKeywordsPredicate;
import seedu.recipe.model.recipe.PropertyNameContainsKeywordsPredicate;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.tag.Tag;

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
        PropertyNameContainsKeywordsPredicate<Name> thirdPredicate =
            new PropertyNameContainsKeywordsPredicate<Name>(Collections.singletonList("second"),
                FindUtil.GET_NAME_FROM_RECIPE,
                FindUtil.GET_NAME_STRING);

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);
        FindCommand findThirdCommand = new FindCommand(thirdPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different but equal values -> returns true
        assertEquals(findSecondCommand, findThirdCommand);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different predicate -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_namePropertyZeroKeywords_noRecipeFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 0);
        PropertyNameContainsKeywordsPredicate<Name> predicate = prepareNamePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredRecipeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredRecipeList());
    }

    @Test
    public void execute_namePropertyMultipleKeywords_multipleRecipesFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 2);
        PropertyNameContainsKeywordsPredicate<Name> predicate = prepareNamePredicate("American Fish");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredRecipeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BLUEBERRY_PANCAKES, FISH_AND_CHIPS), model.getFilteredRecipeList());
    }

    @Test
    public void execute_tagPropertyMultipleKeywords_multipleRecipesFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 2);
        PropertyCollectionContainsKeywordsPredicate<Tag> predicate = prepareTagPredicate("American Italian");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredRecipeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BLUEBERRY_PANCAKES, CACIO_E_PEPE), model.getFilteredRecipeList());
    }

    @Test
    public void execute_ingredientPropertyMultipleKeywords_multipleRecipesFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 3);
        PropertyCollectionContainsKeywordsPredicate<Ingredient> predicate =
            prepareIngredientPredicate("Egg Pepper");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredRecipeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BLUEBERRY_PANCAKES, CACIO_E_PEPE, FISH_AND_CHIPS), model.getFilteredRecipeList());
    }

    /**
     * Parses {@code userInput} into a {@code PropertyNameContainsKeywordsPredicate<Name>}.
     */
    private PropertyNameContainsKeywordsPredicate<Name> prepareNamePredicate(String userInput) {
        return new PropertyNameContainsKeywordsPredicate<Name>(Arrays.asList(userInput.split("\\s+")),
            FindUtil.GET_NAME_FROM_RECIPE,
            FindUtil.GET_NAME_STRING);
    }

    /**
     * Parses {@code userInput} into a {@code PropertyCollectionContainsKeywordsPredicate<Tag>}.
     */
    private PropertyCollectionContainsKeywordsPredicate<Tag> prepareTagPredicate(String userInput) {
        return new PropertyCollectionContainsKeywordsPredicate<Tag>(Arrays.asList(userInput.split("\\s+")),
            FindUtil.GET_TAGS_FROM_RECIPE,
            FindUtil.GET_TAG_STRING);
    }

    /**
     * Parses {@code userInput} into a {@code PropertyCollectionContainsKeywordsPredicate<Ingredient>}.
     */
    private PropertyCollectionContainsKeywordsPredicate<Ingredient> prepareIngredientPredicate(String userInput) {
        return new PropertyCollectionContainsKeywordsPredicate<Ingredient>(Arrays.asList(userInput.split("\\s+")),
            FindUtil.GET_INGREDIENTS_FROM_RECIPE,
            FindUtil.GET_INGREDIENT_STRING);
    }
}
