package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Arrays;

@Command(name = "app", mixinStandardHelpOptions = true, version = "app 1.0",
        description = "This programm do only \"Hello world!\", for now, but later it will works!")
public class App implements Runnable {
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display a help message")
    private boolean helpRequest = false;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit")
    private boolean verbose = false;
    @Option(names = "-f", defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}}")
    private String format = "stylish";
    @Parameters(description = "path to first file")
    private String filepath1;
    @Parameters(description = "path to second file")
    private String filepath2;

    public void run() {
        System.out.println(format);
        System.out.println(filepath1);
        System.out.println(filepath2);
    }

    public static void main(String[] args) {
        App app = new App();
        CommandLine cmd = new CommandLine(app);
        cmd.execute(args);
    }
}