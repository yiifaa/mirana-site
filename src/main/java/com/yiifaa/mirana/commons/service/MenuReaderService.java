package com.yiifaa.mirana.commons.service;

import java.util.List;
import java.util.Map;

import com.yiifaa.mirana.commons.domain.Menu;

public interface MenuReaderService {
	
	Map<String, List<Menu>> read();

}
