package jackson;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JsonValueENUMBean {

    TYPEA(1,"type A "),TYPEB(2,"type B");

    private int value;
    private String name;

    JsonValueENUMBean(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
