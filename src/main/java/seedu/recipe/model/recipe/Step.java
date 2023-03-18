package seedu.recipe.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.commons.util.AppUtil.checkArgument;

/**
 * Represents one of a recipe's steps for a recipe in the recipe book.
 * Guarantees: immutable, is valid as declared in {@link #isValidStep(String)}
 */
public class Step {
    public static final String MESSAGE_CONSTRAINTS =
            "Steps should consist of 2 or more space separated alphanumeric words, and "
            + "should not be blank. Singular tokens such as '.', ',', ':', ';', '(', ')' are allowed,"
            + "but their use should be kept to a minimum.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input. The string
     * should also not end with whitespace.
     */
    public static final String VALIDATION_REGEX =
            "^[A-Za-z0-9]+([\\-,/.][A-Za-z0-9]+)?[.,:;]?"
            + "(\\s+[(]?([A-Za-z0-9]+|[0-9]+\\.[0-9]+)([\\-,/.]([A-Za-z0-9]+|([0-9]+\\.[0-9]+)))?[.,!:;)]{0,2})+";

    private final String description;

    /**
     * Constructs a {@code Step}
     * @param description A valid description for the step
     */
    public Step(String description) {
        requireNonNull(description);
        checkArgument(isValidStep(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid step.
     */
    public static boolean isValidStep(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o == this
                || o instanceof Step
                && ((Step) o).description.equals(this.description);
    }
}
