package hexlet.test;

import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ProjectTest {
    private static String pathToFileJson1;
    private static String pathToFileJson2;
    private static String pathToFileYml1;
    private static String pathToFileYml2;
    private static String pathToFileYml3;

    @BeforeAll
    public static void beforeAll() {
        pathToFileJson1 = "src/test/resources/file1.json";
        pathToFileJson2 = "src/test/resources/file2.json";
        pathToFileYml1 = "src/test/resources/file1.yml";
        pathToFileYml2 = "src/test/resources/file2.yml";
        pathToFileYml3 = "src/test/resources/file3.yml";
    }
    @Test
    public void testParse1() {
        var data1 = new HashMap<>();
        data1.put("chars1", new ArrayList<>(List.of("a", "b", "c")));
        data1.put("chars2", new ArrayList<>(List.of("d", "e", "f")));
        data1.put("checked", false);
        data1.put("default", null);
        data1.put("id", 45);
        data1.put("key1", "value1");
        data1.put("numbers1", new ArrayList<>(List.of(1, 2, 3, 4)));
        data1.put("numbers2", new ArrayList<>(List.of(2, 3, 4, 5)));
        data1.put("numbers3", new ArrayList<>(List.of(3, 4, 5)));
        data1.put("setting1", "Some value");
        data1.put("setting2", 200);
        data1.put("setting3", true);

        assertThat(Parser.parse(pathToFileJson1)).isEqualTo(data1);
        assertThat(Parser.parse(pathToFileYml1)).isEqualTo(data1);
    }
    @Test
    public void testParse2() {
        var data2 = new HashMap<>();
        data2.put("chars1", new ArrayList<>(List.of("a", "b", "c")));
        data2.put("chars2", false);
        data2.put("checked", true);
        data2.put("default", new ArrayList<>(List.of("value1", "value2")));
        data2.put("id", null);
        data2.put("key2", "value2");
        data2.put("numbers1", new ArrayList<>(List.of(1, 2, 3, 4)));
        data2.put("numbers2", new ArrayList<>(List.of(22, 33, 44, 55)));
        data2.put("numbers4", new ArrayList<>(List.of(4, 5, 6)));
        data2.put("setting1", "Another value");
        data2.put("setting2", 300);
        data2.put("setting3", "none");
        data2.put("obj1", new LinkedHashMap<>(Map.of("nestedKey", "value", "isNested", true)));

        assertThat(Parser.parse(pathToFileJson2)).isEqualTo(data2);
        assertThat(Parser.parse(pathToFileYml2)).isEqualTo(data2);
    }
    @Test
    public void testParse3() {
        assertThrows(RuntimeException.class, () -> Parser.parse(pathToFileYml3));
    }
    @Test
    public void testDiffer() {
        var expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";
        var actual = Differ.generate(Parser.parse(pathToFileJson1), Parser.parse(pathToFileJson2));

        assertThat(actual).isEqualTo(expected);
    }

}
