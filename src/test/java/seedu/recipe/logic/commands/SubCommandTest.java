package seedu.recipe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.recipe.commons.core.Messages.MESSAGE_DISPLAY_STORED_SUBS;
import static seedu.recipe.commons.core.Messages.MESSAGE_NO_STORED_SUBS;
import static seedu.recipe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.recipe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.recipe.testutil.TypicalRecipes.getTypicalRecipeBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.recipe.model.Model;
import seedu.recipe.model.ModelManager;
import seedu.recipe.model.UserPrefs;
import seedu.recipe.model.recipe.ingredient.Ingredient;

public class SubCommandTest {
    private static final Ingredient NON_PRELOADED = Ingredient.of("Fish");
    private static final Ingredient PRELOADED = Ingredient.of("Sour Cream");
    private static final List<Ingredient> PRELOADED_SOUR_CREAM_SUBS = List.of(
        Ingredient.of("Greek Yogurt"), Ingredient.of("Cottage Cheese"));

    private final Model model = new ModelManager(getTypicalRecipeBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(model.getRecipeBook(), new UserPrefs());

    @Test
    public void execute_validIngredient_noSubsFound() {
        String expectedMessage = MESSAGE_NO_STORED_SUBS;
        SubCommand command = new SubCommand(NON_PRELOADED);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandFailure(command, model, expectedCommandResult);
    }

    @Test
    public void execute_validIngredient_subsFound() {
        Command subCommand = new SubCommand(PRELOADED);
        CommandResult expected = new CommandResult(
            String.format(MESSAGE_DISPLAY_STORED_SUBS,
                PRELOADED,
                PRELOADED_SOUR_CREAM_SUBS.toString().replace("[", "").replace("]", "")
            )
        );
        assertCommandSuccess(subCommand, model, expected, expectedModel);
    }

    @Test
    public void equals() {
        Command subCommandOne = new SubCommand(PRELOADED);
        Command subCommandOneCopy = new SubCommand(PRELOADED);
        Command subCommandTwo = new SubCommand(NON_PRELOADED);
        //Test different
        assertNotEquals(subCommandTwo, subCommandOne);
        //Test referential equality
        assertEquals(subCommandOne, subCommandOne);
        //Test deep equality
        assertEquals(subCommandOne, subCommandOneCopy);
    }
}
