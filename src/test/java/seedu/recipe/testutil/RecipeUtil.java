package seedu.recipe.testutil;

import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_PORTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.recipe.logic.commands.AddCommand;
import seedu.recipe.logic.util.RecipeDescriptor;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;

/**
 * A utility class for Recipe.
 */
public class RecipeUtil {
    /**
     * Returns an add command string for adding the {@code recipe}.
     */
    public static String getAddCommand(Recipe recipe) {
        return AddCommand.COMMAND_WORD + " " + getRecipeDetails(recipe);
    }

    /**
     * Returns the part of command string for the given {@code recipe}'s details.
     */
    public static String getRecipeDetails(Recipe recipe) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME).append(recipe.getName().recipeName).append(" ");

        if (recipe.getDurationNullable() != null) {
            sb.append(PREFIX_DURATION).append(recipe.getDuration()).append(" ");
        }

        if (recipe.getPortionNullable() != null) {
            sb.append(PREFIX_PORTION).append(recipe.getPortion()).append(" ");
        }

        recipe.getTags()
            .forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));

        recipe.getIngredients().forEach(((ingredient, information) ->
            sb.append(PREFIX_INGREDIENT)
                .append(new IngredientBuilder(ingredient, information))
                .append(" "))
        );

        recipe.getSteps().forEach(step -> sb.append(PREFIX_STEP).append(step).append(" "));

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditRecipeDescriptor}'s details.
     */
    public static String getEditRecipeDescriptorDetails(RecipeDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.recipeName).append(" "));
        descriptor.getDuration().ifPresent(duration -> sb.append(PREFIX_DURATION).append(duration).append(" "));
        descriptor.getPortion().ifPresent(portion -> sb.append(PREFIX_PORTION).append(portion).append(" "));
        descriptor.getTags().ifPresent(tags ->
            tags.forEach(tag -> sb.append(PREFIX_TAG).append(tag).append(" "))
        );
        descriptor.getIngredients().ifPresent(ingredientMap ->
            ingredientMap.forEach((ingredient, information) ->
                sb.append(PREFIX_INGREDIENT)
                    .append(new IngredientBuilder(ingredient, information))
                    .append(" ")
            )
        );
        descriptor.getSteps().ifPresent(steps ->
            steps.forEach(step -> sb.append(PREFIX_STEP).append(step).append(" "))
        );
        return sb.toString();
    }
}
