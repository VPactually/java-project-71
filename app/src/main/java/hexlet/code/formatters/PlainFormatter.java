package hexlet.code.formatters;


import java.util.List;
import java.util.Map;

public final class PlainFormatter {

    public String valid(Object val) {
        var complexValue = "[complex value]";
        if (val == null) {
            return null;
        } else if (val instanceof Map || val instanceof List) {
            return complexValue;
        } else if (val instanceof String) {
            return String.format("'%s'", val);
        } else {
            return val.toString();
        }
    }

    public String format(List<Map<String, Object>> result) {
        var sb = new StringBuilder();
        result.forEach(map -> {
            var key = map.get("FIELD").toString();
            var oldVal = valid(map.get("OLD_VALUE"));
            var newVal = valid(map.get("NEW_VALUE"));
            switch (map.get("STATUS").toString()) {
                case "ADDED":
                    sb.append(String.format("Property '%s' was added with value: %s\n", key, newVal));
                    break;
                case "UPDATED":
                    sb.append(String.format("Property '%s' was updated. From %s to %s\n", key, oldVal, newVal));
                    break;
                case "REMOVED":
                    sb.append(String.format("Property '%s' was removed\n", key));
                    break;
                case "SAME":
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported status: " + map.get("STATUS"));
            }
        });
        return sb.toString().trim();
    }
}
