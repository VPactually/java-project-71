package hexlet.test;

import hexlet.code.Differ;
import hexlet.code.Parser;
import hexlet.code.formatters.plainFormatter.PlainFormatter;
import hexlet.code.formatters.stylishFormatter.StylishFormatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ProjectTest {
    private static String pathToFileJson1;
    private static String pathToFileJson2;
    private static String pathToFileYml1;
    private static String pathToFileYml2;
    private static String pathToFileYml3;
    private static List<Map<String, Object>> list;

    @BeforeAll
    public static void beforeAll() {
        pathToFileJson1 = "src/test/resources/fixtures/file1.json";
        pathToFileJson2 = "src/test/resources/fixtures/file2.json";
        pathToFileYml1 = "src/test/resources/fixtures/file1.yml";
        pathToFileYml2 = "src/test/resources/fixtures/file2.yml";
        pathToFileYml3 = "src/test/resources/fixtures/file3.yml";
        list = List.of(
                Map.of("FIELD", "timeout",
                        "STATUS", "UPDATED",
                        "NEW_VALUE", 20,
                        "OLD_VALUE", 50),
                Map.of("FIELD", "chars4",
                        "STATUS", "ADDED",
                        "NEW_VALUE", new ArrayList<>(List.of("a", "b", "c")),
                        "OLD_VALUE", "null"),
                Map.of("FIELD", "key1",
                        "STATUS", "REMOVED",
                        "NEW_VALUE", "null",
                        "OLD_VALUE", "value1"),
                Map.of("FIELD", "chars1",
                        "STATUS", "SAME",
                        "NEW_VALUE", new ArrayList<>(List.of("a", "b", "c")),
                        "OLD_VALUE", new ArrayList<>(List.of("a", "b", "c"))));
    }

    @Test
    public void testStylishException() {
        var actual = new StylishFormatter();
        assertThrows(IllegalArgumentException.class, () -> actual.format(List.of(
                Map.of("FIELD", "key1", "STATUS", "IDONTKNOW",
                        "NEW_VALUE", "null", "OLD_VALUE", "value1"))));
    }

    @Test
    public void testPlainException() {
        var actual = new PlainFormatter();
        assertThrows(IllegalArgumentException.class, () -> actual.format(List.of(
                Map.of("FIELD", "key1", "STATUS", "IDONTKNOW",
                        "NEW_VALUE", "null", "OLD_VALUE", "value1"))));
    }

    @Test
    public void testDifferStylish() {
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
        var actual = Differ.generate(pathToFileJson1, pathToFileJson2, "stylish");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDifferPlain() {
        var expected = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";

        var actual = Differ.generate(pathToFileJson1, pathToFileJson2, "plain");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDifferJson() {
        var expected = "[{"
                + "\"FIELD\":\"chars1\",\"NEW_VALUE\":[\"a\",\"b\",\"c\"],"
                + "\"OLD_VALUE\":[\"a\",\"b\",\"c\"],\"STATUS\":\"SAME\"},"
                + "{\"FIELD\":\"chars2\",\"NEW_VALUE\":false,"
                + "\"OLD_VALUE\":[\"d\",\"e\",\"f\"],\"STATUS\":\"UPDATED\"},"
                + "{\"FIELD\":\"checked\",\"NEW_VALUE\":true,"
                + "\"OLD_VALUE\":false,\"STATUS\":\"UPDATED\"},"
                + "{\"FIELD\":\"default\",\"NEW_VALUE\":[\"value1\",\"value2\"],"
                + "\"OLD_VALUE\":null,\"STATUS\":\"UPDATED\"},"
                + "{\"FIELD\":\"id\",\"NEW_VALUE\":null,"
                + "\"OLD_VALUE\":45,\"STATUS\":\"UPDATED\"},"
                + "{\"FIELD\":\"key1\",\"NEW_VALUE\":null,"
                + "\"OLD_VALUE\":\"value1\",\"STATUS\":\"REMOVED\"},"
                + "{\"FIELD\":\"key2\",\"NEW_VALUE\":\"value2\","
                + "\"OLD_VALUE\":null,\"STATUS\":\"ADDED\"},"
                + "{\"FIELD\":\"numbers1\",\"NEW_VALUE\":[1,2,3,4],"
                + "\"OLD_VALUE\":[1,2,3,4],\"STATUS\":\"SAME\"},"
                + "{\"FIELD\":\"numbers2\",\"NEW_VALUE\":[22,33,44,55],"
                + "\"OLD_VALUE\":[2,3,4,5],\"STATUS\":\"UPDATED\"},"
                + "{\"FIELD\":\"numbers3\",\"NEW_VALUE\":null,"
                + "\"OLD_VALUE\":[3,4,5],\"STATUS\":\"REMOVED\"},"
                + "{\"FIELD\":\"numbers4\",\"NEW_VALUE\":[4,5,6],"
                + "\"OLD_VALUE\":null,\"STATUS\":\"ADDED\"},"
                + "{\"FIELD\":\"obj1\",\"NEW_VALUE\":{\"nestedKey\":\"value\",\"isNested\":true},"
                + "\"OLD_VALUE\":null,\"STATUS\":\"ADDED\"},"
                + "{\"FIELD\":\"setting1\",\"NEW_VALUE\":\"Another value\","
                + "\"OLD_VALUE\":\"Some value\",\"STATUS\":\"UPDATED\"},"
                + "{\"FIELD\":\"setting2\",\"NEW_VALUE\":300,"
                + "\"OLD_VALUE\":200,\"STATUS\":\"UPDATED\"},"
                + "{\"FIELD\":\"setting3\",\"NEW_VALUE\":\"none\","
                + "\"OLD_VALUE\":true,\"STATUS\":\"UPDATED\"}]";
        var actual = Differ.generate(pathToFileJson1, pathToFileJson2, "json");

        assertThat(actual).isEqualTo(expected);
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
    public void testStylish() {
        String expected = "{\n  - timeout: 50\n  + timeout: 20\n  + chars4: [a, b, c]\n"
                + "  - key1: value1\n    chars1: [a, b, c]\n}";
        var res = new StylishFormatter();
        String actual = res.format(list);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testPlain() {
        var res = new PlainFormatter();
        String actual = res.format(list);
        String expected = "Property 'timeout' was updated. From 50 to 20\n"
                + "Property 'chars4' was added with value: [complex value]\n"
                + "Property 'key1' was removed";

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testValid() {
        var res = new PlainFormatter();
        var actual = res.valid(null, new ArrayList<>(List.of(String.class)));
        assertThat(actual).isEqualTo(null);
    }

}
