package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.BLUEBERRY_PANCAKES;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_DURATION;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_INGREDIENTS;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_NAME;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_PORTION;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_STEPS;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_STRING;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_TAGS;
import static seedu.recipe.testutil.TypicalRecipes.FISH_AND_CHIPS;
import static seedu.recipe.testutil.TypicalRecipes.GRILLED_CHEESE;
import static seedu.recipe.testutil.TypicalRecipes.MASALA_DOSA;

import org.junit.jupiter.api.Test;

import seedu.recipe.model.recipe.exceptions.RecipeDurationNotPresentException;
import seedu.recipe.model.recipe.exceptions.RecipePortionNotPresentException;
import seedu.recipe.model.tag.Tag;
import seedu.recipe.testutil.RecipeBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class RecipeTest {
    // Varying fields
    private static final Recipe FISH_AND_CHIPS_DIFF_PORTION = new RecipeBuilder(
            FISH_AND_CHIPS.getName(), MASALA_DOSA.getPortion(),
            FISH_AND_CHIPS.getDuration(), FISH_AND_CHIPS.getTags(),
            FISH_AND_CHIPS.getIngredients(), FISH_AND_CHIPS.getSteps()).build();

    private static final Recipe FISH_AND_CHIPS_DIFF_DURATION = new RecipeBuilder(
            FISH_AND_CHIPS.getName(), FISH_AND_CHIPS.getPortion(),
            BLUEBERRY_PANCAKES.getDuration(), FISH_AND_CHIPS.getTags(),
            FISH_AND_CHIPS.getIngredients(), FISH_AND_CHIPS.getSteps()).build();

    private static final Recipe FISH_AND_CHIPS_DIFF_TAGS = new RecipeBuilder(
            FISH_AND_CHIPS.getName(), FISH_AND_CHIPS.getPortion(),
            FISH_AND_CHIPS.getDuration(), Set.of(new Tag("fake tag")),
            FISH_AND_CHIPS.getIngredients(), FISH_AND_CHIPS.getSteps()).build();

    private static final Recipe FISH_AND_CHIPS_DIFF_INGREDIENTS = new RecipeBuilder(
            FISH_AND_CHIPS.getName(), FISH_AND_CHIPS.getPortion(),
            FISH_AND_CHIPS.getDuration(), FISH_AND_CHIPS.getTags(),
            MASALA_DOSA.getIngredients(), FISH_AND_CHIPS.getSteps()).build();

    private static final Recipe FISH_AND_CHIPS_DIFF_STEPS = new RecipeBuilder(
            FISH_AND_CHIPS.getName(), FISH_AND_CHIPS.getPortion(),
            FISH_AND_CHIPS.getDuration(), FISH_AND_CHIPS.getTags(),
            FISH_AND_CHIPS.getIngredients(), MASALA_DOSA.getSteps()).build();

    //Name variations
    private static final Recipe DOSA_COPY_DIFF_NAME = new RecipeBuilder(
            new Name("fake dosa"), MASALA_DOSA.getPortion(),
            MASALA_DOSA.getDuration(), MASALA_DOSA.getTags(),
            MASALA_DOSA.getIngredients(), MASALA_DOSA.getSteps()).build();

    private static final Recipe GRILLED_CHEESE_DIFF_CASE = new RecipeBuilder(
            new Name("pan-fried camembert sandwich"), GRILLED_CHEESE.getPortion(),
            GRILLED_CHEESE.getDuration(), GRILLED_CHEESE.getTags(),
            GRILLED_CHEESE.getIngredients(), GRILLED_CHEESE.getSteps()).build();

    private static final Recipe CACIO_TRAILING_SPACE = new RecipeBuilder(
            new Name("Cacio e Pepe "), CACIO_E_PEPE.getPortion(),
            CACIO_E_PEPE.getDuration(), CACIO_E_PEPE.getTags(),
            CACIO_E_PEPE.getIngredients(), CACIO_E_PEPE.getSteps()).build();

    //Deep copying
    private static final Recipe CACIO_DEEP_COPY = new RecipeBuilder(
            CACIO_E_PEPE.getName(), CACIO_E_PEPE.getPortion(),
            CACIO_E_PEPE.getDuration(), CACIO_E_PEPE.getTags(),
            CACIO_E_PEPE.getIngredients(), CACIO_E_PEPE.getSteps()).build();

    //Querying when fields not present
    private static final Recipe CACIO_NAME_ONLY = new Recipe(CACIO_E_PEPE.getName());

    // Name and Constructor logic
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Recipe(null));
    }

    @Test
    public void getName() {
        assertEquals(CACIO_NAME, CACIO_E_PEPE.getName());
    }

    //Portion logic
    @Test
    public void getNullPortion_throwsRecipePortionNotPresentException() {
        assertThrows(RecipePortionNotPresentException.class, CACIO_NAME_ONLY::getPortion);
    }

    @Test
    public void getPortionNonNull() {
        assertEquals(CACIO_PORTION, CACIO_E_PEPE.getPortionNullable());
        assertEquals(CACIO_PORTION, CACIO_E_PEPE.getPortion());
    }

    @Test
    public void setCacioPortion() {
        Recipe test = new RecipeBuilder(CACIO_E_PEPE).build();
        RecipePortion testPortion = RecipePortion.of("8 - 10 servings");
        test.setPortion(testPortion);
        assertEquals(testPortion, test.getPortion());
    }

    //Duration logic
    @Test
    public void getNullDuration_throwsRecipeDurationNotPresentException() {
        assertThrows(RecipeDurationNotPresentException.class, CACIO_NAME_ONLY::getDuration);
    }

    @Test
    public void getDurationNonNull() {
        assertEquals(CACIO_DURATION, CACIO_E_PEPE.getDurationNullable());
        assertEquals(CACIO_DURATION, CACIO_E_PEPE.getDuration());
    }

    @Test
    public void setCacioDuration() {
        Recipe test = new RecipeBuilder(CACIO_E_PEPE).build();
        RecipeDuration testDuration = RecipeDuration.of("20 hours");
        test.setDuration(testDuration);
        assertEquals(testDuration, test.getDuration());
    }

    //Tag logic
    @Test
    public void getTags() {
        assertEquals(CACIO_TAGS, CACIO_E_PEPE.getTags());
    }

    @Test
    public void setTags() {
        Set<Tag> newTagSet = new HashSet<>(CACIO_TAGS);
        Tag[] tagsToAdd = new Tag[]{
                new Tag("Tag one"),
                new Tag("Tag two")
        };
        Recipe test = new RecipeBuilder(CACIO_E_PEPE).build();
        test.setTags(tagsToAdd);
        newTagSet.addAll(Set.of(tagsToAdd));
        assertEquals(newTagSet, test.getTags());
    }

    //Ingredient logic
    @Test
    public void getIngredients() {
        assertEquals(CACIO_INGREDIENTS, CACIO_E_PEPE.getIngredients());
    }

    @Test
    public void setIngredients() {
        List<Ingredient> newIngredientList = new ArrayList<>(CACIO_INGREDIENTS);
        Ingredient[] ingredientsToAdd = new Ingredient[]{
                new Ingredient("Ingredient one"),
                new Ingredient("Ingredient two")
        };
        Recipe test = new RecipeBuilder(CACIO_E_PEPE).build();
        test.setIngredients(ingredientsToAdd);
        newIngredientList.addAll(List.of(ingredientsToAdd));
        assertEquals(newIngredientList, test.getIngredients());
    }

    //Ingredient logic
    @Test
    public void getSteps() {
        assertEquals(CACIO_STEPS, CACIO_E_PEPE.getSteps());
    }

    @Test
    public void setSteps() {
        Step[] newSteps = new Step[]{
                new Step("Step one"),
                new Step("Step two")
        };
        Recipe test = new RecipeBuilder(CACIO_E_PEPE).build();
        test.setSteps(newSteps);
        assertEquals(List.of(newSteps), test.getSteps());
    }

    //Equality
    @Test
    public void isSameRecipe() {
        // same object -> returns true
        assertTrue(FISH_AND_CHIPS.isSameRecipe(FISH_AND_CHIPS));

        // null -> returns false
        assertFalse(FISH_AND_CHIPS.isSameRecipe(null));

        // same name, some other attributes different -> returns true
        assertTrue(FISH_AND_CHIPS.isSameRecipe(FISH_AND_CHIPS_DIFF_TAGS));

        // different name, all other attributes same -> returns false
        assertFalse(MASALA_DOSA.isSameRecipe(DOSA_COPY_DIFF_NAME));

        // name differs in case, all other attributes same -> returns false
        assertFalse(GRILLED_CHEESE.isSameRecipe(GRILLED_CHEESE_DIFF_CASE));

        // name has trailing spaces, all other attributes same -> returns false
        assertFalse(CACIO_E_PEPE.isSameRecipe(CACIO_TRAILING_SPACE));
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertEquals(CACIO_E_PEPE, CACIO_DEEP_COPY);

        // same object -> returns true
        assertEquals(BLUEBERRY_PANCAKES, BLUEBERRY_PANCAKES);

        // null -> returns false
        assertNotEquals(MASALA_DOSA, null);

        // different type -> returns false
        assertNotEquals(FISH_AND_CHIPS, 2);

        // different recipe -> returns false
        assertNotEquals(MASALA_DOSA, FISH_AND_CHIPS);

        // different name -> returns false
        assertNotEquals(MASALA_DOSA, DOSA_COPY_DIFF_NAME);

        // different portion -> returns false
        assertNotEquals(FISH_AND_CHIPS, FISH_AND_CHIPS_DIFF_PORTION);

        // different duration -> returns false
        assertNotEquals(FISH_AND_CHIPS, FISH_AND_CHIPS_DIFF_DURATION);

        // different tags -> returns false
        assertNotEquals(FISH_AND_CHIPS, FISH_AND_CHIPS_DIFF_TAGS);

        // different ingredients -> returns false
        assertNotEquals(FISH_AND_CHIPS, FISH_AND_CHIPS_DIFF_INGREDIENTS);

        // different steps -> returns false
        assertNotEquals(FISH_AND_CHIPS, FISH_AND_CHIPS_DIFF_STEPS);
    }

    @Test
    public void testHashCode() {
        int hash = Objects.hash(CACIO_NAME, CACIO_PORTION, CACIO_DURATION, CACIO_TAGS, CACIO_INGREDIENTS, CACIO_STEPS);
        assertEquals(hash, CACIO_E_PEPE.hashCode());
    }

    @Test
    public void testString() {
        assertEquals(CACIO_STRING, CACIO_E_PEPE.toString());
    }
}
