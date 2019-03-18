package com.yiifaa.mirana.commons.formatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Sort.Direction;

/**
 * 
 * 用于将字符串转换为排序方向
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0 开发日期：2017年6月2日 ： 下午5:20:22
 */
public class DirectionConverter implements Converter<String, Direction> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.core.convert.converter.Converter#convert(java.lang.
	 * Object)
	 */
	@Override
	public Direction convert(String dir) {
		if (StringUtils.equalsIgnoreCase(Direction.ASC.name(), dir)) {
			return Direction.ASC;
		}
		return Direction.DESC;
	}

}
