package seedu.recipe.logic.parser.functional;

import seedu.recipe.logic.parser.exceptions.ParseException;

public interface CheckedParseFunction <T,R> {
    R apply(T t) throws ParseException;
}
