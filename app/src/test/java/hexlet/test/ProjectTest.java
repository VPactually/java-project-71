package hexlet.test;

import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;


public final class ProjectTest {
    private static String pathToFile1;
    private static String pathToFile2;
    private static String jsonResult;
    private static String plainResult;
    private static String stylishResult;

    @BeforeAll
    public static void beforeAll() throws IOException {
        pathToFile1 = "src/test/resources/fixtures/file1";
        pathToFile2 = "src/test/resources/fixtures/file2";
        jsonResult = Files.readString(Paths.get("src/test/resources/fixtures/jsonResult"));
        plainResult = Files.readString(Paths.get("src/test/resources/fixtures/plainResult"));
        stylishResult = Files.readString(Paths.get("src/test/resources/fixtures/stylishResult"));
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public  void testDifferDefault(String fileExtension) {
        var actual = Differ.generate(pathToFile1 + fileExtension, pathToFile2 + fileExtension);
        assertThat(actual).isEqualTo(stylishResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void testDifferStylish(String extension) {
        var actual = Differ.generate(pathToFile1 + extension, pathToFile2 + extension, "stylish");
        assertThat(actual).isEqualTo(stylishResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void testDifferPLain(String extension) {
        var actual = Differ.generate(pathToFile1 + extension, pathToFile2 + extension, "plain");
        assertThat(actual).isEqualTo(plainResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void testDifferJson(String extension) {
        var actual = Differ.generate(pathToFile1 + extension, pathToFile2 + extension, "json");
        assertThat(actual).isEqualTo(jsonResult);
    }
}
