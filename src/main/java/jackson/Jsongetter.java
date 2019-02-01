package jackson;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Jsongetter {

    private String fruit;

    public Jsongetter(String fruit) {
        this.fruit = fruit;
    }


    @JsonGetter
    public String thisIsMyNewGet() {
        return fruit;
    }
}
