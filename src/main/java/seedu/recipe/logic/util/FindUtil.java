package seedu.recipe.logic.util;

import java.util.Collection;
import java.util.function.Function;

import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.tag.Tag;

/**
 * Implements various utility functions relating to the "Find" command.
 */
public class FindUtil {
    // Find-by-name utils
    public static final Function<Recipe, Name> GET_NAME_FROM_RECIPE = Recipe::getName;
    public static final Function<Name, String> GET_NAME_STRING = name -> name.recipeName;

    // Find-by-tag utils
    public static final Function<Recipe, Collection<Tag>> GET_TAGS_FROM_RECIPE = Recipe::getTags;
    public static final Function<Tag, String> GET_TAG_STRING = tag -> tag.tagName;

    // Find-by-ingredient utils
    public static final Function<Recipe, Collection<Ingredient>> GET_INGREDIENTS_FROM_RECIPE =
        Recipe::getIngredientList;
    public static final Function<Ingredient, String> GET_INGREDIENT_STRING = Ingredient::getNames;
}
