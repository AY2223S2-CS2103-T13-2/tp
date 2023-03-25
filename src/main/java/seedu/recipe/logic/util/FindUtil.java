package seedu.recipe.logic.util;

import java.util.function.Function;

import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.Recipe;

/**
 * Implements various utility functions relating to the "Find" command.
 */
public class FindUtil {
    // Find-by-name utils
    public static final Function<Recipe, Name> GET_NAME_FROM_RECIPE = Recipe::getName;
    public static final Function<Name, String> GET_NAME_STRING = name -> name.recipeName;
}
