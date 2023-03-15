package seedu.recipe.logic.parser.functional;

import seedu.recipe.logic.parser.exceptions.ParseException;

import java.util.Optional;

public class TryUtil {
    public static <T, U> Optional<U> safeCompute(CheckedParseFunction<? super T, ? extends U> function, T val) {
        try {
            U out = function.apply(val);
            return Optional.of(out);
        } catch (ParseException e) {
            return Optional.empty();
        }
    }
}
