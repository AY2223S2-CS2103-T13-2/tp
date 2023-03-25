package seedu.recipe.model.recipe;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.recipe.commons.util.StringUtil;

/**
 * Tests that a collection of a property of a {@code Recipe} matches any of the keywords given.
 * A property is a field in the Recipe, e.g. its Name, Tags, or Ingredients.
 *
 * @param <T> Type of the property to be tested
 */
public class PropertyCollectionContainsKeywordsPredicate<T> implements Predicate<Recipe> {
    private final List<String> keywords;
    /**
     * This method gets the name string of property T from Recipe.
     */
    private final Function<Recipe, Collection<T>> propertyGetter;
    private final Function<T, String> nameGetter;
    private final Function<Recipe, Collection<String>> getter;

    /**
     * Constructs an instance of PropertyCollectionContainsKeywordsPredicate.
     *
     * @param keywords       A list of keywords to test against the property name
     * @param propertyGetter A method that returns the collection of property from a Recipe
     * @param nameGetter     A method that gets the name of a property
     */
    public PropertyCollectionContainsKeywordsPredicate(List<String> keywords,
                                                       Function<Recipe, Collection<T>> propertyGetter,
                                                       Function<T, String> nameGetter) {
        this.keywords = keywords;
        this.propertyGetter = propertyGetter;
        this.nameGetter = nameGetter;
        this.getter = (recipe) -> propertyGetter.apply(recipe).stream().map(nameGetter).collect(Collectors.toList());
    }

    /**
     * Constructs an instance of PropertyCollectionContainsKeywordsPredicate.
     *
     * @param keywords       An array of keywords to test against the property name
     * @param propertyGetter A method that returns the collection of property from a Recipe
     * @param nameGetter     A method that gets the name of a property
     */
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
                    name -> StringUtil.containsWordIgnoreCase(name, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PropertyCollectionContainsKeywordsPredicate<?> // instanceof handles nulls
            && keywords.equals(((PropertyCollectionContainsKeywordsPredicate<?>) other).keywords)
            && propertyGetter.equals(((PropertyCollectionContainsKeywordsPredicate<?>) other).propertyGetter)
            && nameGetter.equals(((PropertyCollectionContainsKeywordsPredicate<?>) other).nameGetter)); // state check
    }

}
