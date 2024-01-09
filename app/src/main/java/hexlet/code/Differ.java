package hexlet.code;

import hexlet.code.formatters.jsonFormatter.JsonFormatter;
import hexlet.code.formatters.plainFormatter.PlainFormatter;
import hexlet.code.formatters.stylishFormatter.StylishFormatter;

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

    public static String generate(String filepath1, String filepath2) {
        try {
            return generate(filepath1, filepath2, "stylish");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generate(String filepath1, String filepath2, String style) {
        var data1 = Parser.parse(filepath1);
        var data2 = Parser.parse(filepath2);
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
        switch (style) {
            case "plain":
                return new PlainFormatter().format(result);
            case "json":
                return new JsonFormatter().format(result);
            default:
                return new StylishFormatter().format(result);
        }
    }
}
