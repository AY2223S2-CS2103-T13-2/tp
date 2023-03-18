package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;
import static seedu.recipe.testutil.TypicalRecipes.MASALA_DOSA;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.recipe.model.recipe.exceptions.DuplicateRecipeException;
import seedu.recipe.model.recipe.exceptions.RecipeNotFoundException;
import seedu.recipe.model.tag.Tag;
import seedu.recipe.testutil.RecipeBuilder;

public class UniqueRecipeListTest {

    private final UniqueRecipeList uniqueRecipeList = new UniqueRecipeList();

    @Test
    public void contains_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.contains(null));
    }

    @Test
    public void contains_recipeNotInList_returnsFalse() {
        assertFalse(uniqueRecipeList.contains(CACIO_E_PEPE));
    }

    @Test
    public void contains_recipeInList_returnsTrue() {
        uniqueRecipeList.add(CACIO_E_PEPE);
        assertTrue(uniqueRecipeList.contains(CACIO_E_PEPE));
    }

    @Test
    public void contains_recipeWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRecipeList.add(CACIO_E_PEPE);
        Recipe editedCacioEPepe = CACIO_E_PEPE;
        editedCacioEPepe.setTags(new Tag("Fusion"));
        assertTrue(uniqueRecipeList.contains(editedCacioEPepe));
    }

    @Test
    public void add_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.add(null));
    }

    @Test
    public void add_duplicateRecipe_throwsDuplicateRecipeException() {
        uniqueRecipeList.add(CACIO_E_PEPE);
        assertThrows(DuplicateRecipeException.class, () -> uniqueRecipeList.add(CACIO_E_PEPE));
    }

    @Test
    public void setRecipe_nullTargetRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipe(null, CACIO_E_PEPE));
    }

    @Test
    public void setRecipe_nullEditedRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipe(CACIO_E_PEPE, null));
    }

    @Test
    public void setRecipe_targetRecipeNotInList_throwsRecipeNotFoundException() {
        assertThrows(RecipeNotFoundException.class, () -> uniqueRecipeList.setRecipe(CACIO_E_PEPE, CACIO_E_PEPE));
    }

    @Test
    public void setRecipe_editedRecipeIsSameRecipe_success() {
        uniqueRecipeList.add(CACIO_E_PEPE);
        uniqueRecipeList.setRecipe(CACIO_E_PEPE, CACIO_E_PEPE);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(CACIO_E_PEPE);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipe_editedRecipeHasSameIdentity_success() {
        uniqueRecipeList.add(CACIO_E_PEPE);
        Recipe editedCacioEPepe = new RecipeBuilder(CACIO_E_PEPE).build();
        editedCacioEPepe.setTags(new Tag("Pasta"));
        editedCacioEPepe.setIngredients(new Ingredient("3 rashes bacon"));

        uniqueRecipeList.setRecipe(CACIO_E_PEPE, editedCacioEPepe);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(editedCacioEPepe);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipe_editedRecipeHasDifferentIdentity_success() {
        uniqueRecipeList.add(CACIO_E_PEPE);
        uniqueRecipeList.setRecipe(CACIO_E_PEPE, MASALA_DOSA);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(MASALA_DOSA);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipe_editedRecipeHasNonUniqueIdentity_throwsDuplicateRecipeException() {
        uniqueRecipeList.add(CACIO_E_PEPE);
        uniqueRecipeList.add(MASALA_DOSA);
        assertThrows(DuplicateRecipeException.class, () -> uniqueRecipeList.setRecipe(CACIO_E_PEPE, MASALA_DOSA));
    }

    @Test
    public void remove_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.remove(null));
    }

    @Test
    public void remove_recipeDoesNotExist_throwsRecipeNotFoundException() {
        assertThrows(RecipeNotFoundException.class, () -> uniqueRecipeList.remove(CACIO_E_PEPE));
    }

    @Test
    public void remove_existingRecipe_removesRecipe() {
        uniqueRecipeList.add(CACIO_E_PEPE);
        uniqueRecipeList.remove(CACIO_E_PEPE);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipes_nullUniqueRecipeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipes((UniqueRecipeList) null));
    }

    @Test
    public void setRecipes_uniqueRecipeList_replacesOwnListWithProvidedUniqueRecipeList() {
        uniqueRecipeList.add(CACIO_E_PEPE);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(MASALA_DOSA);
        uniqueRecipeList.setRecipes(expectedUniqueRecipeList);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipes_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipes((List<Recipe>) null));
    }

    @Test
    public void setRecipes_list_replacesOwnListWithProvidedList() {
        uniqueRecipeList.add(CACIO_E_PEPE);
        List<Recipe> recipeList = Collections.singletonList(MASALA_DOSA);
        uniqueRecipeList.setRecipes(recipeList);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(MASALA_DOSA);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);

    }

    @Test
    public void setRecipes_listWithDuplicateRecipes_throwsDuplicateRecipeException() {
        List<Recipe> listWithDuplicateRecipes = Arrays.asList(CACIO_E_PEPE, CACIO_E_PEPE);
        assertThrows(DuplicateRecipeException.class, () -> uniqueRecipeList.setRecipes(listWithDuplicateRecipes));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueRecipeList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void testIterator() {
        uniqueRecipeList.add(CACIO_E_PEPE);
        uniqueRecipeList.add(MASALA_DOSA);
        Iterator<Recipe> iterator = uniqueRecipeList.iterator();
        assertEquals(CACIO_E_PEPE, iterator.next());
        assertEquals(MASALA_DOSA, iterator.next());
    }
}
