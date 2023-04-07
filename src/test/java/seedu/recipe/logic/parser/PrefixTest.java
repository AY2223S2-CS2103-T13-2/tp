package seedu.recipe.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class PrefixTest {
    @Test
    public void equals() {
        Prefix aaa = new Prefix("aaa");
        //Referential
        assertEquals(aaa, aaa);
        //Semantic
        assertEquals(aaa, new Prefix("aaa"));
        //Different Type
        assertNotEquals(aaa, "aaa");
        //Different
        assertNotEquals(aaa, new Prefix("aab"));
    }

    @Test
    public void hashCode_called_generatesProperHashCode() {
        assertEquals(new Prefix(null).hashCode(), 0);
        String testPrefix = "p/";
        assertEquals(new Prefix(testPrefix).hashCode(), testPrefix.hashCode());
    }
}
