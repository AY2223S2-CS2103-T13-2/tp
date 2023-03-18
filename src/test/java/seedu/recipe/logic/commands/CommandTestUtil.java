package seedu.recipe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_PORTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.recipe.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.recipe.commons.core.index.Index;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.model.Model;
import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.recipe.NameContainsKeywordsPredicate;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.testutil.EditRecipeDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_NAME_CHICKEN = "Grilled Chicken";
    public static final String VALID_NAME_FISH = "Grilled Fish";
    public static final String VALID_PORTION_CHICKEN = "1 - 2 portions";
    public static final String VALID_PORTION_FISH = "2 - 3 portions";
    public static final String VALID_DURATION_CHICKEN = "1 hour";
    public static final String VALID_DURATION_FISH = "15 minutes";
    public static final String VALID_TAG_CHINESE = "Chinese";
    public static final String VALID_TAG_ITALIAN = "Italian";
    public static final String VALID_INGREDIENT_CHICKEN = "1 chicken breast";
    public static final String VALID_INGREDIENT_FISH = "200g fish";
    public static final String VALID_STEP_CHICKEN = "In a bowl, marinate the chicken in oyster sauce";
    public static final String VALID_STEP_FISH = "On a chopping board, remove the skin of the fish";

    public static final String NAME_DESC_CHICKEN = " " + PREFIX_NAME + VALID_NAME_CHICKEN;
    public static final String NAME_DESC_FISH = " " + PREFIX_NAME + VALID_NAME_FISH;
    public static final String PORTION_DESC_CHICKEN = " " + PREFIX_PORTION + VALID_PORTION_CHICKEN;
    public static final String PORTION_DESC_FISH = " " + PREFIX_PORTION + VALID_PORTION_FISH;
    public static final String DURATION_DESC_CHICKEN = " " + PREFIX_DURATION + VALID_DURATION_CHICKEN;
    public static final String DURATION_DESC_FISH = " " + PREFIX_DURATION + VALID_DURATION_FISH;
    public static final String TAG_DESC_CHINESE = " " + PREFIX_TAG + VALID_TAG_CHINESE;
    public static final String TAG_DESC_ITALIAN = " " + PREFIX_TAG + VALID_TAG_ITALIAN;
    public static final String INGREDIENT_DESC_CHICKEN = " " + PREFIX_INGREDIENT + VALID_INGREDIENT_CHICKEN;
    public static final String INGREDIENT_DESC_FISH = " " + PREFIX_INGREDIENT + VALID_INGREDIENT_FISH;
    public static final String STEP_DESC_CHICKEN = " " + PREFIX_STEP + VALID_STEP_CHICKEN;
    public static final String STEP_DESC_FISH = " " + PREFIX_STEP + VALID_STEP_FISH;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Chicken&"; // '&' not allowed in names
    public static final String INVALID_PORTION_DESC = " " + PREFIX_PORTION + "1 person"; // missing upper limit

    public static final String INVALID_DURATION_DESC = " " + PREFIX_DURATION + "10"; // missing units
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "Chinese*"; // '*' not allowed in tags

    // number not allowed for ingredient
    public static final String INVALID_INGREDIENT_DESC = " " + PREFIX_INGREDIENT + "2";
    public static final String INVALID_STEP_DESC = " " + PREFIX_STEP + ""; // empty string not allowed for step

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditRecipeDescriptor DESC_CHICKEN;
    public static final EditCommand.EditRecipeDescriptor DESC_FISH;

    static {
        DESC_CHICKEN = new EditRecipeDescriptorBuilder().withName(VALID_NAME_CHICKEN)
                .withPortion(VALID_PORTION_CHICKEN).withDuration(VALID_DURATION_CHICKEN)
                .withTags(VALID_TAG_CHINESE).withIngredients(VALID_INGREDIENT_CHICKEN)
                .withSteps(VALID_STEP_CHICKEN).build();
        DESC_FISH = new EditRecipeDescriptorBuilder().withName(VALID_NAME_FISH)
                .withPortion(VALID_PORTION_FISH).withDuration(VALID_DURATION_FISH)
                .withTags(VALID_TAG_ITALIAN).withIngredients(VALID_INGREDIENT_FISH)
                .withSteps(VALID_STEP_FISH).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered recipe list and selected recipe in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        RecipeBook expectedRecipeBook = new RecipeBook(actualModel.getRecipeBook());
        List<Recipe> expectedFilteredList = new ArrayList<>(actualModel.getFilteredRecipeList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedRecipeBook, actualModel.getRecipeBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredRecipeList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the recipe at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showRecipeAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredRecipeList().size());

        Recipe recipe = model.getFilteredRecipeList().get(targetIndex.getZeroBased());
        final String[] splitName = recipe.getName().recipeName.split("\\s+");
        model.updateFilteredRecipeList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredRecipeList().size());
    }

}
