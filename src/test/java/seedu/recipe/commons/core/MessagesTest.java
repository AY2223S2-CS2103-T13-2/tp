package seedu.recipe.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Container for test methods that validate that the Messages provided to the Application are valid.
 * If a developer has to modify the actual message, the test case here has to be fixed before those changes
 * are merged with the production code.
 */
public class MessagesTest {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_EMPTY_KEYWORDS_FIND = "Keywords for the find command cannot be empty!";
    public static final String MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX = "The recipe index provided is invalid";
    public static final String MESSAGE_RECIPES_LISTED_OVERVIEW = "%1$d recipes listed!";
    public static final String MESSAGE_NO_STORED_SUBS = "No substitutions are available. Check back later or "
            + "update the RecipeBook with some suggested substitutions!";
    public static final String MESSAGE_DISPLAY_STORED_SUBS = "Here's a list of potential substitutes for the "
            + "ingredient %s: \n%s";

    @Test
    public void unknownCommandMessage_get_matchesDefinedMessage() {
        assertEquals(MESSAGE_UNKNOWN_COMMAND, Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void invalidCommandMessage_get_matchesDefinedMessage() {
        String testMessage = "Aa{bC}/:;\\|`'";
        assertEquals(MESSAGE_INVALID_COMMAND_FORMAT, Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT, testMessage),
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, testMessage));
    }

    @Test
    public void emptyKeywordsFindMessage_get_matchesDefinedMessage() {
        assertEquals(MESSAGE_EMPTY_KEYWORDS_FIND, Messages.MESSAGE_EMPTY_KEYWORDS_FIND);
    }

    @Test
    public void invalidRecipeIndexMessage_get_matchesDefinedMessage() {
        assertEquals(MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void recipesListedOverviewMessage_get_matchesDefinedMessage() {
        Integer testNumber = 22;
        assertEquals(MESSAGE_RECIPES_LISTED_OVERVIEW, Messages.MESSAGE_RECIPES_LISTED_OVERVIEW);
        assertEquals(String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, testNumber),
                String.format(Messages.MESSAGE_RECIPES_LISTED_OVERVIEW, testNumber));
    }

    @Test
    public void noStoredSubsMessage_get_matchesDefinedMessage() {
        assertEquals(MESSAGE_NO_STORED_SUBS, Messages.MESSAGE_NO_STORED_SUBS);
    }

    @Test
    public void displayStoredSubsMessage_get_matchesDefinedMessage() {
        String firstIngredient = "smoked halibut";
        String subIngredient = "smoked salmon";
        assertEquals(MESSAGE_DISPLAY_STORED_SUBS, Messages.MESSAGE_DISPLAY_STORED_SUBS);
        assertEquals(String.format(MESSAGE_DISPLAY_STORED_SUBS, firstIngredient, subIngredient),
            String.format(Messages.MESSAGE_DISPLAY_STORED_SUBS, firstIngredient, subIngredient));
    }
}
