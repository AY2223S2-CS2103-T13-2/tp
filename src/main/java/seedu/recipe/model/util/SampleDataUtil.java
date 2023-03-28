package seedu.recipe.model.util;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.recipe.model.ReadOnlyRecipeBook;
import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.recipe.Name;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.RecipeDuration;
import seedu.recipe.model.recipe.RecipePortion;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.model.tag.Tag;

/**
 * Contains utility methods for populating {@code RecipeBook} with sample data.
 */
public class SampleDataUtil {
    public static final Name CACIO_NAME = new Name("Cacio e Pepe");
    public static final RecipePortion CACIO_PORTION = RecipePortion.of("1 - 2 servings");
    public static final RecipeDuration CACIO_DURATION = RecipeDuration.of("15 minutes");
    public static final Set<Tag> CACIO_TAGS = Set.of(new Tag("Italian"));
    public static final List<IngredientBuilder> CACIO_INGREDIENTS = List.of(
            new IngredientBuilder("-n Kosher salt"),
            new IngredientBuilder("-a 6 oz -n egg tagliolini -s bucatini -s spaghetti"),
            new IngredientBuilder("-a 3 Tbsp -n unsalted butter -r cubed -r divided"),
            new IngredientBuilder("-a 1 tsp -n black pepper -r freshly cracked"),
            new IngredientBuilder("-a 3/4 cup -n Grana Padano -s Parmesan -r finely grated"),
            new IngredientBuilder("-a 1/3 cup -n Pecorino -r finely grated"));
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

    public static final Recipe CACIO_E_PEPE = build(
            CACIO_NAME, CACIO_PORTION, CACIO_DURATION, CACIO_TAGS,
            CACIO_INGREDIENTS, CACIO_STEPS);

    // Manually added
    public static final Recipe BLUEBERRY_PANCAKES = build(
            new Name("American blueberry pancakes"),
            RecipePortion.of("2 - 3 servings"),
            RecipeDuration.of("35 minutes"),
            Set.of(new Tag("American"), new Tag("Breakfast")),
            List.of(new IngredientBuilder("-a 200 g -n self-raising flour"),
                    new IngredientBuilder("-a 1 tsp -n baking powder"),
                    new IngredientBuilder("-a 1 -n egg"),
                    new IngredientBuilder("-a 1 knob -n butter"),
                    new IngredientBuilder("-a 150 g -n blueberries"),
                    new IngredientBuilder("-n golden syrup -s maple syrup"),
                    new IngredientBuilder("-n sunflower oil -s butter -r a little")),
            List.of(new Step("Mix together 200 g self-raising flour, 1 tsp baking powder and a "
                            + "pinch of salt in a large bowl."),
                    new Step("Beat 1 egg with 300ml milk, make a well in the centre of the dry ingredients "
                            + "and whisk in the milk to make a thick smooth batter."),
                    new Step("Beat in a knob of melted butter, and gently stir in half of the 150g pack of "
                            + "blueberries."),
                    new Step("Heat a teaspoon of sunflower oil or small knob of butter in a "
                            + "large non-stick frying pan."),
                    new Step("Drop a large tablespoonful of the batter per pancake into the pan to make "
                            + "pancakes about 7.5cm across. Make three or four pancakes at a time."),
                    new Step("Cook for about 3 minutes over a medium heat until small bubbles appear on the "
                            + "surface of each pancake, then turn and cook another 2-3 minutes until golden."),
                    new Step("Cover with kitchen paper to keep warm while you use up the rest of the batter."),
                    new Step("Serve with golden or maple syrup and the rest of the blueberries.")));

    public static final Recipe MASALA_DOSA = build(
            new Name("Classic Masala Dosa"),
            RecipePortion.of("8 to 10 servings"),
            RecipeDuration.of("1 hour"),
            Set.of(new Tag("Indian")),
            List.of(new IngredientBuilder("-a 2 cups -n short-grain rice"),
                    new IngredientBuilder("-a 1/2 cup -n urad dal -cn split husked black lentils"),
                    new IngredientBuilder("-a 1 teaspoon -n fenugreek seeds"),
                    new IngredientBuilder("-a 1/2 teaspoon -n salt"),
                    new IngredientBuilder("-n Vegetable oil -r for frying"),
                    new IngredientBuilder("-a 3 tablespoons -n ghee -s vegetable oil"),
                    new IngredientBuilder("-a 1 teaspoon -n mustard seeds"),
                    new IngredientBuilder("-a 1/2 teaspoon -n cumin seeds"),
                    new IngredientBuilder("-a 2 -n red peppers -r small -r dried -r hot"),
                    new IngredientBuilder("-a 1 -n onion -r medium -r diced"),
                    new IngredientBuilder("-a 1/2 teaspoon -n salt"),
                    new IngredientBuilder("-a 1/2 teaspoon -n turmeric"),
                    new IngredientBuilder("-a a pinch -n asafetida"),
                    new IngredientBuilder("-a 1 tablespoon -n ginger -r grated"),
                    new IngredientBuilder("-a 6 to 8 -n curry leaves"),
                    new IngredientBuilder("-a 4 -n garlic cloves -r minced"),
                    new IngredientBuilder("-a 2 -n small green chilli -r finely chopped"),
                    new IngredientBuilder("-a 1.5 pounds -n Yukon Gold potatoes "
                            + "-s yellow-fleshed potatoes -r boiled -r peeled -r cubed"),
                    new IngredientBuilder("-a 1/2 cup -n cilantro -r leaves -r tender stems -r roughly chopped")),
            List.of(
                    new Step("Make the dosa batter: Put rice in a bowl, rinse well and cover with "
                            + "4 cups cold water. Put urad dal and fenugreek seeds in a small bowl, rinse "
                            + "well and add cold water to cover. Leave both to soak for 4 to 6 hours."),
                    new Step("Drain rice and dal-fenugreek mixture in separate colanders. Put rice "
                            + "in a food processor, blender or wet-dry grinder. Add 1 cup cold water and "
                            + "grind to a smooth paste. It will take about 10 minutes, and it may be necessary"
                            + " to work in batches. Repeat the process with the dal-fenugreek mixture."),
                    new Step("Combine the two pastes in a medium mixing bowl. Whisk together, adding "
                            + "enough water to obtain a medium-thick batter. You should have about 6 cups. "
                            + "Cover bowl with a kitchen towel and set in a warm place. Let ferment until the "
                            + "surface is bubbly, about 8 hours. Stir in the salt. Use the batter straight "
                            + "away or refrigerate for later use. (Batter will keep for up to a week, "
                            + "refrigerated. Thin with water if necessary before proceeding.)"),
                    new Step("Make the potato filling: Put ghee in a wide skillet over medium heat. "
                            + "When oil is wavy, add mustard seeds and cumin seeds. Wait for seeds to pop, "
                            + "about 1 minute, then add red peppers and onion. Cook, stirring until onions "
                            + "have softened, about 5 minutes. Season lightly with salt. "
                            + "Add turmeric, asafetida, ginger,curry leaves, garlic and green chile. "
                            + "Stir to coat and let sizzle for 1 minute."),
                    new Step("Add potatoes and 0.5 cup water. Cook, stirring well to combine, until "
                            + "liquid has evaporated, about 5 minutes. Mash potatoes a bit with the back of "
                            + "a wooden spoon. Season well with salt, add cilantro, then set aside at room"
                            + "temperature. "
                            + "(Potato filling may be prepared up to a day in advance.)"),
                    new Step("To make dosas, set a griddle or cast-iron skillet over medium heat. "
                            + "Brush with about 1 teaspoon vegetable oil. Ladle 1/4 cup batter in the center "
                            + "of griddle. Using bottom of ladle, quickly spread batter outward in a "
                            + "circular motion to a diameter of about 7 inches. Drizzle 0.5 teaspoon oil "
                            + "over the top. Leave dosa batter to brown gradually until outer edges begin to look dry, "
                            + "about 2 minutes, cooking on one side only. With a spatula, carefully loosen "
                            + "dosa from griddle. Bottom should be crisp and beautifully browned. Spoon 0.5 cup "
                            + "potato filling onto top of dosa, centering it as a"
                            + " strip in the middle of the round dosa. Flatten the potato mixture slightly. "
                            + "Using the spatula, fold the sides of the dosa around the filling to make a "
                            + "cylindrical shape. Serve immediately. Continue making dosas one at a time.")));

    public static final Recipe GRILLED_CHEESE = build(
            new Name("Pan-fried camembert sandwich"),
            RecipePortion.of("1 portion"),
            RecipeDuration.of("4 min"),
            Set.of(new Tag("English"), new Tag("Comfort food")),
            List.of(new IngredientBuilder("-a 2 thick slices -n white bread"),
                    new IngredientBuilder("-a a wedge -e 85g/3oz -n camembert -s brie"),
                    new IngredientBuilder("-a 1 tbsp -n cranberry sauce"),
                    new IngredientBuilder("-a a few drops -n balsamic vinegar"),
                    new IngredientBuilder("-n butter")),
            List.of(
                    new Step("Butter the bread. Put a wedge of camembert or brie on the unbuttered side of "
                            + "one slice of bread. Top with a spoonful of cranberry sauce. Drizzle with a "
                            + "few drops of balsamic vinegar, if you have some."),
                    new Step("Put the second slice of bread on top, buttered side out. Fry in a hot non-stick "
                            + "pan, pressing down with a fish slice, for a minute or two on each side, until "
                            + "golden brown and melting. Cut in half and eat straightaway.")));

    public static final Recipe FISH_AND_CHIPS = build(
            new Name("Fish and Chips"),
            RecipePortion.of("1 - 2 servings"),
            RecipeDuration.of("10 minutes"),
            Set.of(new Tag("English"), new Tag("Comfort food")),
            List.of(new IngredientBuilder("-a 120 Grams -n Self Rising Flour -r +additional to coat"),
                    new IngredientBuilder("-a 175 Grams -n Cod -s White Fleshed Fish"),
                    new IngredientBuilder("-a 1 Medium -n Egg White"),
                    new IngredientBuilder("-a 160 ML -e 0.5 cup -e little under half a bottle -n Light Beer -s Lager"),
                    new IngredientBuilder("-a 1 -n Potato -r Large -r Waxy -r Peeled"),
                    new IngredientBuilder("-n Sunflower Oil"),
                    new IngredientBuilder("-n Salt"),
                    new IngredientBuilder("-a 1 Teaspoon -n Curry Powder -r used twice"),
                    new IngredientBuilder("-a 1 Teaspoon -n Baking Powder"),
                    new IngredientBuilder("-a 1 Tablespoon -n Gherkins -r chopped"),
                    new IngredientBuilder("-a 50 Grams -n Creme Fraiche"),
                    new IngredientBuilder("-a 200 Grams -n Mayonnaise"),
                    new IngredientBuilder("-a 3/4 -n Shallot -r Diced"),
                    new IngredientBuilder("-a 1 tsp -n Lemon Juice"),
                    new IngredientBuilder("-n Hot Sauce -r Optional")),
            List.of(
                    new Step("Get oil in pan medium-high heat for frying before assembling ingredients. "
                            + "Do not need a deep pan or pot, a large pan will do"),
                    new Step("In a bowl whisk together flour, baking soda, curry powder and beer. Then whisk "
                            + "egg whites till there are stiff peaks and fold into batter (if too heavy add "
                            + "some water)"),
                    new Step("Add teaspoon of curry powder to dredging flour for more seasoning (optional)"),
                    new Step("Season fish with salt, then coat with flour. Knock off excess flour and put into "
                            + "batter mixture. Make sure fish is fully battered and ad to oil"),
                    new Step("Once fish is in, baste the fish with oil. Let first side cook until golden "
                            + "brown and flip. Basting fish with oil on other side. Take pan on and off oil "
                            + "so that the oil does not get too hot. Fish should be in oil 3-3.5 minutes. "
                            + "Once finished put on plate with paper towel and put in warm oven"),
                    new Step("Chop potato into square, then chop into tall skinny fries (the skinnier the fry "
                            + "the quicker they will cook). Then roll in paper towel to dry any excess "
                            + "moisture. Add new oil to pain then add potatoes to high heat. Once fries are browned, "
                            + "remove from oil on to paper towel and add salt."),
                    new Step("Add all Tartar ingredients together and mix. Add salt to taste and hot sauce "
                            + "if you want heat."),
                    new Step("Assemble together and enjoy!")));

    public static Recipe[] getSampleRecipes() {
        return new Recipe[]{
            CACIO_E_PEPE, BLUEBERRY_PANCAKES, FISH_AND_CHIPS, GRILLED_CHEESE, MASALA_DOSA
        };
    }

    public static ReadOnlyRecipeBook getSampleRecipeBook() {
        RecipeBook sampleRb = new RecipeBook();
        for (Recipe sampleRecipe : getSampleRecipes()) {
            sampleRb.addRecipe(sampleRecipe);
        }
        return sampleRb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }

    private static Recipe build(
            Name name, RecipePortion portion, RecipeDuration duration,
            Set<Tag> tags, List<IngredientBuilder> ingredients, List<Step> steps) {
        requireNonNull(name);
        requireNonNull(portion);
        requireNonNull(duration);
        requireNonNull(tags);
        requireNonNull(ingredients);
        requireNonNull(steps);
        Recipe out = new Recipe(name);
        out.setPortion(portion);
        out.setDuration(duration);
        out.setTags(tags.toArray(Tag[]::new));
        out.setIngredients(ingredients.toArray(IngredientBuilder[]::new));
        out.setSteps(steps.toArray(Step[]::new));
        return out;
    }
}
