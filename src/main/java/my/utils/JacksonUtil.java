package my.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
public class JacksonUtil {
	private static final Log logger = LogFactory.getLog(JacksonUtil.class);
	
	public static Map<String, Object> parseToMap(String jsonStr) throws JsonParseException, IOException{
		ObjectMapper objectMapper  = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
		//{no:'2892',ck:'11',type:'出库',operator:'admin',tms:[{tm:'0009842250832',num:2},{tm:'0002412766040',num:1}]}
		Map<String, Object> maps = objectMapper.readValue(jsonStr, Map.class);
		System.out.println(maps.size());  
		Set<String> key = maps.keySet();  
		Iterator<String> iter = key.iterator();  
		while (iter.hasNext()) {
			String field = iter.next();  
			System.out.println(field + ":" + maps.get(field));  
		}
		return maps;
	}
	
	public static List<Map<String, Object>> parseToList(String jsonStr) throws JsonParseException, IOException{
		ObjectMapper objectMapper  = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
		//{no:'2892',ck:'11',type:'出库',operator:'admin',tms:[{tm:'0009842250832',num:2},{tm:'0002412766040',num:1}]}
		List<Map<String, Object>> list = objectMapper.readValue(jsonStr, List.class);
		for (int i = 0; i < list.size(); i++) {  
			Map<String, Object> map = list.get(i);  
			Set<String> set = map.keySet();  
			for (Iterator<String> it = set.iterator();it.hasNext();) {  
			String key = it.next();  
//			System.out.println(key + ":" + map.get(key));  
		}}
		return list;
	}
	
	/** 
     * 如果对象为Null,返回"null". 
     * 如果集合为空集合,返回"[]". 
     */  
    public static String toJson(Object object) {  
        try {  
        	ObjectMapper objectMapper  = new ObjectMapper();
            return objectMapper.writeValueAsString(object);  
        } catch (IOException e) {  
            logger.warn("write to json string error:" + object, e);  
            return null;  
        }  
    }  
	
	
	
	public static void main(String[] args) throws JsonParseException, IOException {
//		parseToList("[{no:'2892',ck:'11',type:'出库',operator:'admin',tms:[{tm:'0009842250832',num:2},{tm:'0002412766040',num:1}]}]");
	
		Integer obj = 3;
		System.out.println(obj instanceof Integer);
	
	}
}
