package hexlet.code.formatters.stylishFormatter;

import hexlet.code.formatters.DifferFormatter;

import java.util.List;
import java.util.Map;

public final class StylishFormatter implements DifferFormatter {
    public static void stylishFormat(StringBuilder sb, String key, String indicator, Object value) {
        sb.append(String.format("  %s %s: %s\n", indicator, key, value));
    }

    @Override
    public String format(List<Map<String, Object>> result) {
        var sb = new StringBuilder("{\n");
        result.forEach(map -> map.forEach((key, value) -> {
            if (!key.equals("STATUS")) {
                return;
            }
            switch (value.toString()) {
                case "ADDED":
                    stylishFormat(sb, map.get("FIELD").toString(), "+", map.get("NEW_VALUE"));
                    break;
                case "UPDATED":
                    stylishFormat(sb, map.get("FIELD").toString(), "-", map.get("OLD_VALUE"));
                    stylishFormat(sb, map.get("FIELD").toString(), "+", map.get("NEW_VALUE"));
                    break;
                case "REMOVED":
                    stylishFormat(sb, map.get("FIELD").toString(), "-", map.get("OLD_VALUE"));
                    break;
                case "SAME":
                    stylishFormat(sb, map.get("FIELD").toString(), " ", map.get("OLD_VALUE"));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported field: " + value);
            }
        }));
        sb.append("}");
        return sb.toString();
    }
}
