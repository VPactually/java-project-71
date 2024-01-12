package hexlet.test;

import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;


public class ProjectTest {
    private static String pathToFileJson1;
    private static String pathToFileJson2;
    private static String pathToFileYml1;
    private static String pathToFileYml2;
    private static String pathToFileYml3;
    private static List<Map<String, Object>> list;
    private static String jsonResult;
    private static String plainResult;
    private static String stylishResult;

    @BeforeAll
    public static void beforeAll() {
        pathToFileJson1 = "src/test/resources/fixtures/file1.json";
        pathToFileJson2 = "src/test/resources/fixtures/file2.json";
        pathToFileYml1 = "src/test/resources/fixtures/file1.yml";
        pathToFileYml2 = "src/test/resources/fixtures/file2.yml";
        jsonResult = "[{"
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
        plainResult = "Property 'chars2' was updated. From [complex value] to false\n"
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
        stylishResult = "{\n"
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
    }

    @Test
    public void testDifferStylishFromJson() {
        var actual = Differ.generate(pathToFileJson1, pathToFileJson2, "stylish");
        assertThat(actual).isEqualTo(stylishResult);
    }

    @Test
    public void testDifferPlainFromJson() {
        var actual = Differ.generate(pathToFileJson1, pathToFileJson2, "plain");
        assertThat(actual).isEqualTo(plainResult);
    }

    @Test
    public void testDifferJsonFromJson() {
        var actual = Differ.generate(pathToFileJson1, pathToFileJson2, "json");
        assertThat(actual).isEqualTo(jsonResult);
    }

    @Test
    public void testDifferStylishFromYml() {
        var actual = Differ.generate(pathToFileYml1, pathToFileYml2, "stylish");
        assertThat(actual).isEqualTo(stylishResult);
    }

    @Test
    public void testDifferPlainFromYml() {
        var actual = Differ.generate(pathToFileYml1, pathToFileYml2, "plain");
        assertThat(actual).isEqualTo(plainResult);
    }

    @Test
    public void testDifferJsonFromYml() {
        var actual = Differ.generate(pathToFileYml1, pathToFileYml2, "json");
        assertThat(actual).isEqualTo(jsonResult);
    }

    @Test
    public void testDifferDefaultFromJson() {
        var actual = Differ.generate(pathToFileJson1, pathToFileJson2);
        assertThat(actual).isEqualTo(stylishResult);
    }

    @Test
    public void testDifferDefaultFromYml() {
        var actual = Differ.generate(pathToFileYml1, pathToFileYml2);
        assertThat(actual).isEqualTo(stylishResult);
    }
}
