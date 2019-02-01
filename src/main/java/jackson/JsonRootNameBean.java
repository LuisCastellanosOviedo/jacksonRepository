package jackson;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "rootValue")
public class JsonRootNameBean {


    private String name;
    private int age;

    public JsonRootNameBean(String name, int age) {
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
