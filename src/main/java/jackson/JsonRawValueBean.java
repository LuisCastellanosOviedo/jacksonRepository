package jackson;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class JsonRawValueBean {

    private String name;

    @JsonRawValue
    private String json;

    public JsonRawValueBean(String name, String json) {
        this.name = name;
        this.json = json;
    }

    public String getName() {
        return name;
    }

    public String getJson() {
        return json;
    }
}
