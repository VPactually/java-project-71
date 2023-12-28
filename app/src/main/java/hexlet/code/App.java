package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "app", mixinStandardHelpOptions = true, version = "checksum 4.0", description = "This programm do " +
        "only \"Hello world!, for now, but later it will works!")
public class App {
    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    private boolean helpRequest = false;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
        System.out.println("Hello world!");

    }
}
