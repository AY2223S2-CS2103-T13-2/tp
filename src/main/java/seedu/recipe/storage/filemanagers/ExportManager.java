package seedu.recipe.storage.filemanagers;

import static seedu.recipe.commons.util.AppUtil.checkArgument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seedu.recipe.logic.Logic;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.model.ReadOnlyRecipeBook;
import seedu.recipe.storage.JsonRecipeBookStorage;

//@@author alson001
/**
 * API to export current RecipeBook
 */
public class ExportManager {
    public static final String NULL_LOGIC = "Please provide a non-null Logic instance";
    public static final String NO_SELECTION = "Export operation cancelled.";

    private final Path recipeBookFilePath;
    private final Stage owner;
    private final Logic logic;

    /**
     * Creates an instance of the ExportManager that is responsible for exporting the RecipeBook to a JSON file.
     *
     * @param owner the owner stage of MainWindow
     * @param logic The Logic that helps to derive the current Recipe Book path for the export operation.
     */
    public ExportManager(Stage owner, Logic logic) {
        checkArgument(logic != null, NULL_LOGIC);
        this.owner = owner;
        this.logic = logic;
        this.recipeBookFilePath = logic.getRecipeBookFilePath();
    }

    /**
     * Displays a file chooser dialog and exports the RecipeBook to a JSON file.
     * If file exists, there will be a prompt asking whether to overwrite it.
     *
     * @throws IOException if there is an error while writing to the file.
     */
    public void execute() throws IOException, CommandException {
        FileChooser fileChooser = createFile();
        File selectedFile = fileChooser.showSaveDialog(this.owner);
        if (selectedFile != null) {
            writeToFile(selectedFile);
        } else {
            throw new CommandException(NO_SELECTION);
        }
    }

    /**
     * Creates a file chooser dialog with a filter for JSON files.
     *
     * @return the file chooser dialog.
     */
    private FileChooser createFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export RecipeBook");
        fileChooser.setInitialFileName("recipebook.json");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
        return fileChooser;
    }

    /**
     * Writes the contents of the RecipeBook JSON file to the selected file. If the RecipeBook file path does not exist,
     * a different method is used to write the file by using the Logic instance
     *
     * @param selectedFile the selected file to write to.
     * @throws IOException if there is an error while writing to the file.
     */
    protected void writeToFile(File selectedFile) throws IOException {
        if (!recipeBookFilePath.toFile().exists()) {
            writeToFileUsingLogic(selectedFile);
            return;
        }
        FileReader fr = new FileReader(recipeBookFilePath.toFile());
        FileWriter fw = new FileWriter(selectedFile);
        try (BufferedReader reader = new BufferedReader(fr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                fw.write(line);
                fw.write(System.lineSeparator()); // add line separator
            }
        }
        fr.close();
        fw.close();
    }

    /**
     * Writes the contents of the RecipeBook using the Logic instance of RecipeBook. The contents of the RecipeBook are
     * retrieved using the Logic instance and saved to the selected file as a JSON file.
     *
     * @param selectedFile the selected file to write to.
     * @throws IOException if there is an error while writing to the file.
     */
    public void writeToFileUsingLogic(File selectedFile) throws IOException {
        ReadOnlyRecipeBook recipeBook = logic.getRecipeBook();
        JsonRecipeBookStorage jsonRecipeBookStorage = new JsonRecipeBookStorage(selectedFile.toPath());
        jsonRecipeBookStorage.saveRecipeBook(recipeBook);
    }

}
