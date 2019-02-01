package jackson;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"age","name"})
public class JsonPropertyOrderBean {

    private String name;
    private int age;

    public JsonPropertyOrderBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
