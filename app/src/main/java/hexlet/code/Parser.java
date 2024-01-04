package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parser {
    public static Map<String, String> parse(String filepath) {
        var mapper = new ObjectMapper();
        var map = new TreeMap<String, String>();
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        File file = path.toFile();
        try {
            map = mapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

}
