package seedu.recipe.model.recipe;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.recipe.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code Name} matches any of the keywords given.
 */
public class PropertyCollectionContainsKeywordsPredicate<T> implements Predicate<Recipe> {
    private final List<String> keywords;
    /**
     * This method gets the name string of property T from Recipe.
     */
    private final Function<Recipe, Collection<String>> getter;


    public PropertyCollectionContainsKeywordsPredicate(List<String> keywords,
                                                       Function<Recipe, Collection<T>> propertyGetter,
                                                       Function<T, String> nameGetter) {
        this.keywords = keywords;
        this.getter = (recipe) -> propertyGetter.apply(recipe).stream().map(nameGetter).collect(Collectors.toList());
    }

    public PropertyCollectionContainsKeywordsPredicate(String[] keywords,
                                                       Function<Recipe, Collection<T>> propertyGetter,
                                                       Function<T, String> nameGetter) {
        this(Arrays.asList(keywords), propertyGetter, nameGetter);
    }

    @Override
    public boolean test(Recipe recipe) {
        // check if any of the keywords match any property in the collection
        return keywords.stream()
            .anyMatch(
                keyword -> getter.apply(recipe).stream().anyMatch(
                    (name) -> StringUtil.containsWordIgnoreCase(name, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PropertyCollectionContainsKeywordsPredicate<?> // instanceof handles nulls
            && keywords.equals(((PropertyCollectionContainsKeywordsPredicate<?>) other).keywords)
            && getter.equals(((PropertyCollectionContainsKeywordsPredicate<?>) other).getter)); // state check
    }

}
