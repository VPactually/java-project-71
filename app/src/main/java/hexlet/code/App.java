package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "app", mixinStandardHelpOptions = true, version = "app 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {
    @Option(names = "-f", defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}}")
    private String format = "stylish";
    @Parameters(description = "path to first file")
    private String filepath1;
    @Parameters(description = "path to second file")
    private String filepath2;

    @Override
    public String call() {
        try {
            var data1 = Parser.parse(filepath1);
            var data2 = Parser.parse(filepath2);
            var genDiff = Differ.generate(data1, data2);
            System.out.append(genDiff);
            return genDiff;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        App app = new App();
        CommandLine cmd = new CommandLine(app);
        cmd.execute(args);
//        cmd.execute("D:\\Java\\0.Projects\\java-project-71\\app\\src\\main\\java\\hexlet\\code\\files\\file1.json",
//                "src\\main\\java\\hexlet\\code\\files\\file2.json");
    }
}