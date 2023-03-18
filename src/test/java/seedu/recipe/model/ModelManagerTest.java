package seedu.recipe.model;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.model.Model.PREDICATE_SHOW_ALL_RECIPE;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;
import static seedu.recipe.testutil.TypicalRecipes.FISH_AND_CHIPS;
import static seedu.recipe.testutil.TypicalRecipes.MASALA_DOSA;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.recipe.commons.core.GuiSettings;
import seedu.recipe.model.recipe.NameContainsKeywordsPredicate;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.exceptions.RecipeNotFoundException;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new RecipeBook(), new RecipeBook(modelManager.getRecipeBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setRecipeBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setRecipeBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setRecipeBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setRecipeBookFilePath(null));
    }

    @Test
    public void setRecipeBookFilePath_validPath_setsRecipeBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setRecipeBookFilePath(path);
        assertEquals(path, modelManager.getRecipeBookFilePath());
    }

    @Test
    public void hasRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasRecipe(null));
    }


    @Test
    public void getFilteredRecipeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredRecipeList().remove(0));
    }

    @Test
    public void equals() {
        RecipeBook recipeBook = new RecipeBook();
        recipeBook.setRecipes(List.of(CACIO_E_PEPE, MASALA_DOSA));
        RecipeBook differentRecipeBook = new RecipeBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(recipeBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(recipeBook, userPrefs);
        assertEquals(modelManager, modelManagerCopy);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotEquals(null, modelManager);

        // different types -> returns false
        assertNotEquals(5, modelManager);

        // different recipeBook -> returns false
        assertNotEquals(modelManager, new ModelManager(differentRecipeBook, userPrefs));

        // different filteredList -> returns false
        String[] keywords = CACIO_E_PEPE.getName().recipeName.split("\\s+");
        modelManager.updateFilteredRecipeList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertNotEquals(modelManager, new ModelManager(recipeBook, userPrefs));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPE);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setRecipeBookFilePath(Paths.get("differentFilePath"));
        assertNotEquals(modelManager, new ModelManager(recipeBook, differentUserPrefs));
    }

    @Test
    public void addRecipe() {
        //Test Redirection
        assertThrows(NullPointerException.class, () -> modelManager.addRecipe(null));

        ObservableList<Recipe> empty = modelManager.getFilteredRecipeList();
        assertEquals(0, empty.size());

        modelManager.addRecipe(CACIO_E_PEPE);
        ObservableList<Recipe> o = modelManager.getFilteredRecipeList();
        assertTrue(o.get(0).isSameRecipe(CACIO_E_PEPE));
    }

    @Test
    public void hasRecipe() {
        assertFalse(modelManager.hasRecipe(CACIO_E_PEPE));

        modelManager.addRecipe(CACIO_E_PEPE);
        assertTrue(modelManager.hasRecipe(CACIO_E_PEPE));
    }

    @Test
    public void deleteRecipe() {
        assertThrows(RecipeNotFoundException.class, () -> modelManager.deleteRecipe(CACIO_E_PEPE));

        modelManager.addRecipe(CACIO_E_PEPE);
        assertDoesNotThrow(() -> modelManager.deleteRecipe(CACIO_E_PEPE));
    }

    @Test
    public void setRecipe() {
        assertFalse(modelManager.hasRecipe(CACIO_E_PEPE));
        assertThrows(RecipeNotFoundException.class, () -> modelManager.setRecipe(CACIO_E_PEPE, CACIO_E_PEPE));

        modelManager.addRecipe(CACIO_E_PEPE);
        assertDoesNotThrow(() -> modelManager.setRecipe(CACIO_E_PEPE, MASALA_DOSA));
    }

    @Test
    public void setRecipeBook() {
        RecipeBook newBook = new RecipeBook();
        newBook.addRecipe(FISH_AND_CHIPS);

        modelManager.addRecipe(MASALA_DOSA);
        assertTrue(modelManager.hasRecipe(MASALA_DOSA));

        modelManager.setRecipeBook(newBook);
        assertFalse(modelManager.hasRecipe(MASALA_DOSA));
        assertTrue(modelManager.hasRecipe(FISH_AND_CHIPS));
    }
}
