package seedu.recipe.model.recipe.ingredient;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a container for the values stored with respect to an Ingredient,
 * within a Recipe instance.
 */
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class IngredientInformation {
    private final Optional<IngredientQuantity> quantity;
    private final Optional<String> estimatedQuantity;
    private final List<String> remarks;
    private final List<Ingredient> substitutions;

    /**
     * Given the various {@code Recipe}-specific fields associated with an
     * {@code Ingredient} instance, generates a container for all the
     * relevant fields associated with that {@code Ingredient}, and returns
     * the container instance.
     * @param quantity The Ingredient quantity, if present.
     * @param estimatedQuantity The estimated quantity amounts, if present.
     * @param remarks Any remarks about the preparation of this ingredient or its specifics
     * @param substitutions Any possible ingredient substitutions.
     */
    public IngredientInformation(
            IngredientQuantity quantity,
            String estimatedQuantity,
            String[] remarks,
            Ingredient[] substitutions
    ) {
        this.quantity = Optional.ofNullable(quantity);

        //Ensure non-empty
        this.estimatedQuantity = Optional.ofNullable(estimatedQuantity)
                .filter(s -> s.matches("^\\S+(\\s+\\S+)*$"));

        //List::of is unmodifiable by default
        List<String> remarkList = List.of(remarks);
        remarkList = remarkList.stream()
                .filter(r -> r != null && r.matches("^\\S+(\\s+\\S+)*$"))
                .collect(Collectors.toList());
        this.remarks = remarkList;

        //Validated by Ingredient
        List<Ingredient> substituteIngredients = List.of(substitutions);
        substituteIngredients.forEach(Objects::requireNonNull);

        this.substitutions = substituteIngredients;
    }

    public Optional<IngredientQuantity> getQuantity() {
        return this.quantity;
    }

    public Optional<String> getEstimatedQuantity() {
        return this.estimatedQuantity;
    }

    public List<String> getRemarks() {
        return this.remarks;
    }

    public List<Ingredient> getSubstitutions() {
        return this.substitutions;
    }

    @Override
    public String toString() {
        return String.format("{Q: %s; E: %s; S: %s; R: %s}",
            quantity.map(v -> "" + v).orElse("<>"),
            estimatedQuantity.map(v -> "" + v).orElse("<>"),
            substitutions,
            remarks
        );
    }

    @Override
    public boolean equals(Object o) {
        return o == this
            || o instanceof IngredientInformation
            && ((IngredientInformation) o).quantity.equals(this.quantity)
            && ((IngredientInformation) o).estimatedQuantity.equals(this.estimatedQuantity)
            && ((IngredientInformation) o).remarks.equals(this.remarks)
            && ((IngredientInformation) o).substitutions.equals(this.substitutions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, estimatedQuantity, remarks, substitutions);
    }
}
