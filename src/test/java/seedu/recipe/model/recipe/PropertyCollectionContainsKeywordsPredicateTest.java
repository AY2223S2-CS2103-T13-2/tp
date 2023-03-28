package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.testutil.TypicalRecipes.BLUEBERRY_PANCAKES;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;
import static seedu.recipe.testutil.TypicalRecipes.GRILLED_CHEESE;
import static seedu.recipe.testutil.TypicalRecipes.MASALA_DOSA;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.util.FindUtil;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.tag.Tag;

public class PropertyCollectionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        // testing equals for Tag
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        List<String> thirdPredicateKeywordList = Arrays.asList("first", "third");

        PropertyCollectionContainsKeywordsPredicate<Tag> firstPredicate = new
            PropertyCollectionContainsKeywordsPredicate<Tag>(
            firstPredicateKeywordList, FindUtil.GET_TAGS_FROM_RECIPE, FindUtil.GET_TAG_STRING);
        PropertyCollectionContainsKeywordsPredicate<Tag> secondPredicate = new
            PropertyCollectionContainsKeywordsPredicate<Tag>(
            secondPredicateKeywordList, FindUtil.GET_TAGS_FROM_RECIPE, FindUtil.GET_TAG_STRING);
        PropertyCollectionContainsKeywordsPredicate<Tag> thirdPredicate = new
            PropertyCollectionContainsKeywordsPredicate<Tag>(
            secondPredicateKeywordList, FindUtil.GET_TAGS_FROM_RECIPE, FindUtil.GET_TAG_STRING);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        PropertyCollectionContainsKeywordsPredicate<Tag> firstPredicateCopy =
            new PropertyCollectionContainsKeywordsPredicate<Tag>(
                firstPredicateKeywordList, FindUtil.GET_TAGS_FROM_RECIPE, FindUtil.GET_TAG_STRING);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different but equal values -> returns true
        assertEquals(secondPredicate, thirdPredicate);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different recipe -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_tagsContainKeywords_returnsTrue() {
        // Only one keyword, one matching
        PropertyCollectionContainsKeywordsPredicate<Tag> predicate =
            new PropertyCollectionContainsKeywordsPredicate<Tag>(Collections.singletonList("Indian"),
                FindUtil.GET_TAGS_FROM_RECIPE,
                FindUtil.GET_TAG_STRING);
        assertTrue(predicate.test(MASALA_DOSA));

        // Multiple keywords, multiple matching
        predicate = new PropertyCollectionContainsKeywordsPredicate<Tag>(Arrays.asList("American", "Breakfast"),
            FindUtil.GET_TAGS_FROM_RECIPE,
            FindUtil.GET_TAG_STRING);
        assertTrue(predicate.test(BLUEBERRY_PANCAKES));

        // Multiple keywords, only one matching
        predicate = new PropertyCollectionContainsKeywordsPredicate<Tag>(Arrays.asList("Italian", "Asian"),
            FindUtil.GET_TAGS_FROM_RECIPE,
            FindUtil.GET_TAG_STRING);
        assertTrue(predicate.test(CACIO_E_PEPE));

        // Mixed-case keywords, matching
        predicate = new PropertyCollectionContainsKeywordsPredicate<Tag>(Arrays.asList("enGlIsh", "dEliCiOus"),
            FindUtil.GET_TAGS_FROM_RECIPE,
            FindUtil.GET_TAG_STRING);
        assertTrue(predicate.test(GRILLED_CHEESE));
    }

    @Test
    public void test_tagsDoNotContainKeywords_returnsFalse() {
        // Zero keywords
        PropertyCollectionContainsKeywordsPredicate<Tag> predicate =
            new PropertyCollectionContainsKeywordsPredicate<Tag>(
                Collections.emptyList(), FindUtil.GET_TAGS_FROM_RECIPE, FindUtil.GET_TAG_STRING);
        assertFalse(predicate.test(GRILLED_CHEESE));

        // Non-matching keyword
        predicate = new PropertyCollectionContainsKeywordsPredicate<Tag>(Arrays.asList("Steak"),
            FindUtil.GET_TAGS_FROM_RECIPE,
            FindUtil.GET_TAG_STRING);
        assertFalse(predicate.test(GRILLED_CHEESE));

        // Keywords match fields, but does not match tags
        predicate = new PropertyCollectionContainsKeywordsPredicate<Tag>(
            Arrays.asList("Pan-fried", "camembert", "sandwich", "balsamic", "butter"),
            FindUtil.GET_TAGS_FROM_RECIPE,
            FindUtil.GET_TAG_STRING);
        assertFalse(predicate.test(GRILLED_CHEESE));
    }

    @Test
    public void test_ingredientsContainKeywords_returnsTrue() {
        // Only one keyword, one matching
        PropertyCollectionContainsKeywordsPredicate<Ingredient> predicate =
            new PropertyCollectionContainsKeywordsPredicate<Ingredient>(Collections.singletonList("pecorino"),
                FindUtil.GET_INGREDIENTS_FROM_RECIPE,
                FindUtil.GET_INGREDIENT_STRING);
        assertTrue(predicate.test(CACIO_E_PEPE));

        // Multiple keywords, multiple matching
        predicate = new PropertyCollectionContainsKeywordsPredicate<Ingredient>(Arrays.asList("pecorino", "pepper"),
            FindUtil.GET_INGREDIENTS_FROM_RECIPE,
            FindUtil.GET_INGREDIENT_STRING);
        assertTrue(predicate.test(CACIO_E_PEPE));

        // Multiple keywords, only one matching
        predicate = new PropertyCollectionContainsKeywordsPredicate<Ingredient>(Arrays.asList("pepper", "sesame"),
            FindUtil.GET_INGREDIENTS_FROM_RECIPE,
            FindUtil.GET_INGREDIENT_STRING);
        assertTrue(predicate.test(CACIO_E_PEPE));

        // Mixed-case keywords, matching
        predicate = new PropertyCollectionContainsKeywordsPredicate<Ingredient>(Arrays.asList("gRaNa", "pEcOriNO"),
            FindUtil.GET_INGREDIENTS_FROM_RECIPE,
            FindUtil.GET_INGREDIENT_STRING);
        assertTrue(predicate.test(CACIO_E_PEPE));
    }

    @Test
    public void test_ingredientsDoNotContainKeywords_returnsFalse() {
        // Zero keywords
        PropertyCollectionContainsKeywordsPredicate<Ingredient> predicate =
            new PropertyCollectionContainsKeywordsPredicate<Ingredient>(
                Collections.emptyList(), FindUtil.GET_INGREDIENTS_FROM_RECIPE, FindUtil.GET_INGREDIENT_STRING);
        assertFalse(predicate.test(GRILLED_CHEESE));

        // Non-matching keyword
        predicate = new PropertyCollectionContainsKeywordsPredicate<Ingredient>(Arrays.asList("Steak"),
            FindUtil.GET_INGREDIENTS_FROM_RECIPE,
            FindUtil.GET_INGREDIENT_STRING);
        assertFalse(predicate.test(GRILLED_CHEESE));

        // Keywords match fields, but does not match ingredients
        predicate = new PropertyCollectionContainsKeywordsPredicate<Ingredient>(
            Arrays.asList("sandwich", "english", "comfort"),
            FindUtil.GET_INGREDIENTS_FROM_RECIPE,
            FindUtil.GET_INGREDIENT_STRING);
        assertFalse(predicate.test(GRILLED_CHEESE));
    }
}
