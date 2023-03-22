package seedu.recipe.model.recipe.ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
     * Parses an IngredientBuilder instance for its arguments.
     *
     * @param args The raw IngredientBuilder instance.
     */
    public static void parse(String args) {
        List<PrefixPosition> positions = findAllPositions(args,
                AMOUNT_PREFIX, COMMON_NAME_PREFIX,
                ESTIMATE_PREFIX, NAME_PREFIX, REMARK_PREFIX, SUBSTITUTION_PREFIX);
        positions.sort(Comparator.comparingInt(p -> p.startPos));
        HashMap<Prefix, List<String>> out = new HashMap<>();
        positions.add(new PrefixPosition(new Prefix(""), args.length()));
        for (int i = 0; i < positions.size() - 1; i++) {
            PrefixPosition p = positions.get(i);
            String arg = args.substring(p.startPos + p.getPrefix().getPrefix().length() + 1,
                    positions.get(i + 1).startPos).trim();
            if (!out.containsKey(p.getPrefix())) {
                List<String> argList = List.of(arg);
                out.put(p.getPrefix(), argList);
            } else {
                List<String> argList = new ArrayList<>(out.get(p.getPrefix()));
                argList.add(arg);
                out.replace(p.getPrefix(), argList);
            }
        }
    }

    private static List<PrefixPosition> findAllPositions(String args, Prefix... prefixes) {
        return Arrays.stream(prefixes)
                .flatMap(pfx -> findPosition(args, pfx).stream())
                .collect(Collectors.toList());
    }

    private static List<PrefixPosition> findPosition(String args, Prefix prefix) {
        List<PrefixPosition> positions = new ArrayList<>();
        int startPos = args.indexOf(prefix.getPrefix());
        while (startPos != -1) {
            PrefixPosition position = new PrefixPosition(prefix, startPos);
            positions.add(position);
            startPos = args.indexOf(prefix.getPrefix(), startPos + prefix.getPrefix().length() + 1);
        }
        return positions;
    }

    private static class PrefixPosition {
        private final Prefix prefix;
        private final int startPos;
        PrefixPosition(Prefix prefix, int startPos) {
            this.prefix = prefix;
            this.startPos = startPos;
        }

        int getStartPosition() {
            return startPos;
        }

        Prefix getPrefix() {
            return prefix;
        }

        @Override
        public String toString() {
            return "<" + startPos + ">[" + prefix.getPrefix() + "]";
        }
    }
}
