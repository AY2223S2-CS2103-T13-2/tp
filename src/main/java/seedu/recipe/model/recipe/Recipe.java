package seedu.recipe.model.recipe;

import static seedu.recipe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.*;

import seedu.recipe.model.recipe.exceptions.RecipeDurationNotPresentException;
import seedu.recipe.model.recipe.exceptions.RecipePortionNotPresentException;
import seedu.recipe.model.tag.Tag;

/**
 * Represents a Recipe in the recipe book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class Recipe {

    // Identity field
    private final Name name;

    // Data fields
    private Optional<RecipePortion> portion = Optional.empty();
    private Optional<RecipeDuration> duration = Optional.empty();
    private Set<Tag> tags = Set.of();
    private List<Ingredient> ingredients = List.of();
    private List<Step> steps = List.of();

    /**
     * Only the name field is required. The rest are optional (but recommended)
     */
    public Recipe(Name name) {
        requireAllNonNull(name);
        this.name = name;
    }

    public Name getName() {
        return name;
    }
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public RecipePortion getPortion() {
        portion.orElseThrow(RecipePortionNotPresentException::new);
        return portion.get();
    }

    public RecipeDuration getDuration() {
        duration.orElseThrow(RecipePortionNotPresentException::new);
        return duration.get();
    }

    // nullable variants of getPortion and getDuration
    // when we are okay with receiving null
    public RecipePortion getPortionNullable() {
        return portion.orElse(null);
    }

    public RecipeDuration getDurationNullable() {
        return duration.orElse(null);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setPortion(RecipePortion portion) {
        this.portion = Optional.ofNullable(portion);
    }

    public void setDuration(RecipeDuration duration) {
        this.duration = Optional.ofNullable(duration);
    }

    public void setTags(Tag... tags) {
        this.tags.addAll(Set.of(tags));
    }

    public void setIngredients(Ingredient... ingredients) {
        this.ingredients.addAll(List.of(ingredients));
    }

    public void setSteps(Step... steps) {
        this.steps.addAll(List.of(steps));
    }



    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameRecipe(Recipe otherRecipe) {
        if (otherRecipe == this) {
            return true;
        }

        return otherRecipe != null
                && otherRecipe.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Recipe)) {
            return false;
        }

        Recipe otherRecipe = (Recipe) other;
        return otherRecipe.getName().equals(getName())
                && otherRecipe.getPortion().equals(getPortion())
                && otherRecipe.getDuration().equals(getDuration())
                && otherRecipe.getTags().equals(getTags())
                && otherRecipe.getIngredients().equals(getIngredients())
                && otherRecipe.getSteps().equals(getSteps());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, portion, duration, tags, ingredients, steps);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        portion.ifPresent(p -> {
            builder.append("; Portion: ").append(p);
        });

        duration.ifPresent(d -> {
            builder.append("; Duration: ").append(d);
        });

        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        if (!ingredients.isEmpty()) {
            builder.append("; Ingredients: ");
            ingredients.forEach(i -> builder.append(i).append(", "));
        }

        if (!steps.isEmpty()) {
            builder.append("; Steps: ");
            for (int i = 1; i <= steps.size(); i++) {
                builder.append(String.format("%s. %s, ", i, steps.get(i)));
            }
        }
        return builder.toString();
    }
}
