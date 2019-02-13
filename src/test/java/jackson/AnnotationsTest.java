package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnnotationsTest {

    @Test
    public void jsonAnyGetter() throws JsonProcessingException {
        JaacksonAnyGetter jaacksonAnyGetter = new JaacksonAnyGetter("Example JsonAnyGetter");
        jaacksonAnyGetter.getProperties().put("attr1","val1");
        jaacksonAnyGetter.getProperties().put("attr2","val2");


        // SIN --> {"name":"Example JsonAnyGetter","properties":{"attr2":"val2","attr1":"val1"}}
        // CON --> {"name":"Example JsonAnyGetter","attr2":"val2","attr1":"val1"}

        String result = new ObjectMapper().writeValueAsString(jaacksonAnyGetter);

        assertTrue(result.contains("attr1"));
        assertTrue(result.contains("val1"));
    }


    @Test
    public void jsonGetter() throws JsonProcessingException {
        Jsongetter jsonGetter  = new Jsongetter("apple");

        //{"thisIsMyNewGet":"apple"}
        String result = new ObjectMapper().writeValueAsString(jsonGetter);

        assertTrue(result.contains("apple"));
    }

    @Test
    public void jsonPropertyOrder() throws JsonProcessingException {
        JsonPropertyOrderBean jsonPropertyOrderBean = new JsonPropertyOrderBean("name",2);

        String result = new ObjectMapper().writeValueAsString(jsonPropertyOrderBean);

        // order in the json ie. --> @JsonPropertyOrder({"age","name"})
        assertTrue(result.equals("{\"age\":2,\"name\":\"name\"}"));

    }

    @Test
    public void jsonRawValueTest() throws JsonProcessingException {
        JsonRawValueBean jsonRawValueBean = new JsonRawValueBean("My Bean","{\"attribute\":false}");

        String res = new ObjectMapper().writeValueAsString(jsonRawValueBean);

        // print no format over the json attrib
        assertTrue(res.contains("{\"name\":\"My Bean\",\"json\":{\"attribute\":false}}"));

    }

    @Test
    public void jsonValueTest() throws JsonProcessingException {

        String enumAsString = new ObjectMapper().writeValueAsString(JsonValueENUMBean.TYPEA);

        // serialize via enum and the field annotated
        assertTrue(enumAsString.equals("\"type A \""));
    }

    @Test
    public void JsonRootNameBeanTest() throws JsonProcessingException {
        JsonRootNameBean jsonRootNameBean = new JsonRootNameBean("name",2);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);


        String result = mapper.writeValueAsString(jsonRootNameBean);

        assertTrue(result.equals("{\"rootValue\":{\"name\":\"name\",\"age\":2}}"));
    }

    @Test
    public void jsonSerializerTest() throws JsonProcessingException {
        JsonSerializeBean  jsonSerializeBean = new JsonSerializeBean("ddd",new Date(0,0,0));

        String res = new ObjectMapper().writeValueAsString(jsonSerializeBean);

        assertTrue(res.equals("{\"name\":\"ddd\",\"eventDate\":\"Sun Dec 31 00:00:00 COT 1899\"}"));

    }


    @Test
    public void jsonCreatorBeanTest() throws IOException {

        String json = "{\"id\":1,\"theName\":\"My bean\"}";

        JsonCreatorBean  jsonCreatorBean = new ObjectMapper()
                .readerFor(JsonCreatorBean.class)
                .readValue(json);

        assertEquals(jsonCreatorBean.getAge(),1);
        assertEquals(jsonCreatorBean.getName(),"My bean");
    }
}
