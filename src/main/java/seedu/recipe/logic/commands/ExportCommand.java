package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;

import javafx.stage.Stage;
import seedu.recipe.logic.Logic;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.model.Model;
import seedu.recipe.storage.filemanagers.ExportManager;

/**
 * Represents the command used to export RecipeBook JSON file from this RecipeBook app to a specified file location.
 * This is deliberately triggered and created by a UI interaction using a FileWindow, as the FileWindow guarantees
 * the destination filepath passed to the app by the user is not malformed.
 */
public class ExportCommand extends Command {
    public static final String ERROR_MESSAGE = "Error occurred while exporting Recipe Book: ";
    public static final String SUCCESS_MESSAGE = "Recipe Book JSON file exported successfully";

    private final ExportManager exportManager;

    /**
     * Constructs an instance of this Command around the given Export Manager.
     * @param stage The UI stage that triggered this command.
     * @param logic The Logic that helps to derive the current Recipe Book path for Export Manager to use.
     */
    public ExportCommand(Stage stage, Logic logic) {
        requireNonNull(logic);
        exportManager = new ExportManager(stage, logic);
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
            exportManager.execute();
        } catch (IOException e) {
            throw new CommandException(ERROR_MESSAGE + e.getMessage());
        }
        return new CommandResult(SUCCESS_MESSAGE);
    }
}
