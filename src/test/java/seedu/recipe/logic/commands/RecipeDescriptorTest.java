package seedu.recipe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.recipe.logic.util.RecipeDescriptor;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.RecipeDuration;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.recipe.ingredient.IngredientQuantity;
import seedu.recipe.model.tag.Tag;

public class RecipeDescriptorTest {
    private static final Name TEST_NAME = new Name("Cacio e Pepe");
    private static final RecipeDuration TEST_DURATION = RecipeDuration.of("15 minutes");
    private static final RecipePortion TEST_PORTION = RecipePortion.of("1 - 2 servings");
    private static final Set<Tag> TEST_TAGS = Set.of(new Tag("Italian"), new Tag("Carbs"));
    private static final HashMap<Ingredient, IngredientInformation> TEST_INGREDIENT_TABLE;
    private static final IngredientBuilder TEST_BUILDER;
    private static final List<IngredientBuilder> TEST_INGREDIENT_LIST;
    private static final List<Step> TEST_STEPS;
    static {
        TEST_INGREDIENT_TABLE = new HashMap<>();
        TEST_INGREDIENT_TABLE.put(
                Ingredient.of("Pasta"),
                new IngredientInformation(
                        IngredientQuantity.of("100 g"), null, new String[]{}, new Ingredient[]{}
                )
        );

        TEST_BUILDER = new IngredientBuilder("-n Cheese -a 1 wedge");
        TEST_INGREDIENT_LIST = List.of(TEST_BUILDER);

        TEST_STEPS = List.of(new Step("Grate the cheese."));
    }
    private RecipeDescriptor testDescriptor;

    @BeforeEach
    public void setTestDescriptor() {
        testDescriptor = new RecipeDescriptor();
    }

    @Test
    public void defaultConstructor_initOkay() {
        assertDoesNotThrow(() -> new RecipeDescriptor());
    }

    @Test
    public void defaultConstructor_anyFieldEdited_isFalse() {
        assertFalse(testDescriptor.isAnyFieldEdited());
    }

    @Test
    public void defaultConstructor_toRecipe_nameIsBlank() {
        Recipe expected = new Recipe(new Name("BLANK RECIPE"));
        assertEquals(testDescriptor.toRecipe(), expected);
    }

    @Test
    public void defaultConstructor_getFields_allFieldsEmpty() {
        assertEquals(Optional.empty(), testDescriptor.getName());
        assertEquals(Optional.empty(), testDescriptor.getDuration());
        assertEquals(Optional.empty(), testDescriptor.getPortion());
        assertEquals(Optional.empty(), testDescriptor.getTags());
        assertEquals(Optional.empty(), testDescriptor.getIngredients());
        assertEquals(Optional.empty(), testDescriptor.getSteps());
    }

    @Test
    public void defaultConstructor_equals() {
        RecipeDescriptor defaultCopy = new RecipeDescriptor();
        assertEquals(testDescriptor, testDescriptor);
        assertEquals(testDescriptor, defaultCopy);
        assertNotEquals(testDescriptor, "");
    }

    @Test
    public void constructor_nullDescriptor_nullPointerExceptionThrown() {
        assertThrows(NullPointerException.class, () -> new RecipeDescriptor(null));
    }

    @Test
    public void set_getName() {
        assertDoesNotThrow(() -> testDescriptor.setName(TEST_NAME));
        assertEquals(Optional.of(TEST_NAME), testDescriptor.getName());
        assertTrue(testDescriptor.isAnyFieldEdited());
    }

    @Test
    public void set_getDuration() {
        assertDoesNotThrow(() -> testDescriptor.setDuration(TEST_DURATION));
        assertEquals(Optional.of(TEST_DURATION), testDescriptor.getDuration());
        assertTrue(testDescriptor.isAnyFieldEdited());
    }

    @Test
    public void set_getPortion() {
        assertDoesNotThrow(() -> testDescriptor.setPortion(TEST_PORTION));
        assertEquals(Optional.of(TEST_PORTION), testDescriptor.getPortion());
        assertTrue(testDescriptor.isAnyFieldEdited());
    }

    @Test
    public void set_getTags() {
        assertDoesNotThrow(() -> testDescriptor.setTags(TEST_TAGS));
        assertEquals(Optional.of(TEST_TAGS), testDescriptor.getTags());
        assertTrue(testDescriptor.isAnyFieldEdited());
    }

    @Test
    public void set_getIngredients() {
        //Set from HashMap
        assertDoesNotThrow(() -> testDescriptor.setIngredients(TEST_INGREDIENT_TABLE));
        assertEquals(testDescriptor.getIngredients(), Optional.of(TEST_INGREDIENT_TABLE));

        //Set from IngredientBuilder List
        assertDoesNotThrow(() -> testDescriptor.setIngredients(TEST_INGREDIENT_LIST));
        assertEquals(Optional.of(TEST_BUILDER.build()), testDescriptor.getIngredients());
        assertTrue(testDescriptor.isAnyFieldEdited());
    }

    @Test
    public void set_getSteps() {
        assertDoesNotThrow(() -> testDescriptor.setSteps(TEST_STEPS));
        assertEquals(Optional.of(TEST_STEPS), testDescriptor.getSteps());
        assertTrue(testDescriptor.isAnyFieldEdited());
    }

    @Test
    public void setPortionChanged() {
        assertFalse(testDescriptor.isAnyFieldEdited());
        testDescriptor.setPortionChanged(false);
        assertFalse(testDescriptor.isAnyFieldEdited());
        testDescriptor.setPortionChanged(true);
        assertTrue(testDescriptor.isAnyFieldEdited());
    }

    @Test
    public void setDurationChanged() {
        assertFalse(testDescriptor.isAnyFieldEdited());
        testDescriptor.setDurationChanged(false);
        assertFalse(testDescriptor.isAnyFieldEdited());
        testDescriptor.setDurationChanged(true);
        assertTrue(testDescriptor.isAnyFieldEdited());
    }

    @Test
    public void constructor_otherRecipeDescriptor_allFieldsCopied() {
        //Set up test descriptor
        testDescriptor.setName(TEST_NAME);
        testDescriptor.setDuration(TEST_DURATION);
        testDescriptor.setPortion(TEST_PORTION);
        testDescriptor.setTags(TEST_TAGS);
        testDescriptor.setIngredients(TEST_INGREDIENT_TABLE);
        testDescriptor.setSteps(TEST_STEPS);

        RecipeDescriptor actual = new RecipeDescriptor(testDescriptor);
        assertEquals(testDescriptor, actual);
    }

    @Test
    public void fieldsChanged_toRecipe_allFieldsCopied() {
        Recipe newRecipe = new Recipe(TEST_NAME);
        newRecipe.setDuration(TEST_DURATION);
        newRecipe.setPortion(TEST_PORTION);
        newRecipe.setTags(TEST_TAGS.toArray(Tag[]::new));
        newRecipe.setIngredients(TEST_INGREDIENT_TABLE);
        newRecipe.setSteps(TEST_STEPS.toArray(Step[]::new));
        testDescriptor.setName(TEST_NAME);
        testDescriptor.setDuration(TEST_DURATION);
        testDescriptor.setPortion(TEST_PORTION);
        testDescriptor.setTags(TEST_TAGS);
        testDescriptor.setIngredients(TEST_INGREDIENT_TABLE);
        testDescriptor.setSteps(TEST_STEPS);

        assertEquals(newRecipe, testDescriptor.toRecipe());
        assertEquals(newRecipe, testDescriptor.toRecipe(new Recipe(TEST_NAME)));

        //Test if neither Recipe has a Portion, but the current descriptor has its flag set to true.
        testDescriptor.setPortion(null);
        testDescriptor.setPortionChanged(true);
        newRecipe.setPortion(null);
        assertEquals(newRecipe, testDescriptor.toRecipe(newRecipe));
    }
}
