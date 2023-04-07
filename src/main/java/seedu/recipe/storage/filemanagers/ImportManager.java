package seedu.recipe.storage.filemanagers;

import static seedu.recipe.logic.commands.ImportCommand.EMPTY_COMMAND;
import static seedu.recipe.logic.commands.ImportCommand.INVALID_VALUES;
import static seedu.recipe.logic.commands.ImportCommand.NOT_JSON_FILE;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import seedu.recipe.commons.core.LogsCenter;
import seedu.recipe.commons.exceptions.DataConversionException;
import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.logic.parser.CliSyntax;
import seedu.recipe.model.ReadOnlyRecipeBook;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.storage.JsonRecipeBookStorage;

//@@author alson001
/**
 * API to import a RecipeBook from other directories
 */
public class ImportManager {
    //Model Prefixes
    private static final String NAME_PREFIX = CliSyntax.PREFIX_NAME.getPrefix();
    private static final String DURATION_PREFIX = CliSyntax.PREFIX_DURATION.getPrefix();
    private static final String PORTION_PREFIX = CliSyntax.PREFIX_PORTION.getPrefix();
    private static final String TAG_PREFIX = CliSyntax.PREFIX_TAG.getPrefix();
    private static final String INGREDIENT_PREFIX = CliSyntax.PREFIX_INGREDIENT.getPrefix();
    private static final String STEP_PREFIX = CliSyntax.PREFIX_STEP.getPrefix();

    private final Stage owner;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Constructs an instance of the ImportManager that is responsible for importing a JSON file to the current
     * Recipe Book.
     *
     * @param owner The UI stage to show the file chooser dialog.
     */
    public ImportManager(Stage owner) {
        this.owner = owner;
    }

    /**
     * Prompts the user to select a JSON file to import and returns an ObservableList of Recipe objects
     * parsed from the selected file.
     *
     * @return An ObservableList of Recipe parsed from the selected JSON file.
     * @throws IllegalValueException if there were any data constraints violated during the conversion.
     */
    public ObservableList<Recipe> execute() throws IllegalValueException {
        File importedFile = this.selectFile();
        if (importedFile == null) {
            return null;
        }
        return importRecipes(importedFile);
    }

    /**
     * Prompts the user to select a JSON file to import and returns the selected File object.
     *
     * @return The File object representing the selected JSON file.
     */
    public File selectFile() {
        FileChooser fileChooser = new FileChooser();

        // Set filter to only show JSON files
        ExtensionFilter filter = new ExtensionFilter("JSON Files", "*.json");
        fileChooser.getExtensionFilters().add(filter);

        // Set initial directory to the Downloads folder
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Downloads"));

        // Set dialog title
        fileChooser.setTitle("Import RecipeBook");

        // Show the file chooser dialog and get the result
        File selectedFile = fileChooser.showOpenDialog(owner);

        // User canceled the file chooser dialog
        if (selectedFile == null) {
            logger.warning(EMPTY_COMMAND);
            return null;
        }

        // Check if the file is a JSON file
        if (!selectedFile.getName().endsWith(".json")) {
            logger.warning(String.format(NOT_JSON_FILE, selectedFile.getName()));
            return null;
        }

        return selectedFile;
    }

    /**
     * Parses the Recipe objects from the specified JSON file and returns an ObservableList of Recipe objects.
     *
     * @param selectedFile The File object representing the JSON file to parse.
     * @return An ObservableList of Recipe objects parsed from the specified JSON file.
     * @throws IllegalValueException if the JSON data in the file cannot be converted into a RecipeBook object.
     */
    public ObservableList<Recipe> importRecipes(File selectedFile) throws IllegalValueException {
        Path filePath = selectedFile.toPath();
        logger.info("Selected file: " + filePath.toString());

        JsonRecipeBookStorage importedStorage = new JsonRecipeBookStorage(filePath);
        Optional<ReadOnlyRecipeBook> importedRecipeBook;
        try {
            importedRecipeBook = importedStorage.readRecipeBook();
        } catch (DataConversionException e) {
            throw new IllegalValueException(String.format(INVALID_VALUES, filePath));
        }
        return importedRecipeBook.get().getRecipeList();
    }

    /**
     * Returns a string representation of the given Recipe object in the format of a command string to add the recipe
     * to the RecipeBook.
     * The command string format is as follows:
     * n/RECIPE_NAME [d/DURATION] [p/PORTION] [t/TAG]... [i/INGREDIENT]... [s/STEPS]...
     *
     * @param recipe The Recipe object to convert to a command string.
     * @return A string representation of the Recipe object in the format of a command string to add the recipe to the
 *             RecipeBook.
     */
    public String getCommandText(Recipe recipe) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(" ").append(NAME_PREFIX);
        stringBuilder.append(recipe.getName().toString());

        if (recipe.getDurationNullable() != null) {
            stringBuilder.append(" ").append(DURATION_PREFIX);
            stringBuilder.append(recipe.getDuration().toString());
        }

        if (recipe.getPortionNullable() != null) {
            stringBuilder.append(" ").append(PORTION_PREFIX);
            stringBuilder.append(recipe.getPortion().toString());
        }

        //Tags
        recipe.getTags().forEach(tag ->
            stringBuilder.append(" ").append(TAG_PREFIX).append(tag.getTagName()));

        //Ingredients
        recipe.getIngredients().forEach((ingredient, information) ->
                stringBuilder.append(" ").append(INGREDIENT_PREFIX)
                        .append(new IngredientBuilder(ingredient, information)));
        //Steps
        recipe.getSteps().forEach(step ->
                stringBuilder.append(" ").append(STEP_PREFIX).append(step));

        return stringBuilder.toString();
    }
}
