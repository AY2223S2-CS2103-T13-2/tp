package seedu.recipe.ui.events;

import java.util.Map;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Represents an event that indicates a request to edit a recipe.
 * This event is used to communicate between the UI components when
 * a user wants to edit a recipe in the recipe list.
 */
public class EditRecipeEvent extends Event {
    /**
     * The event type for editing a recipe.
     */
    public static final EventType<EditRecipeEvent> EDIT_RECIPE_EVENT_TYPE = new EventType<>(Event.ANY, "EDIT_RECIPE");

    /**
     * The index of the recipe to be edited.
     */
    private final int recipeIndex;

    /**
     * A map of the changed values, where the keys are the field names and
     * the values are the updated field values.
     */
    private final Map<String, String> changedValues;

    /**
     * Constructs an EditRecipeEvent with the EDIT_RECIPE_EVENT_TYPE,
     * specified recipe index, and the map of changed values.
     *
     * @param recipeIndex The index of the recipe to be edited.
     * @param changedValues A map of the changed values, with field names as keys
     *                      and updated field values as values.
     */
    public EditRecipeEvent(int recipeIndex, Map<String, String> changedValues) {
        super(EDIT_RECIPE_EVENT_TYPE);
        this.recipeIndex = recipeIndex;
        this.changedValues = changedValues;
    }

    /**
     * Retrieves the index of the recipe to be edited.
     *
     * @return The index of the recipe to be edited.
     */
    public int getRecipeIndex() {
        return recipeIndex;
    }

    /**
     * Retrieves the map of changed values, with field names as keys
     * and updated field values as values.
     *
     * @return The map of changed values.
     */
    public Map<String, String> getChangedValues() {
        return changedValues;
    }
}
