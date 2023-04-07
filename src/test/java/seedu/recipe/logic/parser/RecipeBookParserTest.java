package seedu.recipe.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.recipe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.recipe.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.recipe.logic.commands.AddCommand;
import seedu.recipe.logic.commands.AddFormCommand;
import seedu.recipe.logic.commands.ClearCommand;
import seedu.recipe.logic.commands.DeleteCommand;
import seedu.recipe.logic.commands.EditCommand;
import seedu.recipe.logic.commands.ExitCommand;
import seedu.recipe.logic.commands.FindCommand;
import seedu.recipe.logic.commands.HelpCommand;
import seedu.recipe.logic.commands.ListCommand;
import seedu.recipe.logic.commands.SubCommand;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.logic.util.FindUtil;
import seedu.recipe.logic.util.RecipeDescriptor;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.PropertyNameContainsKeywordsPredicate;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.RecipeDuration;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.testutil.EditRecipeDescriptorBuilder;
import seedu.recipe.testutil.RecipeUtil;

public class RecipeBookParserTest {
    private final RecipeBookParser parser = new RecipeBookParser();

    @Test
    public void parseCommand_add_success() {
        //Set up data
        Recipe recipe = new Recipe(new Name("Lasagna"));
        RecipeDescriptor descriptor = new RecipeDescriptor();
        descriptor.setName(recipe.getName());
        //Set up assertions
        try {
            AddCommand command = (AddCommand) parser.parseCommand(RecipeUtil.getAddCommand(recipe));
            assertEquals(new AddCommand(descriptor), command);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseCommand_addForm_success() {
        assertDoesNotThrow(() -> parser.parseCommand(AddFormCommand.COMMAND_WORD));
        try {
            assertTrue(parser.parseCommand(AddFormCommand.COMMAND_WORD) instanceof AddFormCommand);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseCommand_clear_success() {
        try {
            assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
            assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseCommand_delete_success() {
        try {
            DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_RECIPE.getOneBased());
            assertEquals(new DeleteCommand(INDEX_FIRST_RECIPE), command);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseCommand_edit_success() {
        Recipe recipe = new Recipe(new Name("Lasagna"));
        recipe.setPortion(RecipePortion.of("1 - 2 servings"));
        recipe.setDuration(RecipeDuration.of("15 min"));
        RecipeDescriptor descriptor = new EditRecipeDescriptorBuilder(recipe).build();

        try {
            EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_RECIPE.getOneBased() + " "
                + RecipeUtil.getEditRecipeDescriptorDetails(descriptor)
            );
            assertEquals(new EditCommand(INDEX_FIRST_RECIPE, descriptor), command);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseCommand_exit_success() {
        try {
            assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
            assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseCommand_find_success() {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand expected = new FindCommand(
            new PropertyNameContainsKeywordsPredicate<>(
                keywords,
                FindUtil.GET_NAME_FROM_RECIPE,
                FindUtil.GET_NAME_STRING
            )
        );

        try {
            FindCommand command = (FindCommand) parser.parseCommand(
                    FindCommand.COMMAND_WORD + " " + String.join(" ", keywords)
            );
            assertEquals(expected, command);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseCommand_help_success() {
        try {
            assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
            assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseCommand_list_success() {
        try {
            assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
            assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseCommand_sub_success() {
        try {
            assertThrows(ParseException.class, () -> parser.parseCommand(SubCommand.COMMAND_WORD));
            assertTrue(parser.parseCommand(SubCommand.COMMAND_WORD + " chicken") instanceof SubCommand);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    /* ----- INVALID INPUTS ---------------- */
    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), () ->
                parser.parseCommand("")
        );
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
