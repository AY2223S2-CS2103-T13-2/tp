package seedu.recipe.model.recipe;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import seedu.recipe.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code Name} matches any of the keywords given.
 */
public class PropertyNameContainsKeywordsPredicate<T> implements Predicate<Recipe> {
    private final List<String> keywords;
    /**
     * This method gets the name string of property T from Recipe.
     */
    private final Function<Recipe, String> getter;


    public PropertyNameContainsKeywordsPredicate(List<String> keywords, Function<Recipe, T> propertyGetter,
                                                 Function<T, String> nameGetter) {
        this.keywords = keywords;
        this.getter = propertyGetter.andThen(nameGetter);
    }

    public PropertyNameContainsKeywordsPredicate(String[] keywords, Function<Recipe, T> propertyGetter,
                                                 Function<T, String> nameGetter) {
        this(Arrays.asList(keywords), propertyGetter, nameGetter);
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
            || (other instanceof PropertyNameContainsKeywordsPredicate<?> // instanceof handles nulls
            && keywords.equals(((PropertyNameContainsKeywordsPredicate<?>) other).keywords)
            && getter.equals(((PropertyNameContainsKeywordsPredicate<?>) other).getter)); // state check
    }

}
