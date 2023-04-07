package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.logic.parser.AddCommandParser;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.logic.util.RecipeDescriptor;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.ui.AddRecipeForm;

/**
 * Adds a recipe to the recipe book via a form UI.
 */
public class AddFormCommand extends Command {

    public static final String COMMAND_WORD = "addf";
    public static final String MESSAGE_EMPTY = "An empty form was submitted. Please enter a recipe name before "
        + "submitting.";
    public static final String MESSAGE_SUCCESS = "New recipe added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the recipe book";
    public static final String MESSAGE_PARSE_RECIPE = "This recipe could not be parsed properly.";

    /**
     * Creates an AddFormCommand instance.
     */
    public AddFormCommand() {
    }

    /**
     * Executes the AddFormCommand, which opens a new RecipeForm instance and adds the recipe.
     *
     * @param model The {@code Model} which the command should operate on.
     * @return A {@code CommandResult} with the message indicating the success of the operation.
     * @throws CommandException If the recipe already exists in the recipe book.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder data = new StringBuilder();
        AddRecipeForm recipeForm = new AddRecipeForm(data);
        recipeForm.display();

        String commandString = data.toString();
        if (commandString.isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY);
        }
        try {
            RecipeDescriptor toAdd = AddCommandParser.parseToRecipeDescriptor(commandString);
            Recipe recipeToAdd = toAdd.toRecipe();
            if (model.hasRecipe(recipeToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
            }
            model.addRecipe(recipeToAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, recipeToAdd.getName()));
        } catch (ParseException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
