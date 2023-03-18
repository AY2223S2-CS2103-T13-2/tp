package seedu.recipe.ui;
import java.util.Comparator;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.recipe.model.recipe.Recipe;

/**
 * Represents the UI component that pops up and displays the detailed view of a Recipe.
 */
public class RecipePopup extends UiPart<Region> {
    private static final String FXML = "RecipeListCard.fxml";

    public final Recipe recipe;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;

    @FXML
    private Label duration;

    @FXML
    private Label portion;

    @FXML
    private FlowPane tags;

    @FXML
    private FlowPane ingredients;

    @FXML
    private FlowPane steps;

    /**
     * Generates and returns the UI instance for this Recipe card.
     * @param recipe The recipe details to display in the card.
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
                        .orElse("Duration was not added.")
        );

        //Portion
        portion.setText(
                Optional.ofNullable(recipe.getPortionNullable())
                        .map(Object::toString)
                        .orElse("Portion was not added.")
        );


        //Tags
        recipe.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        //Ingredients
        recipe.getIngredients()
                .forEach(ingredient -> ingredients.getChildren().add(new Label(ingredient.toString())));

        //Steps
        recipe.getSteps()
                .forEach(step -> steps.getChildren().add(new Label(step.toString())));
    }

    /**
     * Displays the UI as a new window for the JavaFX Scene.
     */
    public void display() {
        Stage window = new Stage();
        // Ensures users do not exit the view by clicking outside
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Recipe Details");
        window.setMinWidth(500);
        window.setMinHeight(300);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> window.close());
        // Create a VBox to hold the RecipeCard
        VBox vbox = new VBox(getRoot(), closeButton);
        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }
}
