package hexlet.code.formatters.plainFormatter;

import hexlet.code.formatters.DifferFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlainFormatter implements DifferFormatter {
//    public static void plainFormatter(StringBuilder sb, String key, Object oldValue, Object newValue) {
//        return sb.append(String.format("Property '%s' "))
//    }

    public String valid(Object val, ArrayList<Object> classes) {
        if (val == null) {
            return null;
        }
        val = val.getClass().equals(String.class)
                ? String.format("'%s'", val)
                : val;
        val = classes.contains(val.getClass()) ? val : "[complex value]";
        return val.toString();
    }

    @Override
    public String format(List<Map<String, Object>> result) {
        var sb = new StringBuilder();
        var classes = new ArrayList<Object>(List.of(Integer.class, Boolean.class, String.class));
        classes.add(null);
        result.forEach(map -> {
            var key = map.get("FIELD").toString();
            var oldVal = valid(map.get("OLD_VALUE"), classes);
            var newVal = valid(map.get("NEW_VALUE"), classes);
            switch (map.get("STATUS").toString()) {
                case "ADDED":
                    sb.append(String.format("\nProperty '%s' was added with value: %s", key, newVal));
                    break;
                case "UPDATED":
                    sb.append(String.format("Property '%s' was updated. From %s to %s\n", key, oldVal, newVal));
                    break;
                case "REMOVED":
                    sb.append(String.format("\nProperty '%s' was removed", key));
                    break;
                case "SAME":
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported status: " + map.get("STATUS"));
            }
        });
        return sb.toString();
    }
}
