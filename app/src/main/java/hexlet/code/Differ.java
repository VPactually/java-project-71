package hexlet.code;

import java.util.Map;
import java.util.TreeSet;

public class Differ {
    public static StringBuilder stylishFormat(StringBuilder sb, String key, String symbol, Object value) {
        return sb.append("  ").append(symbol).append(' ').append(key).append(": ").append(value).append("\n");
    }

    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        StringBuilder sb = new StringBuilder();
        var set = new TreeSet<>(data1.keySet());
        set.addAll(data2.keySet());
        sb.append("{\n");
        for (var key : set) {
            if (!data1.containsKey(key)) {
                sb = stylishFormat(sb, key, "+", data2.get(key));
            } else if (!data2.containsKey(key)) {
                sb = stylishFormat(sb, key, "-", data1.get(key));
            } else if (data1.get(key) == null || !data1.get(key).equals(data2.get(key))) {
                sb = stylishFormat(sb, key, "-", data1.get(key));
                sb = stylishFormat(sb, key, "+", data2.get(key));
            } else {
                sb = stylishFormat(sb, key, " ", data1.get(key));
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
