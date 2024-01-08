package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;


public class Differ {
    public static Map<String, Object> toMap(String key, String status, Object oldValue, Object newValue) {
        var res = new TreeMap<String, Object>();
        res.put("FIELD", key);
        res.put("STATUS", status);
        res.put("OLD_VALUE", oldValue);
        res.put("NEW_VALUE", newValue);
        return res;
    }

    public static String generate(Map<String, Object> data1, Map<String, Object> data2, String style) {
        Set<String> keys = new TreeSet<>(data1.keySet());
        List<Map<String, Object>> result = new ArrayList<>();
        keys.addAll(data2.keySet());
        keys.forEach(key -> {
            var v1 = data1.get(key);
            var v2 = data2.get(key);
            if (!data1.containsKey(key)) {
                result.add(toMap(key, "ADDED", v1, v2));
            } else if (!data2.containsKey(key)) {
                result.add(toMap(key, "REMOVED", v1, v2));
            } else if (v1 == null || !v1.equals(v2)) {
                result.add(toMap(key, "UPDATED", v1, v2));
            } else {
                result.add(toMap(key, "SAME", v1, v2));
            }
        });
        var genDiff = style.equals("stylish") ? new StylishFormatter() : new PlainFormatter();
        return genDiff.format(result);
    }
}
