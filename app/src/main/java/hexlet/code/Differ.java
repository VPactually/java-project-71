package hexlet.code;

import java.util.Map;
import java.util.TreeSet;

public class Differ {
    public static String generate(Map<String, String> data1, Map<String, String> data2) {
        StringBuilder sb = new StringBuilder();
        var set = new TreeSet<>(data1.keySet());
        set.addAll(data2.keySet());
        sb.append("{\n");
        for (var key : set) {
            if (!data1.containsKey(key)) {
                sb.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            } else if (!data2.containsKey(key)) {
                sb.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
            } else if (data1.get(key).equals(data2.get(key))) {
                sb.append("    ").append(key).append(": ").append(data1.get(key)).append("\n");
            } else {
                sb.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
                sb.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
