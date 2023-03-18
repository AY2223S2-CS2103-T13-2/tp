package seedu.recipe.logic.parser;

import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.commands.AddCommand;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    // @Test
    // public void parse_allFieldsPresent_success() {
    //     Recipe expectedRecipe = new RecipeBuilder(BOB).withTags(VALID_TAG_FRIEND).build();
    //     // whitespace only preamble
    //     assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
    //             + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedRecipe));
    //     // multiple names - last name accepted
    //     assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
    //             + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedRecipe));
    //     // multiple phones - last phone accepted
    //     assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
    //             + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedRecipe));
    //     // multiple emails - last email accepted
    //     assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
    //             + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedRecipe));
    //     // multiple addresses - last address accepted
    //     assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
    //             + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedRecipe));
    //     // multiple tags - all accepted
    //     Recipe expectedRecipeMultipleTags = new RecipeBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
    //             .build();
    //     assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
    //             + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedRecipeMultipleTags));
    // }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
    //     Recipe expectedRecipe = new RecipeBuilder(AMY).withTags().build();
    //     assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
    //             new AddCommand(expectedRecipe));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        //     assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
        //             expectedMessage);
        //     // missing phone prefix
        //     assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
        //             expectedMessage);
        //     // missing email prefix
        //     assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
        //             expectedMessage);
        //     // missing address prefix
        //     assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
        //             expectedMessage);
        //     // all prefixes missing
        //     assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
        //     expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        //     assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
        //             + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);
        //     // invalid phone
        //     assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
        //             + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Ingredient.MESSAGE_CONSTRAINTS);
        //     // invalid email
        //     assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
        //             + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);
        //     // invalid address
        //     assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
        //             + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);
        //     // invalid tag
        //     assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
        //             + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);
        //     // two invalid values, only first invalid value reported
        //     assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
        //             Name.MESSAGE_CONSTRAINTS);
        //     // non-empty preamble
        //     assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
        //             + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
        //             String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
