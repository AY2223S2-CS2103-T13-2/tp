package seedu.recipe.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import seedu.recipe.commons.exceptions.DataConversionException;
import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.ReadOnlyRecipeBook;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.tag.Tag;

/**
 * API to import a RecipeBook from other directories
 */
public class ImportManager {

    private final Path recipeBookFilePath = Paths.get("data", "recipebook.json");
    private final Stage owner;

    public ImportManager(Stage owner) {
        this.owner = owner;
    }

    /**
     * Prompts the user to select a JSON file to import and returns an ObservableList of Recipe objects
     * parsed from the selected file.
     *
     * @return An ObservableList of Recipe parsed from the selected JSON file.
     * @throws IOException if an I/O error occurs.
     * @throws DataConversionException if the JSON data in the file cannot be converted into a RecipeBook object.
     * @throws IllegalValueException if there were any data constraints violated during the conversion.
     */
    public ObservableList<Recipe> execute() throws IOException, DataConversionException, IllegalValueException {
        File importedFile = this.selectFile();
        if (importedFile == null) {
            return null;
        }
        ObservableList<Recipe> importedRecipes = importRecipes(importedFile);
        return importedRecipes;
    }

    //Dont remove might be required for a fix
//    public ObservableList<Recipe> uniqueImportedRecipes(ObservableList<Recipe> importedRecipes) throws
//            DataConversionException {
//        JsonRecipeBookStorage currentStorage = new JsonRecipeBookStorage((recipeBookFilePath));
//        Optional<ReadOnlyRecipeBook> currentRecipeBook;
//        try {
//            currentRecipeBook = currentStorage.readRecipeBook();
//        } catch (DataConversionException e) {
//            throw e;
//        }
//        ObservableList<Recipe> currentRecipes = currentRecipeBook.get().getRecipeList();
//        for (Recipe importedRecipe : importedRecipes) {
//            if (currentRecipes.stream().anyMatch())
//        }
//
//    }

    /**
     * Prompts the user to select a JSON file to import and returns the selected File object.
     *
     * @return The File object representing the selected JSON file.
     * @throws IOException if an I/O error occurs.
     */
    public File selectFile() throws IOException {
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
            System.out.println("No file selected.");
            return null;
        }

        // Check if the file is a JSON file
        if (!selectedFile.getName().endsWith(".json")) {
            System.out.println("Selected file is not a JSON file.");
            return null;
        }

        return selectedFile;
    }

    /**
     * Parses the Recipe objects from the specified JSON file and returns an ObservableList of Recipe objects.
     *
     * @param selectedFile The File object representing the JSON file to parse.
     * @return An ObservableList of Recipe objects parsed from the specified JSON file.
     * @throws DataConversionException if the JSON data in the file cannot be converted into a RecipeBook object.
     */
    public ObservableList<Recipe> importRecipes(File selectedFile) throws DataConversionException {
        Path filePath = selectedFile.toPath();
        System.out.println("Selected file: " + filePath.toString());

        JsonRecipeBookStorage importedStorage = new JsonRecipeBookStorage((filePath));
        Optional<ReadOnlyRecipeBook> importedRecipeBook;
        try {
            importedRecipeBook = importedStorage.readRecipeBook();
        } catch (DataConversionException e) {
            throw e;
        }
        return importedRecipeBook.get().getRecipeList();
    }

    public String getCommandText(Recipe recipe) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(" n/");
        stringBuilder.append(recipe.getName().toString());

        if (recipe.getDurationNullable() != null) {
            stringBuilder.append(" d/");
            stringBuilder.append(recipe.getDuration().toString());
        }

        if (recipe.getPortionNullable() != null) {
            stringBuilder.append(" p/");
            stringBuilder.append(recipe.getPortion().toString());
        }

        if (!recipe.getTags().isEmpty()) {
            Set<Tag> tags = recipe.getTags();
            for (Tag tag : tags) {
                stringBuilder.append(" t/");
                stringBuilder.append(tag.getTagName());
            }
        }

        if (!recipe.getIngredients().isEmpty()) {
            HashMap<Ingredient, IngredientInformation> ingredientTable = recipe.getIngredients();
            ingredientTable.forEach(
                    (ingredient, ingredientInfomation) -> {
                        stringBuilder.append(" i/");
                        stringBuilder.append(" -n " + ingredient.getName());
                        if (!ingredient.getCommonName().isEmpty()) {
                            stringBuilder.append(" -cn " + ingredient.getCommonName());
                        }
                        if (ingredientInfomation.getQuantity().isPresent()) {
                            stringBuilder.append(" -a " + ingredientInfomation.getQuantity().get().toString());
                        }
                        if (ingredientInfomation.getEstimatedQuantity().isPresent()) {
                            stringBuilder.append(" -e " + ingredientInfomation.getEstimatedQuantity().get().toString());
                        }
                        if (!ingredientInfomation.getRemarks().isEmpty()) {
                            List<String> remarks = ingredientInfomation.getRemarks();
                            for (String remark : remarks) {
                                stringBuilder.append(" -r " + remark);
                            }
                        }
                        if (!ingredientInfomation.getSubstitutions().isEmpty()) {
                            List<Ingredient> substitutions = ingredientInfomation.getSubstitutions();
                            for (Ingredient substitution : substitutions) {
                                stringBuilder.append(" -s " + substitution.getName());
                            }
                        }
                    });
        }

        if (!recipe.getSteps().isEmpty()) {
            List<Step> steps = recipe.getSteps();
            for (Step step : steps) {
                stringBuilder.append(" s/");
                stringBuilder.append(step.toString());
            }
        }
        return stringBuilder.toString();
    }
}
