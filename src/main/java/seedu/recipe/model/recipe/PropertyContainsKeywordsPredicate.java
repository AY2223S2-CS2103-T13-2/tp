package seedu.recipe.model.recipe;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import seedu.recipe.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code Name} matches any of the keywords given.
 */
public class PropertyContainsKeywordsPredicate<T> implements Predicate<Recipe> {
    private final List<String> keywords;
    /**
     * This method gets the name string of property T from Recipe.
     */
    private final Function<Recipe, String> getter;


    public PropertyContainsKeywordsPredicate(List<String> keywords, Function<Recipe, T> propertyGetter,
                                             Function<T, String> nameGetter) {
        this.keywords = keywords;
        this.getter = propertyGetter.andThen(nameGetter);
    }

    @Override
    public boolean test(Recipe recipe) {
        return keywords.stream()
            .anyMatch(
                keyword -> StringUtil.containsWordIgnoreCase(getter.apply(recipe), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PropertyContainsKeywordsPredicate<?> // instanceof handles nulls
            && keywords.equals(((PropertyContainsKeywordsPredicate<?>) other).keywords)
            && getter.equals(((PropertyContainsKeywordsPredicate<?>) other).getter)); // state check
    }

}
