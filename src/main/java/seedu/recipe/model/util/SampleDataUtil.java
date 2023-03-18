package seedu.recipe.model.util;

import seedu.recipe.model.ReadOnlyRecipeBook;
import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.RecipeDuration;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.tag.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contains utility methods for populating {@code RecipeBook} with sample data.
 */
public class SampleDataUtil {
    public static Recipe[] getSampleRecipes() {
        Name CACIO_NAME = new Name("Cacio e Pepe");
        RecipePortion CACIO_PORTION = RecipePortion.of("1 - 2 servings");
        RecipeDuration CACIO_DURATION = RecipeDuration.of("15 minutes");
        Set<Tag> CACIO_TAGS = Set.of(new Tag("Italian"));
        List<Ingredient> CACIO_INGREDIENTS = List.of(
                new Ingredient("Kosher salt"),
                new Ingredient("6 oz pasta (such as egg tagliolini, bucatini, or spaghetti)"),
                new Ingredient("3 Tbsp unsalted butter, cubed, divided"),
                new Ingredient("1 tsp freshly cracked black pepper"),
                new Ingredient("0.75 cup finely grated Grana Padano or Parmesan"),
                new Ingredient("1/3 cup finely grated Pecorino")
        );
        List<Step> CACIO_STEPS = List.of(
                new Step("Bring 3 quarts water to a boil in a 5-qt. pot. "
                        + "Season with salt; add pasta and cook, stirring occasionally, "
                        + "until about 2 minutes before tender. Drain, reserving 3/4 cup "
                        + "pasta cooking water."),
                new Step("Meanwhile, melt 2 Tbsp. butter in a Dutch oven or other large pot "
                        + "or skillet over medium heat. Add pepper and cook, swirling pan, "
                        + "until toasted, about 1 minute."),
                new Step("Add 0.5 cup reserved pasta water to skillet and bring to a simmer. "
                        + "Add pasta and remaining butter. Reduce heat to low and add Grana Padano, "
                        + "stirring and tossing with tongs until melted. Remove pan from heat; add "
                        + "Pecorino, stirring and tossing until cheese melts, sauce coats the pasta, "
                        + "and pasta is al dente. (Add more pasta water if sauce seems dry.) Transfer "
                        + "pasta to warm bowls and serve."));
        Recipe cacioEPepePasta = new Recipe(CACIO_NAME);
        cacioEPepePasta.setDuration(CACIO_DURATION);
        cacioEPepePasta.setPortion(CACIO_PORTION);
        cacioEPepePasta.setTags(CACIO_TAGS.toArray(Tag[]::new));
        cacioEPepePasta.setIngredients(CACIO_INGREDIENTS.toArray(Ingredient[]::new));
        cacioEPepePasta.setSteps(CACIO_STEPS.toArray(Step[]::new));

        return new Recipe[] {
                cacioEPepePasta
        };
    }

    public static ReadOnlyRecipeBook getSampleRecipeBook() {
        RecipeBook sampleAb = new RecipeBook();
        for (Recipe sampleRecipe : getSampleRecipes()) {
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
