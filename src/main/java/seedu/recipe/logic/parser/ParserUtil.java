package seedu.recipe.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_PORTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.recipe.commons.core.index.Index;
import seedu.recipe.commons.util.StringUtil;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.logic.util.RecipeDescriptor;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.RecipeDuration;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code Duration}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static RecipeDuration parseDuration(String duration) throws ParseException {
        requireNonNull(duration);
        String trimmedDuration = duration.trim();

        try {
            return RecipeDuration.of(trimmedDuration);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
    }

    /**
     * Parses a {@code String name} into a {@code Portion}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static RecipePortion parsePortion(String portion) throws ParseException {
        requireNonNull(portion);
        String trimmedPortion = portion.trim();

        try {
            return RecipePortion.of(trimmedPortion);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
    }

    /**
     * Parses a {@code String tag} into a {@code IngredientBuilder}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static HashMap<Ingredient, IngredientInformation> parseIngredient(String ingredient) throws ParseException {
        requireNonNull(ingredient);
        String trimmedIngredient = ingredient.trim();
        try {
            IngredientBuilder ingredientBuilder = new IngredientBuilder(trimmedIngredient);
            return ingredientBuilder.build();
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
    }

    /**
     * Parses a {@code String in} into a {@code IngredientBuilder}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static HashMap<Ingredient, IngredientInformation> parseIngredients(
        Collection<String> ingredients) throws ParseException {
        requireNonNull(ingredients);
        HashMap<Ingredient, IngredientInformation> ingredientMap = new HashMap<>();
        for (String ingredientName : ingredients) {
            ingredientMap.putAll(parseIngredient(ingredientName));
        }
        return ingredientMap;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();

        // ensure tags are non-empty
        if (tags.size() != 1 || !tags.contains("")) {
            for (String tagName : tags) {
                tagSet.add(parseTag(tagName));
            }
        }
        return tagSet;
    }

    /**
     * Creates and returns a Step instance, given a String description.
     *
     * @param step The String description
     * @return The Step Instance
     * @throws ParseException The Exception resulting from an invalid Step description provided.
     */
    public static Step parseStep(String step) throws ParseException {
        requireNonNull(step);
        String trimmedStep = step.trim();
        if (!Step.isValidStep(trimmedStep)) {
            throw new ParseException(Step.MESSAGE_CONSTRAINTS);
        }
        return new Step(step);
    }

    /**
     * Creates and returns a list of Step instances, given a Collection of String descriptions.
     *
     * @param steps The Collection of String descriptions
     * @return The list of Step instances
     * @throws ParseException The Exception resulting from an invalid Step description provided.
     */
    public static List<Step> parseSteps(Collection<String> steps) throws ParseException {
        requireNonNull(steps);
        List<Step> stepList = new ArrayList<>();

        // ensure steps are non-empty
        if (steps.size() != 1 || !steps.contains("")) {
            for (String stepDescription : steps) {
                stepList.add(parseStep(stepDescription));
            }
        }
        return stepList;
    }

    /**
     * Creates a RecipeDescriptor from a multimap of recipe arguments.
     *
     * @param argMultimap Multimap of recipe arguments.
     * @return RecipeDescriptor created from given argument multimap.
     * @throws ParseException Thrown when any argument is unable to be properly parsed.
     */
    public static RecipeDescriptor parseToRecipeDescriptor(ArgumentMultimap argMultimap) throws ParseException {
        RecipeDescriptor recipeDescriptor = new RecipeDescriptor();

        Optional<String> nameString = argMultimap.getValue(PREFIX_NAME);
        if (nameString.isPresent()) {
            Name name = parseName(nameString.get());
            recipeDescriptor.setName(name);
        }

        Optional<String> durationString = argMultimap.getValue(PREFIX_DURATION);
        if (durationString.isPresent()) {
            RecipeDuration duration = null;
            // handle the case where empty duration string is provided
            // interpret this as: user wants to clear the duration field
            if (!durationString.get().trim().isEmpty()) {
                duration = parseDuration(durationString.get());
            }
            recipeDescriptor.setDuration(duration);
            recipeDescriptor.setDurationChanged(true);
        }

        Optional<String> portionString = argMultimap.getValue(PREFIX_PORTION);
        if (portionString.isPresent()) {
            RecipePortion portion = null;
            // handle the case where empty portion string is provided
            // interpret this as: user wants to clear the portion field
            if (!portionString.get().trim().isEmpty()) {
                portion = parsePortion(portionString.get());
            }
            recipeDescriptor.setPortion(portion);
            recipeDescriptor.setPortionChanged(true);
        }

        if (argMultimap.containsKey(PREFIX_TAG)) {
            Set<Tag> tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            recipeDescriptor.setTags(tags);
        }

        if (argMultimap.containsKey(PREFIX_INGREDIENT)) {
            HashMap<Ingredient, IngredientInformation> ingredientTable = ParserUtil.parseIngredients(
                argMultimap.getAllValues(PREFIX_INGREDIENT));
            recipeDescriptor.setIngredients(ingredientTable);
        }

        if (argMultimap.containsKey(PREFIX_STEP)) {
            List<Step> steps = ParserUtil.parseSteps(argMultimap.getAllValues(PREFIX_STEP));
            recipeDescriptor.setSteps(steps);
        }

        return recipeDescriptor;
    }

}
