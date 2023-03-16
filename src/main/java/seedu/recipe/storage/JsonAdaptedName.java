package seedu.recipe.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Name;

import java.util.Optional;

/**
 * Jackson-friendly version of {@link Name}.
 */
@JsonInclude(Include.NON_NULL)
class JsonAdaptedName {

    private final String name;

    /**
     * Constructs a {@code JsonAdaptedName} with the given {@code NameName}.
     */
    @JsonCreator
    public JsonAdaptedName(String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code Name} into this class for Jackson use.
     */
    public JsonAdaptedName(Name source) {
        name = source.toString();
    }

    @JsonValue
    public String getName() {
        return name;
    }

    /**
     * Converts this Jackson-friendly adapted Name object into the model's {@code Name} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Name.
     */
    public Name toModelType() throws IllegalValueException {
        //WIP
        return new Name(name);
    }

    public Optional<Name> toModelTypeOptional() {
        try {
            Name out = this.toModelType();
            return Optional.ofNullable(out);
        } catch (IllegalValueException e) {
            //log
            return Optional.empty();
        }
    }
}
