package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    private static void stylishFormat(StringBuilder sb, String key, String indicator, Object value) {
        sb.append(String.format("  %s %s: %s\n", indicator, key, value));
    }

    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        StringBuilder sb = new StringBuilder();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());
        sb.append("{\n");
        keys.forEach(key -> {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);
            if (!data1.containsKey(key)) {
                stylishFormat(sb, key, "+", value2);
            } else if (!data2.containsKey(key)) {
                stylishFormat(sb, key, "-", value1);
            } else if (value1 == null || !value1.equals(value2)) {
                stylishFormat(sb, key, "-", value1);
                stylishFormat(sb, key, "+", value2);
            } else {
                stylishFormat(sb, key, " ", value1);
            }
        });
        sb.append("}");
        return sb.toString();
    }
}
