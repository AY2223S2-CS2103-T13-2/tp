package seedu.recipe.model;

import javafx.collections.ObservableList;
import seedu.recipe.model.recipe.Recipe;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyRecipeBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Recipe> getRecipeList();

}
