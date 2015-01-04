package my.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
/**
 * jackson 处理 null 为 ""
 * @author taox
 */
public class MyJsonMapper extends ObjectMapper  {

	public MyJsonMapper() {
		// 空值处理为空串  
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>(){ 
            @Override  
            public void serialize(
                    Object value,
                    JsonGenerator jg, 
                    SerializerProvider sp) throws IOException, JsonProcessingException{
            	if(value == null){
            		jg.writeString("");
            	}else{
            		jg.writeObject(value);
            	}
            }  
        });
	}
}
