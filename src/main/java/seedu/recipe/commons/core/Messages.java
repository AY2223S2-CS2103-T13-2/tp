package seedu.recipe.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_EMPTY_KEYWORDS_FIND = "Keywords for the find command cannot be empty!";
    public static final String MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX = "The recipe index provided is invalid";
    public static final String MESSAGE_RECIPES_LISTED_OVERVIEW = "%1$d recipes listed!";
    public static final String MESSAGE_NO_STORED_SUBS = "No substitutions are available. Check back later or "
            + "update the RecipeBook with some suggested substitutions!";
    public static final String MESSAGE_DISPLAY_STORED_SUBS = "Here's a list of potential substitutes for the "
            + "ingredient %s: \n%s";
}
