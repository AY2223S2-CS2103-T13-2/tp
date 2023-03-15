package seedu.recipe.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.util.*;

import seedu.recipe.commons.core.index.Index;
import seedu.recipe.commons.util.StringUtil;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.*;
import seedu.recipe.model.recipe.unit.PortionUnit;
import seedu.recipe.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
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
        if (!RecipeDuration.isValidRecipeDuration(trimmedDuration)) {
            throw new ParseException(RecipeDuration.MESSAGE_CONSTRAINTS);
        }
        return RecipeDuration.of(trimmedDuration);
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
        if (!RecipePortion.isValidRecipePortion(trimmedPortion)) {
            throw new ParseException(RecipePortion.MESSAGE_CONSTRAINTS);
        }
        return RecipePortion.of(trimmedPortion);
    }

    /**
     * Parses a {@code String tag} into a {@code Ingredient}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Ingredient parseIngredient(String ingredient) throws ParseException {
        requireNonNull(ingredient);
        String trimmedIngredient = ingredient.trim();
        if (!Ingredient.isValidIngredient(trimmedIngredient)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Ingredient(trimmedIngredient);
    }

    /**
     * Parses a {@code String in} into a {@code Ingredient}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static List<Ingredient> parseIngredients(Collection<String> ingredients) throws ParseException {
        requireNonNull(ingredients);
        List<Ingredient> ingredientList = new ArrayList<>();
        for (String ingredientName: ingredients) {
            ingredientList.add(parseIngredient(ingredientName));
        }
        return ingredientList;
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
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    public static Step parseStep(String step) throws ParseException {
        String trimmedStep = step.trim();
        if (!Step.isValidStep(trimmedStep)) {
            throw new ParseException(Step.MESSAGE_CONSTRAINTS);
        }
        return new Step(step);
    }

    public static List<Step> parseSteps(Collection<String> steps) throws ParseException {
        requireNonNull(steps);
        List<Step> stepList = new ArrayList<>();
        for (String stepDescription: steps) {
            stepList.add(parseStep(stepDescription));
        }
        return stepList;
    }
}
