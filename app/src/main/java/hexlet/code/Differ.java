package hexlet.code;

import hexlet.code.formatters.BaseFormatter;

public class Differ {


    public static String generate(String filepath1, String filepath2) {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String style) {
        var data1 = Parser.parse(filepath1);
        var data2 = Parser.parse(filepath2);
        var result = GenDiff.genDiff(data1, data2);
        return BaseFormatter.switcher(result, style);

    }
}
