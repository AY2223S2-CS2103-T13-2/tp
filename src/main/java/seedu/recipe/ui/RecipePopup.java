package seedu.recipe.ui;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.util.IngredientUtil;


/**
 * Represents the UI component that pops up and displays the detailed view of a Recipe.
 */
public class RecipePopup extends UiPart<Region> {
    private static final String FXML = "RecipePopup.fxml";

    public final Recipe recipe;

    //Root Layout Component
    @FXML
    private HBox cardPane;

    //Child Data fields
    @FXML
    private Label name;
    @FXML
    private Label id;

    @FXML
    private Label duration;

    @FXML
    private Label portion;

    @FXML
    private VBox ingredients;

    @FXML
    private VBox steps;

    @FXML
    private HBox tags;

    @FXML
    private HBox emptyTags;

    /**
     * Generates and returns the UI instance for this Recipe card.
     *
     * @param recipe         The recipe details to display in the card.
     * @param displayedIndex The index number to display in the card.
     */
    public RecipePopup(Recipe recipe, int displayedIndex) {
        super(FXML);
        this.recipe = recipe;
        id.setText(displayedIndex + ". ");
        name.setText(recipe.getName().recipeName);

        //Duration
        duration.setText(
                Optional.ofNullable(recipe.getDurationNullable())
                        .map(Object::toString)
                        .orElse("Duration was not added."));

        //Portion
        portion.setText(
                Optional.ofNullable(recipe.getPortionNullable())
                        .map(Object::toString)
                        .orElse("Portion was not added."));

        // Ingredients
        createIngredientList(recipe);

        // Steps
        createStepList(recipe);

        //Tags
        recipe.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        if (recipe.getTags().size() == 0) {
            emptyTags.getChildren().add(new Label("No Tags were added. Add some!"));
        }

    }

    private void createIngredientList(Recipe recipe) {
        HashMap<Ingredient, IngredientInformation> ingredientTable = recipe.getIngredients();
        if (ingredientTable.size() == 0) {
            ingredients.getChildren().add(new Label("No Ingredients were added. Add some!"));
            return;
        }
        ingredientTable.forEach((ingredient, information) -> {
            String ingredientLabelText = IngredientUtil.ingredientKeyValuePairToString(ingredient, information);
            Label ingredientLabel = new Label("• " + ingredientLabelText);
            ingredientLabel.setWrapText(true);
            ingredients.getChildren().add(ingredientLabel);
        });
    }

    private void createStepList(Recipe recipe) {
        List<Step> stepList = recipe.getSteps();
        if (stepList.size() == 0) {
            steps.getChildren().add(new Label("No steps were added. Add some!"));
            return;
        }
        for (int i = 0; i < stepList.size(); i++) {
            Label stepLabel = new Label((
                i + 1) + ". " + stepList.get(i).toString()
            );
            stepLabel.setWrapText(true);
            steps.getChildren().add(stepLabel);
        }
    }

    /**
     * Displays the UI as a new window for the JavaFX Scene.
     */
    public void display() {
        Stage window = new Stage();
        // Ensures users do not exit the view by clicking outside
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Recipe Details");
        VBox vbox = new VBox(getRoot());
        vbox.setStyle("-fx-background-color: #3f3f46");
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
