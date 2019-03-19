package com.yiifaa.mirana.commons.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.yiifaa.mirana.commons.domain.Menu;
import com.yiifaa.mirana.commons.service.MenuReaderService;

public class MenuReaderServiceImpl implements MenuReaderService {
	
	private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
	
	private Resource menuResource;

	public MenuReaderServiceImpl(Resource menuResource) {
		super();
		this.menuResource = menuResource;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<Menu>> read() {
		ImmutableMap.Builder<String, List<Menu>> builder = ImmutableMap.builder();
		try {
			Map<String, Object> menus = objectMapper.readValue(menuResource.getFile(), Map.class);
			for(String key: menus.keySet()) {
				Object value = menus.get(key);
				ImmutableList.Builder<Menu> menuBuilder = ImmutableList.builder();
				if(value instanceof List) {
					List<Map<String, Object>> items = (List<Map<String,Object>>)value;
					for(Map<String, Object> item: items) {
						menuBuilder.add(convert(item));
					}
					builder.put(key, menuBuilder.build());
				}
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.build();
	}
	
	
	private Menu convert(Map<String, Object> item) {
		Menu menuItem = new Menu();
		menuItem.setName((String)item.get("name"));
		menuItem.setIcon((String)item.get("icon"));
		menuItem.setUrl((String)item.get("url"));
		if(item.containsKey("children")) {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> childs = (List<Map<String,Object>>)item.get("children");
			for(Map<String, Object> child: childs) {
				menuItem.addChild(convert(child));
			}
		}
		return menuItem;
	}
	

}
