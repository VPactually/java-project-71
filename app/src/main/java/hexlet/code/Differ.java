package hexlet.code;

import hexlet.code.formatters.BaseFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Differ {

    public static String getStringResult(String filepath) {
        try {
            return Files.readString(Paths.get(filepath).toAbsolutePath().normalize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFileType(String filepath) {
        return Paths.get(filepath).toString().split("\\.")[1];
    }

    public static String generate(String filepath1, String filepath2) {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String style) {
        var fileType1 = getFileType(filepath1);
        var fileType2 = getFileType(filepath2);
        var file1Strings = getStringResult(filepath1);
        var file2Strings = getStringResult(filepath2);
        var data1 = Parser.parse(file1Strings, fileType1);
        var data2 = Parser.parse(file2Strings, fileType2);
        var result = DiffGenerator.genDiff(data1, data2);
        return BaseFormatter.switcher(result, style);

    }
}
