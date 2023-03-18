package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.recipe.model.tag.Tag;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different recipe -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }
    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Lasagna"));
        assertTrue(predicate.test(new Recipe(new Name("Lasagna"))));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Grilled", "Cheese"));
        assertTrue(predicate.test(new Recipe(new Name("Grilled Cheese"))));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Steamed", "Baked"));
        assertTrue(predicate.test(new Recipe(new Name("Baked Rice"))));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("pork", "hAlaL"));
        assertTrue(predicate.test(new Recipe(new Name("Halal Bak Kut Teh"))));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new Recipe(new Name("Grilled Cheese"))));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Steak"));
        assertFalse(predicate.test(new Recipe(new Name("Grilled Cheese"))));

        // Keywords match fields, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Butter", "Onions", "Halibut", "Mediterranean"));
        Recipe r = new Recipe(new Name("Pan-roasted fish"));
        r.setPortion(RecipePortion.of("1 serving"));
        r.setDuration(RecipeDuration.of("2 h"));
        r.setTags(new Tag("Mediterranean"));
        r.setIngredients(new Ingredient("Butter"), new Ingredient("Onions"), new Ingredient("Halibut"));
        r.setSteps(new Step("Descale the fish with a paring knife."));
        assertFalse(predicate.test(r));
    }
}
