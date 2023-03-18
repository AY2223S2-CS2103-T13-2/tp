package seedu.recipe.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.commons.util.AppUtil.checkArgument;

/**
 * Represents a recipe's ingredient in the recipe book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIngredient(String)}
 */
public class Ingredient {

    public static final String MESSAGE_CONSTRAINTS =
            "Ingredient contains alphanumeric characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX =
            "^(([1-9][0-9]*|(([0-9]|[1-9][0-9]+)[./][0-9]+))([a-z.-]+)?|[A-Za-z().,/-]+)(\\s+[0-9A-Za-z().,+-/:;]+)*$";
    public final String name;

    /**
     * Constructs a {@code Ingredient}.
     *
     * @param name A valid ingredient number.
     */
    public Ingredient(String name) {
        requireNonNull(name);
        checkArgument(isValidIngredient(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid ingredient.
     */
    public static boolean isValidIngredient(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Ingredient // instanceof handles nulls
                && name.equals(((Ingredient) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
