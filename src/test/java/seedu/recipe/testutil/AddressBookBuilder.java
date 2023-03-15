package seedu.recipe.testutil;

import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.recipe.Recipe;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private RecipeBook addressBook;

    public AddressBookBuilder() {
        addressBook = new RecipeBook();
    }

    public AddressBookBuilder(RecipeBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Recipe} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Recipe recipe) {
        addressBook.addRecipe(recipe);
        return this;
    }

    public RecipeBook build() {
        return addressBook;
    }
}
