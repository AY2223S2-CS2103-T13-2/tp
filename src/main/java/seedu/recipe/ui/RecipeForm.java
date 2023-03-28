package seedu.recipe.ui;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.ui.events.EditRecipeEvent;

/**
 * Represents the form element for users to edit {@code Recipe}s
 */
public class RecipeForm extends UiPart<Region> {
    private static final String FXML = "RecipeForm.fxml";

    @FXML
    private TextField nameField;

    @FXML
    private TextField durationField;

    @FXML
    private TextField portionField;

    @FXML
    private TextField ingredientsField;

    @FXML
    private TextField stepsField;

    @FXML
    private TextField tagsField;

    @FXML
    private FlowPane tags;

    @FXML
    private VBox ingredientsBox;

    @FXML
    private VBox stepsBox;

    @FXML
    private FlowPane ingredients;

    @FXML
    private FlowPane steps;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;
    private int displayedIndex;
    private Map<String, String> initialValues;
    private Recipe recipe;

    /**
     * Creates a new RecipeForm with the given recipe and displayed index.
     * If the recipe is not null, the form fields are pre-populated with the recipe's data.
     *
     * @param recipe         The recipe to edit or null for creating a new recipe.
     * @param displayedIndex The index of the recipe in the displayed list.
     */
    public RecipeForm(Recipe recipe, int displayedIndex) {
        super(FXML);
        this.recipe = recipe;
        this.displayedIndex = displayedIndex;
        if (recipe != null) {
            populateFields();
        }
        assert saveButton != null;
        saveButton.setOnAction(event -> saveRecipe());
        cancelButton.setOnAction(event -> closeForm());
    }

    /**
     * Stores the initial values of the form fields in a HashMap.
     * This is used for comparison when saving the recipe to determine
     * which fields have been changed.
     */
    private void storeInitialValues() {
        initialValues = new HashMap<>();
        initialValues.put("name", nameField.getText());
        initialValues.put("duration", durationField.getText());
        initialValues.put("portion", portionField.getText());
        initialValues.put("ingredients", ingredientsBox.getChildren().stream()
            .map(node -> ((TextField) node).getText())
            .collect(Collectors.joining(", ")));

        initialValues.put("steps", stepsBox.getChildren().stream()
            .map(node -> ((TextField) node).getText())
            .collect(Collectors.joining(", ")));

        initialValues.put("tags", tagsField.getText());
    }

    /**
     * Populates the form fields with the data from the existing recipe.
     * Stores all prepopulated data into a hashmap for comparison later when saving.
     */
    private void populateFields() {
        nameField.setText(recipe.getName().recipeName);
        //Duration
        durationField.setText(
                Optional.ofNullable(recipe.getDurationNullable())
                        .map(Object::toString)
                        .orElse("Duration was not added.")
        );
        //Portion
        portionField.setText(
                Optional.ofNullable(recipe.getPortionNullable())
                        .map(Object::toString)
                        .orElse("Portion was not added.")
        );
        /*
        //Ingredients
        ingredientsField.setText(recipe.getIngredients().stream()
                .map(Ingredient::toString)
                .collect(Collectors.joining(", ")));

        //Steps
        stepsField.setText(recipe.getSteps().stream()
                .map(Step::toString)
                .collect(Collectors.joining(", ")));
        */
        //Ingredients
        recipe.getIngredients().forEach((ingredient, information) -> {
            TextField ingredientField = new TextField(ingredient.toString());
            ingredientsBox.getChildren().add(ingredientField);
        });

        //Steps
        recipe.getSteps().forEach(step -> {
            TextField stepField = new TextField(step.toString());
            stepsBox.getChildren().add(stepField);
        });
        //Tags
        tagsField.setText(recipe.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .map(tag -> tag.tagName)
                .collect(Collectors.joining(", ")));

        storeInitialValues();
    }

    /**
     * Saves the changes made to the recipe and closes the form.
     * If any fields have been modified, the new values are stored
     * in a map of changed values.
     */
    private void saveRecipe() {
        // Check which fields have been changed
        Map<String, String> changedValues = new HashMap<>();
        for (Map.Entry<String, String> entry : initialValues.entrySet()) {
            String key = entry.getKey();
            String initialValue = entry.getValue();
            String currentValue = null;

            switch (key) {
            case "name":
                currentValue = nameField.getText();
                break;
            case "duration":
                currentValue = durationField.getText();
                break;
            case "portion":
                currentValue = portionField.getText();
                break;
            case "ingredients":
                currentValue = ingredientsBox.getChildren().stream()
                    .map(node -> ((TextField) node).getText())
                    .collect(Collectors.joining(", "));
                break;
            case "steps":
                currentValue = stepsBox.getChildren().stream()
                    .map(node -> ((TextField) node).getText())
                    .collect(Collectors.joining(", "));
                break;
            case "tags":
                currentValue = tagsField.getText();
                break;
            default:
                currentValue = ""; // or any default value you prefer
                break;
            }

            if (!initialValue.equals(currentValue)) {
                changedValues.put(key, currentValue);
            }
        }
        /*
        ...
        model.saveRecipe(recipe);
        EditRecipeEvent editEvent = new EditRecipeEvent(displayedIndex);
        cardPane.fireEvent(editEvent);
        */
        System.out.println(changedValues);
        EditRecipeEvent editEvent = new EditRecipeEvent(displayedIndex, changedValues);
        closeForm();
    }

    /**
     * Closes the form without saving any changes.
     */
    private void closeForm() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Displays the form in a new window.
     * The window's title will be "Add Recipe" if creating a new recipe,
     * or "Edit Recipe" if editing an existing recipe.
     */
    public void display() {
        Stage window = new Stage();
        // Ensures users do not exit the view by clicking outside
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(recipe == null ? "Add Recipe" : "Edit Recipe");
        window.setMinWidth(500);
        window.setMinHeight(700);
        VBox vbox = new VBox(getRoot());
        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                window.close();
            }
        });
        window.setScene(scene);
        window.showAndWait();
    }
}
