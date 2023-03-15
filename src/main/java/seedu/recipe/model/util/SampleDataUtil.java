package seedu.recipe.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.ReadOnlyRecipeBook;
import seedu.recipe.model.recipe.*;
import seedu.recipe.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Recipe[] getSamplePersons() {
        return new Recipe[] {
            new Recipe(new Name("Alex Yeoh"), new Ingredient("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), new Step("HELLO")),
            new Recipe(new Name("Bernice Yu"), new Ingredient("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), new Step("Goodbye")),
        };
    }

    public static ReadOnlyRecipeBook getSampleAddressBook() {
        RecipeBook sampleAb = new RecipeBook();
        for (Recipe sampleRecipe : getSamplePersons()) {
            sampleAb.addRecipe(sampleRecipe);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
