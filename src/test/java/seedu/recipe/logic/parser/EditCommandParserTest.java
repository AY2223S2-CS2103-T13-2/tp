package seedu.recipe.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.logic.commands.CommandTestUtil.DESC_FISH;
import static seedu.recipe.logic.commands.CommandTestUtil.DURATION_DESC_CHICKEN;
import static seedu.recipe.logic.commands.CommandTestUtil.DURATION_DESC_FISH;
import static seedu.recipe.logic.commands.CommandTestUtil.INGREDIENT_DESC_FISH;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_PORTION_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_STEP_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.NAME_DESC_CHICKEN;
import static seedu.recipe.logic.commands.CommandTestUtil.NAME_DESC_FISH;
import static seedu.recipe.logic.commands.CommandTestUtil.PORTION_DESC_CHICKEN;
import static seedu.recipe.logic.commands.CommandTestUtil.PORTION_DESC_FISH;
import static seedu.recipe.logic.commands.CommandTestUtil.STEP_DESC_FISH;
import static seedu.recipe.logic.commands.CommandTestUtil.TAG_DESC_CHINESE;
import static seedu.recipe.logic.commands.CommandTestUtil.TAG_DESC_ITALIAN;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_DURATION_FISH;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_INGREDIENT_FISH;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_NAME_CHICKEN;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_NAME_FISH;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_PORTION_FISH;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_STEP_FISH;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_TAG_CHINESE;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_TAG_ITALIAN;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.recipe.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static seedu.recipe.testutil.TypicalIndexes.INDEX_SECOND_RECIPE;
import static seedu.recipe.testutil.TypicalIndexes.INDEX_THIRD_RECIPE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.core.index.Index;
import seedu.recipe.logic.commands.EditCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.logic.util.RecipeDescriptor;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.exceptions.RecipeDurationInvalidArgumentLengthException;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.tag.Tag;
import seedu.recipe.testutil.EditRecipeDescriptorBuilder;

//@@author alson001
public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_CHICKEN, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_CHICKEN, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_CHICKEN, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", IngredientBuilder.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // invalid portion
        assertParseFailure(parser, "1" + INVALID_PORTION_DESC, RecipePortion.MESSAGE_CONSTRAINTS);

        // invalid duration
        assertParseFailure(parser, "1" + INVALID_DURATION_DESC, RecipeDurationInvalidArgumentLengthException
            .MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid step
        assertParseFailure(parser, "1" + INVALID_STEP_DESC, Step.MESSAGE_CONSTRAINTS);

        // invalid portion followed by valid duration
        assertParseFailure(parser, "1" + INVALID_PORTION_DESC + DURATION_DESC_CHICKEN,
                RecipePortion.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Recipe} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_CHINESE + TAG_DESC_ITALIAN + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_ITALIAN + TAG_EMPTY + TAG_DESC_CHINESE, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_CHINESE + TAG_DESC_ITALIAN, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_PORTION_DESC
                + INVALID_DURATION_DESC, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_RECIPE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_FISH + PORTION_DESC_FISH + DURATION_DESC_FISH
            + TAG_DESC_ITALIAN + INGREDIENT_DESC_FISH + STEP_DESC_FISH;
        EditRecipeDescriptorBuilder descriptor = new EditRecipeDescriptorBuilder().withName(VALID_NAME_FISH)
            .withPortion(VALID_PORTION_FISH).withDuration(VALID_DURATION_FISH).withTags(VALID_TAG_ITALIAN)
            .withIngredients(VALID_INGREDIENT_FISH).withSteps(VALID_STEP_FISH);
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor.build());
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_RECIPE;
        String userInput = targetIndex.getOneBased() + PORTION_DESC_FISH + DURATION_DESC_FISH;
        EditRecipeDescriptorBuilder descriptor = new EditRecipeDescriptorBuilder().withPortion(VALID_PORTION_FISH)
            .withDuration(VALID_DURATION_FISH);
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor.build());
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_RECIPE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_FISH;
        EditRecipeDescriptorBuilder descriptor = new EditRecipeDescriptorBuilder().withName(VALID_NAME_FISH);
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor.build());
        assertParseSuccess(parser, userInput, expectedCommand);
        // portion
        userInput = targetIndex.getOneBased() + PORTION_DESC_FISH;
        descriptor = new EditRecipeDescriptorBuilder().withPortion(VALID_PORTION_FISH);
        expectedCommand = new EditCommand(targetIndex, descriptor.build());
        assertParseSuccess(parser, userInput, expectedCommand);
        // duration
        userInput = targetIndex.getOneBased() + DURATION_DESC_FISH;
        descriptor = new EditRecipeDescriptorBuilder().withDuration(VALID_DURATION_FISH);
        expectedCommand = new EditCommand(targetIndex, descriptor.build());
        assertParseSuccess(parser, userInput, expectedCommand);
        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_ITALIAN;
        descriptor = new EditRecipeDescriptorBuilder().withTags(VALID_TAG_ITALIAN);
        expectedCommand = new EditCommand(targetIndex, descriptor.build());
        assertParseSuccess(parser, userInput, expectedCommand);
        // ingredients
        userInput = targetIndex.getOneBased() + INGREDIENT_DESC_FISH;
        descriptor = new EditRecipeDescriptorBuilder().withIngredients(VALID_INGREDIENT_FISH);
        expectedCommand = new EditCommand(targetIndex, descriptor.build());
        assertParseSuccess(parser, userInput, expectedCommand);
        // Steps
        userInput = targetIndex.getOneBased() + STEP_DESC_FISH;
        descriptor = new EditRecipeDescriptorBuilder().withSteps(VALID_STEP_FISH);
        expectedCommand = new EditCommand(targetIndex, descriptor.build());
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_RECIPE;
        String userInput = targetIndex.getOneBased() + PORTION_DESC_CHICKEN + DURATION_DESC_CHICKEN
            + TAG_DESC_ITALIAN + PORTION_DESC_FISH + DURATION_DESC_FISH + TAG_DESC_CHINESE;
        EditRecipeDescriptorBuilder descriptor = new EditRecipeDescriptorBuilder().withPortion(VALID_PORTION_FISH)
            .withDuration(VALID_DURATION_FISH).withTags(VALID_TAG_ITALIAN, VALID_TAG_CHINESE);
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor.build());
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_RECIPE;
        String userInput = targetIndex.getOneBased() + INVALID_PORTION_DESC + PORTION_DESC_FISH;
        EditRecipeDescriptorBuilder descriptor =
                new EditRecipeDescriptorBuilder().withPortion(VALID_PORTION_FISH);
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor.build());
        assertParseSuccess(parser, userInput, expectedCommand);
        // other valid values specified
        userInput = targetIndex.getOneBased() + PORTION_DESC_FISH + INVALID_DURATION_DESC + DURATION_DESC_FISH
                + INGREDIENT_DESC_FISH;
        descriptor = new EditRecipeDescriptorBuilder().withPortion(VALID_PORTION_FISH).withDuration(VALID_DURATION_FISH)
                .withIngredients(VALID_INGREDIENT_FISH);
        expectedCommand = new EditCommand(targetIndex, descriptor.build());
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_RECIPE;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        RecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parseTagsForEdit_emptyTagsOptional() {
        Collection<String> tags = Collections.emptyList();
        Optional<Set<Tag>> expectedTagsOptional = Optional.empty();

        try {
            assertEquals(expectedTagsOptional, parser.parseTagsForEdit(tags));
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseTagsForEdit_nonEmptyTagsOptional() {
        Collection<String> tags = Arrays.asList(VALID_TAG_ITALIAN, VALID_TAG_CHINESE);
        Optional<Set<Tag>> expectedTagsOptional = Optional.of(
                new HashSet<>(Arrays.asList(new Tag(VALID_TAG_ITALIAN), new Tag(VALID_TAG_CHINESE))));

        try {
            assertEquals(expectedTagsOptional, parser.parseTagsForEdit(tags));
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseIngredientsForEdit_emptyIngredientsOptional() {
        Collection<String> ingredients = Collections.emptyList();
        Optional<HashMap<Ingredient, IngredientInformation>> expectedIngredientsOptional = Optional.empty();

        try {
            assertEquals(expectedIngredientsOptional, parser.parseIngredientsForEdit(ingredients));
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseIngredientsForEdit_nonEmptyIngredientsOptional() {
        Collection<String> ingredients = List.of(VALID_INGREDIENT_FISH);
        Map<Ingredient, IngredientInformation> expectedIngredientsMap = DESC_FISH.getIngredients().get();
        Optional<Map<Ingredient, IngredientInformation>> expectedIngredientsOptional =
                Optional.of(expectedIngredientsMap);
        try {
            assertEquals(expectedIngredientsOptional, parser.parseIngredientsForEdit(ingredients));
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseIngredientsForEdit_emptyStringList() {
        try {
            assertEquals(Optional.of(Map.of()), parser.parseIngredientsForEdit(List.of("")));
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseStepsForEdit_emptyStepsOptional() {
        Collection<String> steps = Collections.emptyList();
        Optional<List<Step>> expectedStepsOptional = Optional.empty();

        try {
            assertEquals(expectedStepsOptional, parser.parseStepsForEdit(steps));
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseStepsForEdit_nonEmptyStepsOptional() {
        Collection<String> steps = List.of(VALID_STEP_FISH);
        List<Step> expectedStepsList = DESC_FISH.getSteps().get();
        Optional<List<Step>> expectedStepsOptional = Optional.of(expectedStepsList);
        try {
            assertEquals(expectedStepsOptional, parser.parseStepsForEdit(steps));
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }
}
