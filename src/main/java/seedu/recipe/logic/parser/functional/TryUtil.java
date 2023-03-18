package seedu.recipe.logic.parser.functional;

import java.util.Optional;

import seedu.recipe.logic.parser.exceptions.ParseException;

/**
 * Represents a utility that provides a deterministic output type when its safeCompute method is called.
 */
public class TryUtil {
    /**
     * Executes a function which may throw a ParseException, and returns an Optional instance wrapped around
     * the valid output, if it executes normally. Else, returns an empty Optional instance.
     * @param function The function which may throw a ParseException.
     * @param val The value to pass into the function.
     * @param <T> The input parameter type
     * @param <U> The output type around which the Optional is wrapped
     * @return The output Optional instance.
     */
    public static <T, U> Optional<U> safeCompute(CheckedParseFunction<? super T, ? extends U> function, T val) {
        try {
            U out = function.apply(val);
            return Optional.of(out);
        } catch (ParseException e) {
            return Optional.empty();
        }
    }
}
