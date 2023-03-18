package seedu.recipe.testutil;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.RecipeDuration;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.tag.Tag;

/**
 * A utility class containing a list of {@code Recipe} objects to be used in tests.
 */
public class TypicalRecipes {
    //Individual Fields
    public static final Name CACIO_NAME = new Name("Cacio e Pepe");
    public static final RecipePortion CACIO_PORTION = RecipePortion.of("1 - 2 servings");
    public static final RecipeDuration CACIO_DURATION = RecipeDuration.of("15 minutes");
    public static final Set<Tag> CACIO_TAGS = Set.of(new Tag("Italian"));
    public static final List<Ingredient> CACIO_INGREDIENTS = List.of(
        new Ingredient("Kosher salt"),
        new Ingredient("6 oz pasta (such as egg tagliolini, bucatini, or spaghetti)"),
        new Ingredient("3 Tbsp unsalted butter, cubed, divided"),
        new Ingredient("1 tsp freshly cracked black pepper"),
        new Ingredient("3/4 cup finely grated Grana Padano or Parmesan"),
        new Ingredient("1/3 cup finely grated Pecorino")
    );
    public static final List<Step> CACIO_STEPS = List.of(
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

    public static final String CACIO_STRING = String.format(
            "%s;\nPortion: %s;\nDuration: %s;\nTags: %s;\n"
                    + "Ingredients: %s;\nSteps: %s",
            CACIO_NAME, CACIO_PORTION, CACIO_DURATION, (
                (Supplier<String>) () -> {
                    StringBuilder out = new StringBuilder();
                    for (Tag tag: CACIO_TAGS) {
                        out.append(tag.toString());
                    }
                    return out.toString();
                }).get(), (
                (Supplier<String>) () -> {
                    StringBuilder out = new StringBuilder();
                    for (Ingredient i: CACIO_INGREDIENTS) {
                        out.append(i.toString())
                                .append(",\n");
                    }
                    return out.toString();
                }).get(), (
                (Supplier<String>) () -> {
                    StringBuilder out = new StringBuilder();
                    for (int i = 0; i < CACIO_STEPS.size(); i++) {
                        out.append(i + 1)
                                .append(". ")
                                .append(CACIO_STEPS.get(i).toString())
                                .append(",\n");
                    }
                    return out.toString();
                }).get()
    );

    public static final Recipe CACIO_E_PEPE = new RecipeBuilder(
            CACIO_NAME, CACIO_PORTION, CACIO_DURATION, CACIO_TAGS,
            CACIO_INGREDIENTS, CACIO_STEPS).build();

    // Manually added
    public static final Recipe BLUEBERRY_PANCAKES = new RecipeBuilder(
            new Name("American blueberry pancakes"),
            RecipePortion.of("2 - 3 servings"),
            RecipeDuration.of("35 minutes"),
            Set.of(new Tag("American"), new Tag("Breakfast")),
            List.of(
                new Ingredient("200 g self-raising flour"),
                new Ingredient("1 tsp baking powder"),
                new Ingredient("1 egg"),
                new Ingredient("1 knob butter"),
                new Ingredient("150 g pack blueberry"),
                new Ingredient("golden or maple syrup"),
                new Ingredient("sunflower oil or a little butter")),
            List.of(
                new Step("Mix together 200 g self-raising flour, 1 tsp baking powder and a "
                        + "pinch of salt in a large bowl."),
                new Step("Beat 1 egg with 300ml milk, make a well in the centre of the dry ingredients and whisk "
                        + "in the milk to make a thick smooth batter."),
                new Step("Beat in a knob of melted butter, and gently stir in half of the 150g pack of "
                        + "blueberries."),
                new Step("Heat a teaspoon of sunflower oil or small knob of butter in a large non-stick frying"
                        + "pan."),
                new Step("Drop a large tablespoonful of the batter per pancake into the pan to make pancakes about"
                        + " 7.5cm across. Make three or four pancakes at a time."),
                new Step("Cook for about 3 minutes over a medium heat until small bubbles appear on the surface of"
                        + "each pancake, then turn and cook another 2-3 minutes until golden."),
                new Step("Cover with kitchen paper to keep warm while you use up the rest of the batter."),
                new Step("Serve with golden or maple syrup and the rest of the blueberries.")
            )).build();
    public static final Recipe MASALA_DOSA = new RecipeBuilder(
            new Name("Classic Masala Dosa"),
            RecipePortion.of("8 to 10 servings"),
            RecipeDuration.of("1 hour"),
            Set.of(new Tag("Indian")),
            List.of(new Ingredient("2 cups short-grain rice"),
                new Ingredient("0.5 cup urad dal (split husked black lentils)"),
                new Ingredient("1 teaspoon fenugreek seeds"),
                new Ingredient("0.5 teaspoon salt"),
                new Ingredient("Vegetable oil, for frying"),
                new Ingredient("3 tablespoons ghee or vegetable oil"),
                new Ingredient("1 teaspoon mustard seeds"),
                new Ingredient("0.5 teaspoon cumin seeds"),
                new Ingredient("2 small dried hot red peppers"),
                new Ingredient("1 medium onion, diced"),
                new Ingredient("0.5 teaspoon salt"),
                new Ingredient("0.5 teaspoon turmeric"),
                new Ingredient("Pinch of asafetida"),
                new Ingredient("1 tablespoon grated ginger"),
                new Ingredient("6 to 8 curry leaves"),
                new Ingredient("4 garlic cloves, minced"),
                new Ingredient("2 small green chiles, finely chopped"),
                new Ingredient("1.5 pounds yellow-fleshed potatoes, such as Yukon Gold, boiled, peeled and cubed"),
                new Ingredient("0.5 cup roughly chopped cilantro, leaves and tender stems")),
            List.of(
                new Step("Make the dosa batter: Put rice in a bowl, rinse well and cover with "
                        + "4 cups cold water. Put urad dal and fenugreek seeds in a small bowl, rinse "
                        + "well and add cold water to cover. Leave both to soak for 4 to 6 hours."),
                new Step("Drain rice and dal-fenugreek mixture in separate colanders. Put rice "
                        + "in a food processor, blender or wet-dry grinder. Add 1 cup cold water and "
                        + "grind to a smooth paste. It will take about 10 minutes, and it may be necessary "
                        + "to work in batches. Repeat the process with the dal-fenugreek mixture."),
                new Step("Combine the two pastes in a medium mixing bowl. Whisk together, adding "
                        + "enough water to obtain a medium-thick batter. You should have about 6 cups. "
                        + "Cover bowl with a kitchen towel and set in a warm place. Let ferment until the "
                        + "surface is bubbly, about 8 hours. Stir in the salt. Use the batter straight away "
                        + "or refrigerate for later use. (Batter will keep for up to a week, refrigerated. "
                        + " Thin with water if necessary before proceeding.)"),
                new Step("Make the potato filling: Put ghee in a wide skillet over medium heat. "
                        + "When oil is wavy, add mustard seeds and cumin seeds. Wait for seeds to pop, "
                        + "about 1 minute, then add red peppers and onion. Cook, stirring until onions have "
                        + "softened, about 5 minutes. Season lightly with salt. Add turmeric, asafetida, ginger, "
                        + "curry leaves, garlic and green chile. Stir to coat and let sizzle for 1 minute."),
                new Step("Add potatoes and 0.5 cup water. Cook, stirring well to combine, until "
                        + "liquid has evaporated, about 5 minutes. Mash potatoes a bit with the back of "
                        + "a wooden spoon. Season well with salt, add cilantro, then set aside at room"
                        + "temperature. "
                        + "(Potato filling may be prepared up to a day in advance.)"),
                new Step("To make dosas, set a griddle or cast-iron skillet over medium heat. "
                        + "Brush with about 1 teaspoon vegetable oil. Ladle 1/4 cup batter in the center "
                        + "of griddle. Using bottom of ladle, quickly spread batter outward in a circular motion "
                        + "to a diameter of about 7 inches. Drizzle 0.5 teaspoon oil over the top. Leave dosa batter"
                        + " to brown "
                        + "gradually until outer edges begin to look dry, about 2 minutes, cooking on one side"
                        + " only. With a spatula, carefully loosen dosa from griddle. Bottom should be crisp and "
                        + "beautifully browned. Spoon 0.5 cup potato filling onto top of dosa, centering it as a"
                        + " strip in the middle of the round dosa. Flatten the potato mixture slightly. Using "
                        + "the spatula, fold the sides of the dosa around the filling to make a cylindrical shape."
                        + " Serve immediately. Continue making dosas one at a time."))).build();

    public static final Recipe GRILLED_CHEESE = new RecipeBuilder(
            new Name("Pan-fried camembert sandwich"),
            RecipePortion.of("1 portion"),
            RecipeDuration.of("4 min"),
            Set.of(new Tag("English"), new Tag("Comfort food")),
            List.of(new Ingredient("2 thick slices of white bread"),
                new Ingredient("wedge of camembert or brie (about 85g/3oz)"),
                new Ingredient("a spoonful of cranberry sauce"),
                new Ingredient("a few drops of balsamic vinegar"),
                new Ingredient("butter")
            ),
            List.of(
                new Step("Butter the bread. Put a wedge of camembert or brie on the unbuttered side of one slice of"
                        + " bread. Top with a spoonful of cranberry sauce. Drizzle with a few drops of balsamic"
                        + " vinegar, if you have some."),
                new Step("Put the second slice of bread on top, buttered side out. Fry in a hot non-stick pan,"
                        + " pressing down with a fish slice, for a minute or two on each side, until golden brown"
                        + " and melting. Cut in half and eat straightaway."))).build();

    public static final Recipe FISH_AND_CHIPS = new RecipeBuilder(
            new Name("Fish and Chips"),
            RecipePortion.of("1 - 2 servings"),
            RecipeDuration.of("10 minutes"),
            Set.of(new Tag("English"), new Tag("Comfort food")),
            List.of(new Ingredient("120 Grams Self Rising Flour + additional to coat"),
                new Ingredient("175 Grams White Fleshed Fish (such as Cod)"),
                new Ingredient("1 Medium Egg White"),
                new Ingredient("160 ML (0.5 cup or little under half a bottle) Light Beer or Lager"),
                new Ingredient("1 Large Waxy Potato, Peeled"),
                new Ingredient("Sunflower Oil"),
                new Ingredient("Salt"),
                new Ingredient("1 Teaspoon Curry Powder (used twice)"),
                new Ingredient("1 Teaspoon Baking Powder"),
                new Ingredient("1 Tablespoon of Gherkins, chopped"),
                new Ingredient("50 Grams Creme Fraiche"),
                new Ingredient("200 Grams Mayonnaise"),
                new Ingredient("0.75 Shallot Diced"),
                new Ingredient("Lemon Juice (about 1 Teaspoon)"),
                new Ingredient("Hot Sauce (Optional)")),
            List.of(
                new Step("Get oil in pan medium-high heat for frying before assembling ingredients. Do not need"
                        + " a deep pan or pot, a large pan will do"),
                new Step("In a bowl whisk together flour, baking soda, curry powder and beer. Then whisk egg whites"
                        + " till there are stiff peaks and fold into batter (if too heavy add some water)"),
                new Step("Add teaspoon of curry powder to dredging flour for more seasoning (optional)"),
                new Step("Season fish with salt, then coat with flour. Knock off excess flour and put into batter"
                        + " mixture. Make sure fish is fully battered and ad to oil"),
                new Step("Once fish is in, baste the fish with oil. Let first side cook until golden brown and flip."
                        + " Basting fish with oil on other side. Take pan on and off oil so that the oil does not get"
                        + " too hot. Fish should be in oil 3-3.5 minutes. Once finished put on plate with paper towel"
                        + " and put in warm oven"),
                new Step("Chop potato into square, then chop into tall skinny fries (the skinnier the fry the quicker"
                        + " they will cook). Then roll in paper towel to dry any excess moisture. Add new oil to pain"
                        + " then add potatoes to high heat. Once fries are browned, remove from oil on to paper towel"
                        + " and add salt."),
                new Step("Add all Tartar ingredients together and mix. Add salt to taste and hot sauce if you want"
                        + " heat."),
                new Step("Assemble together and enjoy!"))).build();

    // Manually added - Recipe's details found in {@code CommandTestUtil}
    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalRecipes() {} // prevents instantiation

    /**
     * Returns an {@code RecipeBook} with all the typical recipes.
     */
    public static RecipeBook getTypicalRecipeBook() {
        RecipeBook ab = new RecipeBook();
        for (Recipe recipe : getTypicalRecipes()) {
            ab.addRecipe(recipe);
        }
        return ab;
    }

    public static List<Recipe> getTypicalRecipes() {
        return List.of(
                BLUEBERRY_PANCAKES,
                CACIO_E_PEPE,
                FISH_AND_CHIPS,
                GRILLED_CHEESE,
                MASALA_DOSA
        );
    }
}
