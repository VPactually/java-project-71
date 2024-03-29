package hexlet.code.formatters;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public final class JsonFormatter {
    public String format(List<Map<String, Object>> result) {
        var mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
