package jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.HashMap;
import java.util.Map;

public class JaacksonAnyGetter {

    public String name;
    private Map<String,String> properties = new HashMap<>();

    public JaacksonAnyGetter(String name) {
        this.name = name;
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }
}
