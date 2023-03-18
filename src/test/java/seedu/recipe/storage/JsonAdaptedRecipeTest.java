package seedu.recipe.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.storage.JsonAdaptedRecipe.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_DURATION;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_INGREDIENTS;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_NAME;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_PORTION;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_STEPS;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_TAGS;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.RecipeDuration;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.tag.Tag;

public class JsonAdaptedRecipeTest {
    private static final String INVALID_NAME = "7896";
    private static final JsonAdaptedRecipePortion INVALID_PORTION = new JsonAdaptedRecipePortion(
            1, 0, new JsonAdaptedPortionUnit("unitName")
    );
    private static final JsonAdaptedRecipeDuration INVALID_DURATION = new JsonAdaptedRecipeDuration(
            -1, new JsonAdaptedTimeUnit("minutes")
    );
    private static final String INVALID_TAG = "#lit";
    private static final String INVALID_INGREDIENT = "-1oz. juice";

    @Test
    public void toModelType_validRecipeDetails_returnsRecipe() throws Exception {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(CACIO_E_PEPE);
        Recipe r = recipe.toModelType();
        assertTrue(CACIO_E_PEPE.isSameRecipe(r));
        assertEquals(CACIO_E_PEPE, r);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(
                new JsonAdaptedName(INVALID_NAME),
                Optional.of(CACIO_PORTION).map(JsonAdaptedRecipePortion::new),
                Optional.of(CACIO_DURATION).map(JsonAdaptedRecipeDuration::new),
                CACIO_TAGS.stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                CACIO_INGREDIENTS.stream().map(JsonAdaptedIngredient::new).collect(Collectors.toList()),
                CACIO_STEPS.stream().map(JsonAdaptedStep::new).collect(Collectors.toList())
        );
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(
                null,
                Optional.of(CACIO_PORTION).map(JsonAdaptedRecipePortion::new),
                Optional.of(CACIO_DURATION).map(JsonAdaptedRecipeDuration::new),
                CACIO_TAGS.stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                CACIO_INGREDIENTS.stream().map(JsonAdaptedIngredient::new).collect(Collectors.toList()),
                CACIO_STEPS.stream().map(JsonAdaptedStep::new).collect(Collectors.toList())
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidPortion_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(
                new JsonAdaptedName(CACIO_NAME),
                Optional.of(INVALID_PORTION),
                Optional.of(CACIO_DURATION).map(JsonAdaptedRecipeDuration::new),
                CACIO_TAGS.stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                CACIO_INGREDIENTS.stream().map(JsonAdaptedIngredient::new).collect(Collectors.toList()),
                CACIO_STEPS.stream().map(JsonAdaptedStep::new).collect(Collectors.toList())
        );
        String expectedMessage = RecipePortion.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_nullPortion_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(
                new JsonAdaptedName(CACIO_NAME),
                null,
                Optional.of(CACIO_DURATION).map(JsonAdaptedRecipeDuration::new),
                CACIO_TAGS.stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                CACIO_INGREDIENTS.stream().map(JsonAdaptedIngredient::new).collect(Collectors.toList()),
                CACIO_STEPS.stream().map(JsonAdaptedStep::new).collect(Collectors.toList())
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RecipePortion.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(
                new JsonAdaptedName(CACIO_NAME),
                Optional.of(CACIO_PORTION).map(JsonAdaptedRecipePortion::new),
                Optional.of(INVALID_DURATION),
                CACIO_TAGS.stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                CACIO_INGREDIENTS.stream().map(JsonAdaptedIngredient::new).collect(Collectors.toList()),
                CACIO_STEPS.stream().map(JsonAdaptedStep::new).collect(Collectors.toList())
        );
        String expectedMessage = RecipeDuration.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_nullDuration_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(
                new JsonAdaptedName(CACIO_NAME),
                Optional.of(CACIO_PORTION).map(JsonAdaptedRecipePortion::new),
                null,
                CACIO_TAGS.stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                CACIO_INGREDIENTS.stream().map(JsonAdaptedIngredient::new).collect(Collectors.toList()),
                CACIO_STEPS.stream().map(JsonAdaptedStep::new).collect(Collectors.toList())
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RecipeDuration.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = CACIO_TAGS.stream().map(JsonAdaptedTag::new).collect(Collectors.toList());
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(
                new JsonAdaptedName(CACIO_NAME),
                Optional.of(CACIO_PORTION).map(JsonAdaptedRecipePortion::new),
                Optional.of(CACIO_DURATION).map(JsonAdaptedRecipeDuration::new),
                invalidTags,
                CACIO_INGREDIENTS.stream().map(JsonAdaptedIngredient::new).collect(Collectors.toList()),
                CACIO_STEPS.stream().map(JsonAdaptedStep::new).collect(Collectors.toList()));
        String expectedMessage = String.format(Tag.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidIngredients_throwsIllegalValueException() {
        List<JsonAdaptedIngredient> invalidIngredients =
                CACIO_INGREDIENTS.stream().map(JsonAdaptedIngredient::new).collect(Collectors.toList());
        invalidIngredients.add(new JsonAdaptedIngredient(INVALID_INGREDIENT));
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(
                new JsonAdaptedName(CACIO_NAME),
                Optional.of(CACIO_PORTION).map(JsonAdaptedRecipePortion::new),
                Optional.of(CACIO_DURATION).map(JsonAdaptedRecipeDuration::new),
                CACIO_TAGS.stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                invalidIngredients,
                CACIO_STEPS.stream().map(JsonAdaptedStep::new).collect(Collectors.toList()));
        String expectedMessage = String.format(Ingredient.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidSteps_throwsIllegalValueException() {
        List<JsonAdaptedStep> invalidSteps =
                CACIO_STEPS.stream().map(JsonAdaptedStep::new).collect(Collectors.toList());
        invalidSteps.add(new JsonAdaptedStep(INVALID_INGREDIENT));
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(
                new JsonAdaptedName(CACIO_NAME),
                Optional.of(CACIO_PORTION).map(JsonAdaptedRecipePortion::new),
                Optional.of(CACIO_DURATION).map(JsonAdaptedRecipeDuration::new),
                CACIO_TAGS.stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                CACIO_INGREDIENTS.stream().map(JsonAdaptedIngredient::new).collect(Collectors.toList()),
                invalidSteps);
        String expectedMessage = String.format(Step.MESSAGE_CONSTRAINTS);
        assertThrows(IllegalValueException.class, expectedMessage, recipe::toModelType);
    }

}
