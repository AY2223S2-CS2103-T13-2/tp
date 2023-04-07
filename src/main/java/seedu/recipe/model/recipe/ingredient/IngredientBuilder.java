package seedu.recipe.model.recipe.ingredient;

import static seedu.recipe.commons.util.AppUtil.checkArgument;
import static seedu.recipe.model.recipe.ingredient.IngredientParser.AMOUNT_PREFIX;
import static seedu.recipe.model.recipe.ingredient.IngredientParser.COMMON_NAME_PREFIX;
import static seedu.recipe.model.recipe.ingredient.IngredientParser.ESTIMATE_PREFIX;
import static seedu.recipe.model.recipe.ingredient.IngredientParser.NAME_PREFIX;
import static seedu.recipe.model.recipe.ingredient.IngredientParser.REMARK_PREFIX;
import static seedu.recipe.model.recipe.ingredient.IngredientParser.SUBSTITUTION_PREFIX;
import static seedu.recipe.model.recipe.ingredient.IngredientParser.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.recipe.logic.parser.Prefix;

/**
 * Represents a recipe's ingredient in the recipe book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIngredient(String)}
 */
public class IngredientBuilder {
    public static final String MESSAGE_CONSTRAINTS =
        "Ingredients should follow this format: \n"
            + "[-a AMOUNT] [-e ESTIMATED AMOUNT] -n NAME "
            + "[-cn COMMON NAME] [-r REMARKS]... [-s SUBSTITUTION]...\n"
            + "i.e. -a 1 oz. -n butter -r cubed -s margarine";

    private final String commandString;

    private final HashMap<Prefix, List<String>> arguments;

    /**
     * Constructs a {@code IngredientBuilder}.
     *
     * @param commandString A valid ingredient number.
     */
    public IngredientBuilder(String commandString) {
        checkArgument(commandString != null, "An IngredientBuilder instance should not have a `null` command string.");
        HashMap<Prefix, List<String>> tokens = parse(commandString);
        checkArgument(tokens.containsKey(NAME_PREFIX), MESSAGE_CONSTRAINTS);
        this.commandString = commandString;
        this.arguments = tokens;
    }

    /**
     * Constructs an {@code IngredientBuilder} instance, from a Recipe instance's Ingredient Key-Value pair.
     * This is used for UI conversion for the Edit form functionality.
     *
     * @param mainIngredient The Recipe instance's valid ingredient key.
     * @param information    The Recipe instance's valid IngredientInformation value assigned to the Ingredient key.
     */
    public IngredientBuilder(Ingredient mainIngredient, IngredientInformation information) {
        StringBuilder commandString = new StringBuilder();

        //Quantity
        information.getQuantity()
            .map(v -> AMOUNT_PREFIX.getPrefix() + " " + v + " ")
            .ifPresent(commandString::append);
        //Estimated Amount
        information.getEstimatedQuantity()
            .map(v -> ESTIMATE_PREFIX.getPrefix() + " " + v + " ")
            .ifPresent(commandString::append);
        //Name
        commandString.append(NAME_PREFIX)
            .append(" ")
            .append(mainIngredient.getName())
            .append(" ");
        //Common Name
        if (!mainIngredient.getCommonName().isEmpty()) {
            commandString.append(COMMON_NAME_PREFIX.getPrefix())
                .append(" ")
                .append(mainIngredient.getCommonName())
                .append(" ");
        }
        //Remarks
        information.getRemarks().forEach(remark ->
            commandString.append(REMARK_PREFIX.getPrefix())
                .append(" ")
                .append(remark)
                .append(" "));
        //Substitutions
        information.getSubstitutions().forEach(ingredient ->
            commandString.append(SUBSTITUTION_PREFIX.getPrefix())
                .append(" ")
                .append(ingredient.getName())
                .append(" "));

        HashMap<Prefix, List<String>> tokens = IngredientParser.parse(commandString.toString());
        checkArgument(tokens.containsKey(NAME_PREFIX), MESSAGE_CONSTRAINTS);
        this.arguments = tokens;
        this.commandString = commandString.toString();
    }

    /**
     * Returns true if a given string is a valid ingredient.
     */
    public static boolean isValidIngredient(String test) {
        checkArgument(test != null, "An IngredientBuilder instance should not have a `null` command string.");
        HashMap<Prefix, List<String>> tokens = parse(test);
        return tokens.containsKey(NAME_PREFIX);
    }

    /**
     * Generates and returns a Key-Value pair for the enclosing {@code Recipe}
     * instance to store.
     *
     * @return The {@code HashMap} instance containing the key-value pair.
     */
    public HashMap<Ingredient, IngredientInformation> build() {
        HashMap<Ingredient, IngredientInformation> ingredientKeyValuePair = new HashMap<>();
        ingredientKeyValuePair.put(createMainIngredient(), createInformation());
        return ingredientKeyValuePair;
    }

    /**
     * Parses the prefix table for the fields pertaining to an Ingredient instance, creates, and returns a new
     * Ingredient instance around those fields.
     *
     * @return The new Ingredient instance.
     */
    private Ingredient createMainIngredient() {
        Ingredient mainIngredient = Ingredient.of(this.arguments.get(NAME_PREFIX).get(0));
        if (arguments.containsKey(COMMON_NAME_PREFIX)) { //First common name
            String commonName = arguments.get(COMMON_NAME_PREFIX).get(0);
            mainIngredient.setCommonName(commonName);
        }
        return mainIngredient;
    }

    /**
     * Parses the prefix table for the fields pertaining to an IngredientInformation instance, creates,
     * and returns an IngredientInformation instance around those fields.
     *
     * @return The IngredientInformation instance.
     */
    private IngredientInformation createInformation() {
        IngredientQuantity quantity = null;
        String estimatedQuantity = "";
        List<String> remarks = new ArrayList<>();
        Set<Ingredient> substitutes = new HashSet<>();

        if (arguments.containsKey(AMOUNT_PREFIX)) { //Take the first one
            quantity = IngredientQuantity.of(arguments.get(AMOUNT_PREFIX).get(0));
        }
        if (arguments.containsKey(ESTIMATE_PREFIX)) { //First estimated name
            estimatedQuantity = arguments.get(ESTIMATE_PREFIX).get(0);
        }
        if (arguments.containsKey(REMARK_PREFIX)) { //Remarks
            //Invalid remarks will fail silently, and not get added.
            //TODO: Add logging here
            List<String> remarkList = arguments.get(REMARK_PREFIX);
            remarks.addAll(remarkList.stream()
                .filter(s -> s.matches("^[A-Za-z]+(\\s+[A-Za-z]+)*"))
                .collect(Collectors.toList()));
        }
        if (arguments.containsKey(SUBSTITUTION_PREFIX)) { //Substitutions
            //Invalid substitutions will indicate to the system via errors thrown by Ingredient.
            substitutes.addAll(arguments.get(SUBSTITUTION_PREFIX)
                .stream()
                .map(Ingredient::of)
                .collect(Collectors.toList()));
        }
        return new IngredientInformation(
            quantity,
            estimatedQuantity,
            remarks.toArray(String[]::new),
            substitutes.toArray(Ingredient[]::new)
        );
    }

    @Override
    public String toString() {
        return commandString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof IngredientBuilder // instanceof handles nulls
            && commandString.equals(((IngredientBuilder) other).commandString)) // state check
            && arguments.equals(((IngredientBuilder) other).arguments);
    }

    @Override
    public int hashCode() {
        return commandString.hashCode();
    }
}
