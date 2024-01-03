package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Arrays;

@Command(name = "app", mixinStandardHelpOptions = true, version = "app 1.0",
        description = "This programm do only \"Hello world!\", for now, but later it will works!")
public class App implements Runnable {
    @Option(names = "-f", defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}}")
    private String format = "stylish";
    @Parameters(description = "path to first file")
    private String filepath1;
    @Parameters(description = "path to second file")
    private String filepath2;

    public void run() {
        try {
            var data1 = Parser.parse(filepath1);
            var data2 = Parser.parse(filepath2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        App app = new App();
        CommandLine cmd = new CommandLine(app);
        cmd.execute("D:\\Java\\0.Projects\\java-project-71\\app\\src\\main\\java\\hexlet\\code\\files\\file1.json",
                "D:\\Java\\0.Projects\\java-project-71\\app\\src\\main\\java\\hexlet\\code\\files\\file2.json");
    }
}