package jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class AnnotationsTest {

    @Test
    public void jsonAnyGetter() throws JsonProcessingException {
        JaacksonAnyGetter jaacksonAnyGetter = new JaacksonAnyGetter("Example JsonAnyGetter");
        jaacksonAnyGetter.getProperties().put("attr1","val1");
        jaacksonAnyGetter.getProperties().put("attr2","val2");


        // SIN --> {"name":"Example JsonAnyGetter","properties":{"attr2":"val2","attr1":"val1"}}
        // CON --> {"name":"Example JsonAnyGetter","attr2":"val2","attr1":"val1"}

        String result = new ObjectMapper().writeValueAsString(jaacksonAnyGetter);

        Assert.assertTrue(result.contains("attr1"));
        Assert.assertTrue(result.contains("val1"));
    }


    @Test
    public void jsonGetter() throws JsonProcessingException {
        Jsongetter jsonGetter  = new Jsongetter("apple");

        //{"thisIsMyNewGet":"apple"}
        String result = new ObjectMapper().writeValueAsString(jsonGetter);

        Assert.assertTrue(result.contains("apple"));
    }

    @Test
    public void jsonPropertyOrder() throws JsonProcessingException {
        JsonPropertyOrderBean jsonPropertyOrderBean = new JsonPropertyOrderBean("name",2);

        String result = new ObjectMapper().writeValueAsString(jsonPropertyOrderBean);

        // order in the json ie. --> @JsonPropertyOrder({"age","name"})
        Assert.assertTrue(result.equals("{\"age\":2,\"name\":\"name\"}"));

    }

    @Test
    public void jsonRawValueTest() throws JsonProcessingException {
        JsonRawValueBean jsonRawValueBean = new JsonRawValueBean("My Bean","{\"attribute\":false}");

        String res = new ObjectMapper().writeValueAsString(jsonRawValueBean);

        // print no format over the json attrib
        Assert.assertTrue(res.contains("{\"name\":\"My Bean\",\"json\":{\"attribute\":false}}"));

    }

    @Test
    public void jsonValueTest() throws JsonProcessingException {

        String enumAsString = new ObjectMapper().writeValueAsString(JsonValueENUMBean.TYPEA);

        // serialize via enum and the field annotated
        Assert.assertTrue(enumAsString.equals("\"type A \""));
    }

    @Test
    public void JsonRootNameBeanTest() throws JsonProcessingException {
        JsonRootNameBean jsonRootNameBean = new JsonRootNameBean("name",2);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);


        String result = mapper.writeValueAsString(jsonRootNameBean);

        Assert.assertTrue(result.equals("{\"rootValue\":{\"name\":\"name\",\"age\":2}}"));
    }

    @Test
    public void jsonSerializerTest() throws JsonProcessingException {
        JsonSerializeBean  jsonSerializeBean = new JsonSerializeBean("ddd",new Date(0,0,0));

        String res = new ObjectMapper().writeValueAsString(jsonSerializeBean);

        Assert.assertTrue(res.equals("{\"name\":\"ddd\",\"eventDate\":\"Sun Dec 31 00:00:00 COT 1899\"}"));

    }


}
