package seedu.recipe.logic.commands;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.storage.filemanagers.ImportManager;

/**
 * Represents the command used to import RecipeBook JSON file from another file location into this RecipeBook app.
 * This is deliberately triggered and created by a UI interaction using a FileWindow, as the FileWindow guarantees
 * the filepath passed to the app by the user is not malformed.
 */
public class ImportCommand extends Command {
    public static final String EMPTY_COMMAND = "No file was selected.";
    public static final String NOT_JSON_FILE = "Selected file '%s' is not a JSON file.";
    public static final String INVALID_VALUES = "Selected JSON file '%s' contains invalid values";
    public static final String DUPLICATE_VALUES =
            "The selected JSON file contains some duplicate recipes which have been ignored and imported successfully";
    public static final String SUCCESS_MESSAGE = "Selected JSON file imported successfully";

    private final ImportManager importManager;
    private boolean hasDuplicate;

    /**
     * Constructs an instance of this Command around the given Import Manager.
     * @param stage The UI stage that triggered this command.
     */
    public ImportCommand(Stage stage) {
        this.importManager = new ImportManager(stage);
        this.hasDuplicate = false;
    }

    public boolean isDuplicate() {
        return hasDuplicate;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            ObservableList<Recipe> importedRecipeList = importManager.execute();
            if (importedRecipeList == null) {
                throw new CommandException(EMPTY_COMMAND);
            }
            // Validate uniqueness, add to model
            ObservableList<Recipe> currentRecipes = model.getFilteredRecipeList();
            for (Recipe recipe : importedRecipeList) {
                if (currentRecipes.stream().noneMatch(recipe::isSameRecipe)) {
                    model.addRecipe(recipe);
                } else {
                    hasDuplicate = true;
                }
            }
        } catch (IllegalValueException e) {
            throw new CommandException(e.getMessage());
        }
        if (isDuplicate()) {
            return new CommandResult(DUPLICATE_VALUES);
        }
        return new CommandResult(SUCCESS_MESSAGE);
    }
}
