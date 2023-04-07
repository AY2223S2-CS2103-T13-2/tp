package seedu.recipe.storage.filemanagers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_PORTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;

import java.io.File;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.storage.JsonSerializableRecipeBookTest;
import seedu.recipe.testutil.TypicalRecipes;

public class ImportManagerTest {
    //As UI cannot be tested, set the stage as null.
    private static final ImportManager MANAGER = new ImportManager(null);

    //The UI methods cannot be tested with logic unit tests:
    //ImportManager::execute
    //ImportManager::selectFile

    @Test
    public void test_importFileNull() {
        assertThrows(NullPointerException.class, () -> MANAGER.importRecipes(null));
    }

    @Test
    public void test_importValidFile() {
        File typicalRecipe = JsonSerializableRecipeBookTest.TYPICAL_RECIPE_FILE.toFile();
        try {
            ObservableList<Recipe> recipeList = MANAGER.importRecipes(typicalRecipe);
            assertTrue(recipeList.size() > 0);

            //Test that all the recipes are imported successfully
            RecipeBook typicalRecipeBook = TypicalRecipes.getTypicalRecipeBook();
            recipeList.forEach(recipe -> {
                assertTrue(typicalRecipeBook.hasRecipe(recipe));
            });

        } catch (IllegalValueException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_importInvalidFile() {
        File invalidRecipeFile = JsonSerializableRecipeBookTest.INVALID_RECIPE_FILE.toFile();
        assertThrows(IllegalValueException.class, () -> MANAGER.importRecipes(invalidRecipeFile));
    }

    @Test
    public void test_toRecipeString() {
        String actual = MANAGER.getCommandText(CACIO_E_PEPE);
        String expected = String.format(
                " %s%s %s%s %s%s%s%s%s",
                PREFIX_NAME.getPrefix(), CACIO_E_PEPE.getName(),
                PREFIX_DURATION.getPrefix(), CACIO_E_PEPE.getDuration(),
                PREFIX_PORTION.getPrefix(), CACIO_E_PEPE.getPortion(),
                CACIO_E_PEPE.getTags().stream()
                    .map(tag -> " " + PREFIX_TAG.getPrefix() + tag.getTagName())
                    .reduce("", (x, y) -> x + y),
                CACIO_E_PEPE.getIngredients().entrySet().stream()
                    .map(entry -> new IngredientBuilder(entry.getKey(), entry.getValue()))
                    .map(builder -> " " + PREFIX_INGREDIENT.getPrefix() + builder.toString())
                    .reduce("", (x, y) -> x + y),
                CACIO_E_PEPE.getSteps().stream()
                    .map(step -> " " + PREFIX_STEP.getPrefix() + step.toString())
                    .reduce("", (x, y) -> x + y)
        );
        assertEquals(expected, actual);
    }
}
