package hexlet.test;

import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

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
        data1.put("host", "hexlet.io");
        data1.put("timeout", "50");
        data1.put("proxy", "123.234.53.22");
        data1.put("follow", "false");

        assertThat(Parser.parse(pathToFileJson1)).isEqualTo(data1);
    }
    @Test
    public void testParse2() {
        var data2 = new HashMap<>();
        data2.put("timeout", "20");
        data2.put("verbose", "true");
        data2.put("host", "hexlet.io");

        assertThat(Parser.parse(pathToFileJson2)).isEqualTo(data2);
    }
    @Test
    public void testParse3() {
        assertThrows(RuntimeException.class, () -> Parser.parse(pathToFileYml3));
    }
    @Test
    public void testDiffer1() {
        String differ1 = "{\n  "
                + "- follow: false\n  "
                + "  host: hexlet.io\n  "
                + "- proxy: 123.234.53.22\n  "
                + "- timeout: 50\n  "
                + "+ timeout: 20\n  "
                + "+ verbose: true\n}";
        var actualJson = Differ.generate(Parser.parse(pathToFileJson1), Parser.parse(pathToFileJson2));
        var actualYml = Differ.generate(Parser.parse(pathToFileYml1), Parser.parse(pathToFileYml2));

        assertThat(actualJson).isEqualTo(differ1);
        assertThat(actualYml).isEqualTo(differ1);
    }
    @Test
    public void testDiffer2() {
        String differ2 = "{\n  "
                + "+ follow: false\n  "
                + "  host: hexlet.io\n  "
                + "+ proxy: 123.234.53.22\n  "
                + "- timeout: 20\n  "
                + "+ timeout: 50\n  "
                + "- verbose: true\n}";
        var actualJson = Differ.generate(Parser.parse(pathToFileJson2), Parser.parse(pathToFileJson1));
        var actualYml = Differ.generate(Parser.parse(pathToFileYml2), Parser.parse(pathToFileYml1));

        assertThat(actualJson).isEqualTo(differ2);
        assertThat(actualYml).isEqualTo(differ2);
    }

}
