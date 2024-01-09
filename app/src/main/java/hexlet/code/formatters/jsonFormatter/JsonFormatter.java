package hexlet.code.formatters.jsonFormatter;

import hexlet.code.formatters.DifferFormatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonFormatter implements DifferFormatter {
    @Override
    public String format(List<Map<String, Object>> result) {
        var mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
