package seedu.recipe.testutil;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.recipe.logic.util.RecipeDescriptor;
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
 * A utility class to help with building EditRecipeDescriptor objects.
 */
public class EditRecipeDescriptorBuilder {

    private RecipeDescriptor descriptor;

    public EditRecipeDescriptorBuilder() {
        descriptor = new RecipeDescriptor();
    }

    public EditRecipeDescriptorBuilder(RecipeDescriptor descriptor) {
        this.descriptor = new RecipeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRecipeDescriptor} with fields containing {@code recipe}'s details
     */
    public EditRecipeDescriptorBuilder(Recipe recipe) {
        descriptor = new RecipeDescriptor();
        descriptor.setName(recipe.getName());
        descriptor.setPortion(recipe.getPortion());
        descriptor.setDuration(recipe.getDuration());
        descriptor.setTags(recipe.getTags());
        descriptor.setIngredients(recipe.getIngredients());
        descriptor.setSteps(recipe.getSteps());
    }

    /**
     * Sets the {@code Name} of the {@code EditRecipeDescriptor} that we are building.
     */
    public EditRecipeDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Portion} of the {@code EditRecipeDescriptor} that we are building.
     */
    public EditRecipeDescriptorBuilder withPortion(String portion) {
        descriptor.setPortion(RecipePortion.of(portion));
        return this;
    }

    /**
     * Sets the {@code RecipeDuration} of the {@code EditRecipeDescriptor} that we are building.
     */
    public EditRecipeDescriptorBuilder withDuration(String duration) {
        descriptor.setDuration(RecipeDuration.of(duration));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditRecipeDescriptor}
     * that we are building.
     */
    public EditRecipeDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Ingredients} of the {@code EditRecipeDescriptor} that we are building.
     */
    public EditRecipeDescriptorBuilder withIngredients(String... ingredients) {
        HashMap<Ingredient, IngredientInformation> ingredientTable = new HashMap<>();
        Stream.of(ingredients)
                .map(IngredientBuilder::new)
                .map(IngredientBuilder::build)
                .forEach(ingredientTable::putAll);
        descriptor.setIngredients(ingredientTable);
        return this;
    }

    /**
     * Sets the {@code Steps} of the {@code EditRecipeDescriptor} that we are building.
     */
    public EditRecipeDescriptorBuilder withSteps(String... steps) {
        List<Step> stepList = Stream.of(steps).map(Step::new).collect(Collectors.toList());
        descriptor.setSteps(stepList);
        return this;
    }


    public RecipeDescriptor build() {
        return descriptor;
    }
}
