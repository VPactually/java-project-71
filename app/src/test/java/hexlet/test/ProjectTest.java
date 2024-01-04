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
        pathToFile1 = "D:\\Java\\0.Projects\\java-project-71\\app\\src\\test\\resources\\file1.json";
        pathToFile2 = "src\\test\\resources\\file2.json";
    }

    @Test
    public void testParse1()  {
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
        String differ1 = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        assertThat(Differ.generate(Parser.parse(pathToFile1), Parser.parse(pathToFile2))).isEqualTo(differ1);
    }

    @Test
    public void testDiffer2() {
        String differ2 = """
                {
                  + follow: false
                    host: hexlet.io
                  + proxy: 123.234.53.22
                  - timeout: 20
                  + timeout: 50
                  - verbose: true
                }""";

        assertThat(Differ.generate(Parser.parse(pathToFile2), Parser.parse(pathToFile1))).isEqualTo(differ2);
    }

}
