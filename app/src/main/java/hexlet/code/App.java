package hexlet.code;

import lombok.Getter;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "app 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {
    @Getter
    @Option(names = "-f", defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}}")
    private static String format;
    @Parameters(description = "path to first file")
    private String filepath1;
    @Parameters(description = "path to second file")
    private String filepath2;

    @Override
    public String call() {
        var genDiff = Differ.generate(filepath1, filepath2);
        System.out.println(genDiff);
        return genDiff;
    }

    public static void main(String[] args) {
        App app = new App();
        CommandLine cmd = new CommandLine(app);
        cmd.execute(args);

//        cmd.execute("src/main/resources/file1.json","src/main/resources/file2.json");

//        cmd.execute("D:\\Java\\0.Projects\\java-project-71\\app\\src\\main\\resources\\file1.yml",
//                "src\\main\\resources\\file2.yml");
    }
}
