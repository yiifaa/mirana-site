package com.yiifaa.mirana.commons;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlReaderTest {

	public static void  main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		Map<String, Object> menus = mapper.readValue(new File("src/main/resources/menu.yaml"), Map.class);
		for(String key: menus.keySet()) {
			Object value = menus.get(key);
			if(value instanceof List) {
				List<Map<String, Object>> items = (List<Map<String,Object>>)value;
				for(Map<String, Object> item: items) {
					if(item.containsKey("children")) {
					}
				}
			}
		}
	}

}
