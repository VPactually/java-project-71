package hexlet.code;

import lombok.*;

@lombok.Data
public class Data {
    private String host;
    private String timeout;
    private String proxy;
    private String follow;
    private String verbose;
}
