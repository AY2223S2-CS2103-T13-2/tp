package seedu.recipe.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Represents an event that indicates a request to add a new recipe.
 * This event is used to communicate between the UI components when
 * a user wants to add a new recipe to the recipe list.
 */
public class AddRecipeEvent extends Event {
    /**
     * The event type for adding a new recipe.
     */
    public static final EventType<AddRecipeEvent> ADD_RECIPE_EVENT_TYPE = new EventType<>(Event.ANY, "ADD_RECIPE");

    /**
     * Constructs an AddRecipeEvent with the ADD_RECIPE_EVENT_TYPE.
     */
    public AddRecipeEvent() {
        super(ADD_RECIPE_EVENT_TYPE);
    }
}
