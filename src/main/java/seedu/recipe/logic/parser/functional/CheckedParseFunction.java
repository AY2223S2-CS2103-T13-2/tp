package seedu.recipe.logic.parser.functional;

import seedu.recipe.logic.parser.exceptions.ParseException;

/**
 * Represents a method which maps from an input type R to an output type T, which may generate a ParseException
 * in the process.
 * @param <T> The type of the input parameter.
 * @param <R> The return type of the function.
 */
public interface CheckedParseFunction <T, R> {
    R apply(T t) throws ParseException;
}
