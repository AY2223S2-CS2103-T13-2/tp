package seedu.recipe.model.recipe.ingredient;

import static seedu.recipe.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents an ingredient that is used in a {@code Recipe}.
 */
public class Ingredient implements Comparable<Ingredient> {
    public static final String MESSAGE_CONSTRAINTS = "Invalid ingredient name: %s\n"
        + "An ingredient should be made up of one or more groups of "
        + "whitespace separated alphabetic characters. \nThese characters may also be separated by "
        + "singular hyphens, such as 'self-raising flour'.";
    private static final String WORD_GROUP = "[A-Za-z]+(\\-[A-Za-z]+)?";
    public static final String VALIDATION_REGEX = String.format("^%s(\\s+%s)*$", WORD_GROUP, WORD_GROUP);

    //Data fields
    private final String name;
    private String commonName = "";

    private Ingredient(String name) {
        this.name = name;
    }

    /**
     * Given a String ingredient name, parses it for validity and returns a new
     * Ingredient instance if it is valid.
     *
     * @param s The Ingredient name to construct from.
     * @return The generated Ingredient instance.
     */
    public static Ingredient of(String s) {
        assert s != null;
        checkArgument(isValidIngredientName(s),
            String.format(MESSAGE_CONSTRAINTS, s));
        return new Ingredient(s);
    }

    /**
     * Given a String ingredient name, parses it for validity and returns a
     * boolean value indicating if it is valid.
     *
     * @param s The ingredient name to test.
     * @return True if the ingredient name is valid, False otherwise.
     */
    public static boolean isValidIngredientName(String s) {
        return s.matches(VALIDATION_REGEX);
    }

    @Override
    // converting all char in IngredientName to lowercase to make it such that hashing is case-insensitive
    // i.e. 'KFC' will return the same hashcode as 'kfc'
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    @Override
    public String toString() {
        if (commonName.length() > 0) {
            return String.format("%s (aka %s)", name, commonName);
        }
        return name;
    }

    @Override
    public boolean equals(Object o) {
        return o == this
            || o instanceof Ingredient
            && ((Ingredient) o).name.equalsIgnoreCase(this.name);
    }

    @Override
    public int compareTo(Ingredient other) {
        return name.compareTo(other.name);
    }

    public String getName() {
        return this.name;
    }

    public String getCommonName() {
        return this.commonName;
    }

    /**
     * Set the common name of this Ingredient.
     *
     * @param s The common name to be set.
     */
    public void setCommonName(String s) {
        assert s != null;
        checkArgument(isValidIngredientName(s), String.format(MESSAGE_CONSTRAINTS, s));
        this.commonName = s;
    }

    public String getNames() {
        String s = String.format("%s %s", this.name, this.commonName);
        return s;
    }
}
