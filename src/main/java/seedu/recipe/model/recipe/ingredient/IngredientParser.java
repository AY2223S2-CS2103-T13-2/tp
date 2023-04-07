package seedu.recipe.model.recipe.ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import seedu.recipe.logic.parser.Prefix;

/**
 * Represents a parser that inspects an {@code IngredientBuilder} instance for
 * the relevant fields, and separates them into their respective categories.
 */
public class IngredientParser {
    public static final Prefix AMOUNT_PREFIX = new Prefix("-a");
    public static final Prefix ESTIMATE_PREFIX = new Prefix("-e");

    public static final Prefix COMMON_NAME_PREFIX = new Prefix("-cn");
    public static final Prefix NAME_PREFIX = new Prefix("-n");
    public static final Prefix SUBSTITUTION_PREFIX = new Prefix("-s");

    public static final Prefix REMARK_PREFIX = new Prefix("-r");

    /**
     * Parses a command string for its arguments.
     *
     * @param args The raw ingredient command string.
     * @return A key-value pair map, that maps prefixes to their tokens within the command
     *         string.
     */
    public static HashMap<Prefix, List<String>> parse(String args) {
        assert args != null;
        List<PrefixPosition> positions = findAllPositions(args,
                AMOUNT_PREFIX, COMMON_NAME_PREFIX,
                ESTIMATE_PREFIX, NAME_PREFIX, REMARK_PREFIX, SUBSTITUTION_PREFIX);
        return extractAllArguments(args, positions);
    }

    private static List<PrefixPosition> findAllPositions(String args, Prefix... prefixes) {
        return Arrays.stream(prefixes)
                .flatMap(pfx -> findPosition(args, pfx).stream())
                .collect(Collectors.toList());
    }

    private static List<PrefixPosition> findPosition(String args, Prefix prefix) {
        List<PrefixPosition> positions = new ArrayList<>();
        int startPos = args.indexOf(prefix.getPrefix() + " ");
        while (startPos != -1) {
            PrefixPosition position = new PrefixPosition(prefix, startPos);
            positions.add(position);
            startPos = args.indexOf(prefix.getPrefix(), startPos + prefix.getPrefix().length() + 1);
        }
        return positions;
    }

    private static HashMap<Prefix, List<String>> extractAllArguments(String args, List<PrefixPosition> positions) {
        HashMap<Prefix, List<String>> out = new HashMap<>();

        //Sort positions
        positions.sort(Comparator.comparingInt(p -> p.startPos));

        //Add dummy flag to end of list
        positions.add(new PrefixPosition(new Prefix(""), args.length()));

        Function<PrefixPosition, Integer> getEndPosition = p -> p.prefix.getPrefix().length();

        //Iterate and extract
        for (int i = 0; i < positions.size() - 1; i++) {
            PrefixPosition p = positions.get(i);
            String arg = args.substring(
                    p.startPos + getEndPosition.apply(p) + 1, //start
                    positions.get(i + 1).startPos //end - next prefix's start
            ).trim();

            //Set or add if list exists
            if (!out.containsKey(p.prefix)) { //Set
                List<String> argList = List.of(arg);
                out.put(p.prefix, argList);
            } else { //Add to existing list
                List<String> argList = new ArrayList<>(out.get(p.prefix));
                argList.add(arg);
                out.replace(p.prefix, argList);
            }
        }
        return out;
    }

    static class PrefixPosition {
        private final Prefix prefix;
        private final int startPos;

        PrefixPosition(Prefix prefix, int startPos) {
            this.prefix = prefix;
            this.startPos = startPos;
        }

        @Override
        public String toString() {
            return "<" + startPos + ">[" + prefix.getPrefix() + "]";
        }
    }
}
