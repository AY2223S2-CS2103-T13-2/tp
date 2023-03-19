package seedu.recipe.ui;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Recipe;

/**
 * An UI component that displays information of a {@code Recipe}.
 */
public class RecipeCard extends UiPart<Region> {

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
     * Creates a {@code RecipeCode} with the given {@code Recipe} and index to display.
     */
    public RecipeCard(Recipe recipe, int displayedIndex) {
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
        
        // Add a click listener to the cardPane node
        cardPane.setOnMouseClicked(event -> {
            RecipePopup popup = new RecipePopup(recipe, displayedIndex);
            popup.display();
        });
        
        /* 
        cardPane.setOnKeyPressed(event -> {
            System.out.println(event);
            if (event.getCode() == KeyCode.DELETE) {
                // Perform delete operation here
                RecipePopup popup = new RecipePopup(recipe, displayedIndex);
                popup.display();            
            }
        });
        */
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeCard)) {
            return false;
        }

        // state checkbyd
        RecipeCard card = (RecipeCard) other;
        return id.getText().equals(card.id.getText())
                && recipe.equals(card.recipe);
    }
}   

