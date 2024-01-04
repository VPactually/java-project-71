package hexlet.test;

import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;


public class ProjectTest {
    private static String pathToFile1;
    private static String pathToFile2;

    @BeforeAll
    public static void beforeAll() {
        pathToFile1 = "src/test/java/hexlet/test/file1.json";
        pathToFile2 = "src/test/java/hexlet/test/file2.json";
    }

    @Test
    public void testParse1() {
        var data1 = new HashMap<>();
        data1.put("host", "hexlet.io");
        data1.put("timeout", "50");
        data1.put("proxy", "123.234.53.22");
        data1.put("follow", "false");

        assertThat(Parser.parse(pathToFile1)).isEqualTo(data1);
    }

    @Test
    public void testParse2() {
        var data2 = new HashMap<>();
        data2.put("timeout", "20");
        data2.put("verbose", "true");
        data2.put("host", "hexlet.io");

        assertThat(Parser.parse(pathToFile2)).isEqualTo(data2);
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

        assertThat(Differ.generate(Parser.parse(pathToFile1), Parser.parse(pathToFile2))).isEqualTo(differ1);
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

        assertThat(Differ.generate(Parser.parse(pathToFile2), Parser.parse(pathToFile1))).isEqualTo(differ2);
    }

}
