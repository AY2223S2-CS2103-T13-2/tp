package seedu.recipe.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Represents an event that indicates a request to delete a recipe.
 * This event is used to communicate between the UI components when
 * a user wants to delete a recipe from the recipe list.
 */
public class DeleteRecipeEvent extends Event {
    /**
     * The event type for deleting a recipe.
     */
    public static final EventType<DeleteRecipeEvent> DELETE_RECIPE_EVENT_TYPE =
            new EventType<>(Event.ANY, "DELETE_RECIPE");

    private final int recipeIndex;

    /**
     * Constructs a DeleteRecipeEvent with the DELETE_RECIPE_EVENT_TYPE
     * and the specified recipe index.
     *
     * @param recipeIndex The index of the recipe to be deleted.
     */
    public DeleteRecipeEvent(int recipeIndex) {
        super(DELETE_RECIPE_EVENT_TYPE);
        this.recipeIndex = recipeIndex;
    }

    /**
     * Retrieves the index of the recipe to be deleted.
     *
     * @return The index of the recipe to be deleted.
     */
    public int getRecipeIndex() {
        return recipeIndex;
    }
}
