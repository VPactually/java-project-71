package hexlet.code.formatters;


import java.util.List;
import java.util.Map;

public class BaseFormatter {
    public static String switcher(List<Map<String, Object>> result, String style) {
        switch (style) {
            case "plain":
                return new PlainFormatter().format(result);
            case "json":
                return new JsonFormatter().format(result);
            default:
                return new StylishFormatter().format(result);
        }
    };
}
