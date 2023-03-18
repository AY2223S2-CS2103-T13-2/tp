package seedu.recipe.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.commons.util.AppUtil.checkArgument;

/**
 * Represents a Recipe's name in the recipe book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX =
            "^(([1-9][0-9]?(\\-)?\\s*)?[A-Za-z]+)(\\-[A-Za-z]+)?(\\s+[A-Za-z0-9\\-]*)*$";

    public final String recipeName;

    /**
     * Constructs a {@code Name}.
     *
     * @param recipeName A valid name.
     */
    public Name(String recipeName) {
        requireNonNull(recipeName);
        checkArgument(isValidName(recipeName), MESSAGE_CONSTRAINTS + "{" + recipeName + "}");
        this.recipeName = recipeName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return recipeName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && recipeName.equals(((Name) other).recipeName)); // state check
    }

    @Override
    public int hashCode() {
        return recipeName.hashCode();
    }



}
