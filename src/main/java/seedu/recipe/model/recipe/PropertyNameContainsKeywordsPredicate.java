package seedu.recipe.model.recipe;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import seedu.recipe.commons.util.StringUtil;

/**
 * Tests that the property name of a {@code Recipe} matches any of the keywords given.
 * A property is a field in the Recipe, e.g. its Name, Tags, or Ingredients.
 *
 * @param <T> Type of the property to be tested
 */
public class PropertyNameContainsKeywordsPredicate<T> implements Predicate<Recipe> {
    private final List<String> keywords;
    /**
     * This method gets the name string of property T from Recipe.
     */
    private final Function<Recipe, T> propertyGetter;
    private final Function<T, String> nameGetter;


    /**
     * Constructs an instance of PropertyNameContainsKeywordsPredicate.
     *
     * @param keywords       A list of keywords to test against the property name
     * @param propertyGetter A method that returns the property from a Recipe
     * @param nameGetter     A method that gets the name of a property
     */
    public PropertyNameContainsKeywordsPredicate(List<String> keywords, Function<Recipe, T> propertyGetter,
                                                 Function<T, String> nameGetter) {
        this.keywords = keywords;
        this.propertyGetter = propertyGetter;
        this.nameGetter = nameGetter;

    }

    /**
     * Constructs an instance of PropertyNameContainsKeywordsPredicate.
     *
     * @param keywords       An array of keywords to test against the property name
     * @param propertyGetter A method that returns the property from a Recipe
     * @param nameGetter     A method that gets the name of a property
     */
    public PropertyNameContainsKeywordsPredicate(String[] keywords, Function<Recipe, T> propertyGetter,
                                                 Function<T, String> nameGetter) {
        this(Arrays.asList(keywords), propertyGetter, nameGetter);
    }

    /**
     * Tests if the target property of the given Recipe matches any of the keywords.
     *
     * @param recipe the input Recipe
     * @return Whether any of the keywords match the target property
     */
    @Override
    public boolean test(Recipe recipe) {
        Function<Recipe, String> getter = propertyGetter.andThen(nameGetter);
        return keywords.stream()
            .anyMatch(
                keyword -> StringUtil.containsWordIgnoreCase(getter.apply(recipe), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PropertyNameContainsKeywordsPredicate<?> // instanceof handles nulls
            && keywords.equals(((PropertyNameContainsKeywordsPredicate<?>) other).keywords)
            && propertyGetter.equals(((PropertyNameContainsKeywordsPredicate<?>) other).propertyGetter)
            && nameGetter.equals(((PropertyNameContainsKeywordsPredicate<?>) other).nameGetter)); // state check
    }

}
