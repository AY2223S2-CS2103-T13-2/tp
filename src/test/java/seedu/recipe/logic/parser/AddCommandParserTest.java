package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.logic.commands.CommandTestUtil.DESC_CHICKEN;
import static seedu.recipe.logic.commands.CommandTestUtil.DURATION_DESC_CHICKEN;
import static seedu.recipe.logic.commands.CommandTestUtil.DURATION_DESC_FISH;
import static seedu.recipe.logic.commands.CommandTestUtil.INGREDIENT_DESC_CHICKEN;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_PORTION_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_STEP_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.recipe.logic.commands.CommandTestUtil.NAME_DESC_CHICKEN;
import static seedu.recipe.logic.commands.CommandTestUtil.NAME_DESC_FISH;
import static seedu.recipe.logic.commands.CommandTestUtil.PORTION_DESC_CHICKEN;
import static seedu.recipe.logic.commands.CommandTestUtil.PORTION_DESC_FISH;
import static seedu.recipe.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.recipe.logic.commands.CommandTestUtil.STEP_DESC_CHICKEN;
import static seedu.recipe.logic.commands.CommandTestUtil.TAG_DESC_CHINESE;
import static seedu.recipe.logic.commands.CommandTestUtil.TAG_DESC_ITALIAN;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_NAME_CHICKEN;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_TAG_CHINESE;
import static seedu.recipe.logic.commands.CommandTestUtil.VALID_TAG_ITALIAN;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.recipe.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.commands.AddCommand;
import seedu.recipe.logic.util.RecipeDescriptor;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.exceptions.RecipeDurationInvalidArgumentLengthException;
import seedu.recipe.model.tag.Tag;

//@@author alson001
public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        AddCommandParser parser = new AddCommandParser();
        RecipeDescriptor expectedRecipeDescriptor = new RecipeDescriptor(DESC_CHICKEN);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_CHICKEN + PORTION_DESC_CHICKEN
            + DURATION_DESC_CHICKEN + TAG_DESC_CHINESE + INGREDIENT_DESC_CHICKEN + STEP_DESC_CHICKEN,
            new AddCommand(expectedRecipeDescriptor));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_FISH + NAME_DESC_CHICKEN + PORTION_DESC_CHICKEN
            + DURATION_DESC_CHICKEN + TAG_DESC_CHINESE + INGREDIENT_DESC_CHICKEN + STEP_DESC_CHICKEN,
            new AddCommand(expectedRecipeDescriptor));

        // multiple portions - last portion accepted
        assertParseSuccess(parser, NAME_DESC_CHICKEN + PORTION_DESC_FISH + PORTION_DESC_CHICKEN
            + DURATION_DESC_CHICKEN + TAG_DESC_CHINESE + INGREDIENT_DESC_CHICKEN + STEP_DESC_CHICKEN,
            new AddCommand(expectedRecipeDescriptor));

        // multiple durations - last duration accepted
        assertParseSuccess(parser, NAME_DESC_CHICKEN + PORTION_DESC_CHICKEN + DURATION_DESC_FISH
            + DURATION_DESC_CHICKEN + TAG_DESC_CHINESE + INGREDIENT_DESC_CHICKEN + STEP_DESC_CHICKEN,
            new AddCommand(expectedRecipeDescriptor));

        // multiple tags - all accepted
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(new Tag(VALID_TAG_CHINESE));
        tagSet.add(new Tag(VALID_TAG_ITALIAN));
        RecipeDescriptor expectedRecipeMultipleTags = new RecipeDescriptor(DESC_CHICKEN);
        expectedRecipeMultipleTags.setTags(tagSet);
        assertParseSuccess(parser, NAME_DESC_CHICKEN + PORTION_DESC_CHICKEN + DURATION_DESC_CHICKEN
            + TAG_DESC_CHINESE + TAG_DESC_ITALIAN + INGREDIENT_DESC_CHICKEN + STEP_DESC_CHICKEN,
            new AddCommand(expectedRecipeMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        RecipeDescriptor expectedRecipe = new RecipeDescriptor(DESC_CHICKEN);
        expectedRecipe.setTags(null);
        assertParseSuccess(parser, NAME_DESC_CHICKEN + PORTION_DESC_CHICKEN + DURATION_DESC_CHICKEN
            + INGREDIENT_DESC_CHICKEN + STEP_DESC_CHICKEN,
            new AddCommand(expectedRecipe));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_CHICKEN + PORTION_DESC_CHICKEN + DURATION_DESC_CHICKEN
            + TAG_DESC_CHINESE + INGREDIENT_DESC_CHICKEN + STEP_DESC_CHICKEN,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PORTION_DESC_CHICKEN + DURATION_DESC_CHICKEN
            + TAG_DESC_CHINESE + INGREDIENT_DESC_CHICKEN + STEP_DESC_CHICKEN, Name.MESSAGE_CONSTRAINTS);

        // invalid portion
        assertParseFailure(parser, NAME_DESC_CHICKEN + INVALID_PORTION_DESC + DURATION_DESC_CHICKEN
            + TAG_DESC_CHINESE + INGREDIENT_DESC_CHICKEN + STEP_DESC_CHICKEN,
            RecipePortion.MESSAGE_CONSTRAINTS);

        // invalid duration
        assertParseFailure(parser, NAME_DESC_CHICKEN + PORTION_DESC_CHICKEN + INVALID_DURATION_DESC
                + TAG_DESC_CHINESE + INGREDIENT_DESC_CHICKEN + STEP_DESC_CHICKEN,
                RecipeDurationInvalidArgumentLengthException.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_CHICKEN + PORTION_DESC_CHICKEN + DURATION_DESC_CHICKEN
            + INVALID_TAG_DESC + INGREDIENT_DESC_CHICKEN + STEP_DESC_CHICKEN,
            Tag.MESSAGE_CONSTRAINTS);

        // invalid step
        assertParseFailure(parser, NAME_DESC_CHICKEN + PORTION_DESC_CHICKEN + DURATION_DESC_CHICKEN
            + TAG_DESC_CHINESE + INGREDIENT_DESC_CHICKEN + INVALID_STEP_DESC,
            Step.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PORTION_DESC_CHICKEN + DURATION_DESC_CHICKEN
            + TAG_DESC_CHINESE + INGREDIENT_DESC_CHICKEN + INVALID_STEP_DESC,
            Name.MESSAGE_CONSTRAINTS);
    }
}
