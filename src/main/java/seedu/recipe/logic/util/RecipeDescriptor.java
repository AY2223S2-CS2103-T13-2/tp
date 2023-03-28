package seedu.recipe.logic.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.recipe.commons.util.CollectionUtil;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.RecipeDuration;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.tag.Tag;

/**
 * Stores the details to edit the recipe with. Each non-empty field value will replace the
 * corresponding field value of the recipe.
 */
public class RecipeDescriptor {
    private Name name;
    private RecipeDuration duration;
    private RecipePortion portion;
    private Set<Tag> tags;
    private HashMap<Ingredient, IngredientInformation> ingredients;
    private List<Step> steps;

    public RecipeDescriptor() {
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public RecipeDescriptor(RecipeDescriptor toCopy) {
        setName(toCopy.name);
        setDuration(toCopy.duration);
        setPortion(toCopy.portion);
        setTags(toCopy.tags);
        setIngredients(toCopy.ingredients);
        setSteps(toCopy.steps);
    }

    /**
     * Creates and returns a {@code Recipe} with the details of {@code recipeToEdit}
     * edited with {@code editRecipeDescriptor}.
     */
    public Recipe toRecipe(Recipe recipeToEdit) {
        assert recipeToEdit != null;

        Name updatedName = getName().orElse(recipeToEdit.getName());
        Recipe newRecipe = new Recipe(updatedName);

        RecipeDuration updatedDuration = getDuration().orElse(recipeToEdit.getDurationNullable());
        newRecipe.setDuration(updatedDuration);

        RecipePortion updatedPortion = getPortion().orElse(recipeToEdit.getPortionNullable());
        newRecipe.setPortion(updatedPortion);

        Tag[] updatedTags = getTags().orElse(recipeToEdit.getTags()).toArray(Tag[]::new);
        newRecipe.setTags(updatedTags);

        HashMap<Ingredient, IngredientInformation> updatedIngredients = getIngredients()
                .map(HashMap::new)
                .orElse(recipeToEdit.getIngredients());
        newRecipe.setIngredients(updatedIngredients);

        Step[] updatedSteps = getSteps().orElse(recipeToEdit.getSteps()).toArray(Step[]::new);
        newRecipe.setSteps(updatedSteps);

        return newRecipe;
    }

    /**
     * Generates a Recipe from this RecipeDescriptor.
     */
    public Recipe toRecipe() {
        Recipe blank = new Recipe(this.name);
        return this.toRecipe(blank);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, duration, portion, tags, ingredients, steps);
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<RecipeDuration> getDuration() {
        return Optional.ofNullable(duration);
    }

    public void setDuration(RecipeDuration duration) {
        this.duration = duration;
    }

    public Optional<RecipePortion> getPortion() {
        return Optional.ofNullable(portion);
    }

    public void setPortion(RecipePortion portion) {
        this.portion = portion;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    public Optional<Map<Ingredient, IngredientInformation>> getIngredients() {
        return (ingredients != null) ? Optional.of(Collections.unmodifiableMap(ingredients)) : Optional.empty();
    }

    public void setIngredients(HashMap<Ingredient, IngredientInformation> ingredientTable) {
        this.ingredients = ingredientTable;
    }

    public void setIngredients(List<IngredientBuilder> ingredients) {
        HashMap<Ingredient, IngredientInformation> ingredientTable = new HashMap<>();
        ingredients.forEach(ingredient -> ingredientTable.putAll(ingredient.build()));
        this.ingredients = ingredientTable;
    }

    public Optional<List<Step>> getSteps() {
        return (steps != null) ? Optional.of(Collections.unmodifiableList(steps)) : Optional.empty();
    }

    public void setSteps(List<Step> steps) {
        this.steps = (steps != null) ? new ArrayList<>(steps) : null;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeDescriptor)) {
            return false;
        }

        // state check
        RecipeDescriptor e = (RecipeDescriptor) other;

        return getName().equals(e.getName())
                && getDuration().equals(e.getDuration())
                && getPortion().equals(e.getPortion())
                && getTags().equals(e.getTags())
                && getIngredients().equals(e.getIngredients())
                && getSteps().equals(e.getSteps());
    }
}
