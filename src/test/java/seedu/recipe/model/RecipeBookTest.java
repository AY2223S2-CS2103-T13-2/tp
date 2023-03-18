package seedu.recipe.model;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_TAGS;
import static seedu.recipe.testutil.TypicalRecipes.MASALA_DOSA;
import static seedu.recipe.testutil.TypicalRecipes.getTypicalRecipeBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.exceptions.DuplicateRecipeException;
import seedu.recipe.model.recipe.exceptions.RecipeNotFoundException;
import seedu.recipe.model.tag.Tag;
import seedu.recipe.testutil.RecipeBuilder;

public class RecipeBookTest {

    private final RecipeBook recipeBook = new RecipeBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), recipeBook.getRecipeList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyRecipeBook_replacesData() {
        RecipeBook newData = getTypicalRecipeBook();
        recipeBook.resetData(newData);
        assertEquals(newData, recipeBook);
    }

    @Test
    public void resetData_withDuplicateRecipes_throwsDuplicateRecipeException() {
        // Two recipes with the same name fields
        Recipe editedDosa = new RecipeBuilder(MASALA_DOSA).build();
        editedDosa.setTags(CACIO_TAGS.toArray(Tag[]::new));
        List<Recipe> newRecipes = Arrays.asList(MASALA_DOSA, editedDosa);
        RecipeBookStub newData = new RecipeBookStub(newRecipes);

        assertThrows(DuplicateRecipeException.class, () -> recipeBook.resetData(newData));
    }

    @Test
    public void hasRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeBook.hasRecipe(null));
    }

    @Test
    public void hasRecipe_recipeNotInRecipeBook_returnsFalse() {
        assertFalse(recipeBook.hasRecipe(MASALA_DOSA));
    }

    @Test
    public void hasRecipe_recipeInRecipeBook_returnsTrue() {
        recipeBook.addRecipe(MASALA_DOSA);
        assertTrue(recipeBook.hasRecipe(MASALA_DOSA));
    }

    @Test
    public void hasRecipe_recipeWithSameIdentityFieldsInRecipeBook_returnsTrue() {
        recipeBook.addRecipe(MASALA_DOSA);
        Recipe editedDosa = new RecipeBuilder(MASALA_DOSA).build();
        editedDosa.setTags(CACIO_TAGS.toArray(Tag[]::new));
        assertTrue(recipeBook.hasRecipe(editedDosa));
    }

    @Test
    public void getRecipeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> recipeBook.getRecipeList().remove(0));
    }

    @Test
    public void setRecipe() {
        //Null Assertion
        assertThrows(NullPointerException.class, () -> recipeBook.setRecipe(CACIO_E_PEPE, null));

        //Set functionality
        recipeBook.addRecipe(CACIO_E_PEPE);
        recipeBook.setRecipe(CACIO_E_PEPE, MASALA_DOSA);
        RecipeBook newBook = new RecipeBook();
        newBook.addRecipe(MASALA_DOSA);
        assertEquals(recipeBook, newBook);

        //Test direction
        // Recipe newRecipe = new RecipeBuilder(MASALA_DOSA).build();

        assertThrows(RecipeNotFoundException.class, () -> recipeBook.setRecipe(CACIO_E_PEPE, MASALA_DOSA));
        // assertThrows(DuplicateRecipeException.class, () -> recipeBook.setRecipe(MASALA_DOSA, newRecipe));
    }

    @Test
    public void remove() {
        assertThrows(RecipeNotFoundException.class, () -> recipeBook.removeRecipe(CACIO_E_PEPE));
        recipeBook.addRecipe(CACIO_E_PEPE);
        assertDoesNotThrow(() -> recipeBook.removeRecipe(CACIO_E_PEPE));
    }

    @Test
    public void overloadedConstructor() {
        List<Recipe> list = List.of(MASALA_DOSA);
        recipeBook.setRecipes(list);
        RecipeBook n = new RecipeBook(recipeBook);
        assertTrue(n.hasRecipe(MASALA_DOSA));
    }

    @Test
    public void test_toString() {
        assertEquals("0 recipes", recipeBook.toString());
    }

    /**
     * A stub ReadOnlyRecipeBook whose recipes list can violate interface constraints.
     */
    private static class RecipeBookStub implements ReadOnlyRecipeBook {
        private final ObservableList<Recipe> recipes = FXCollections.observableArrayList();

        RecipeBookStub(Collection<Recipe> recipes) {
            this.recipes.setAll(recipes);
        }

        @Override
        public ObservableList<Recipe> getRecipeList() {
            return recipes;
        }
    }
}
