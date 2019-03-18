package com.yiifaa.mirana.commons.formatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;

/**
 * @ClassName: DateFormatter
 * @Version:1.0
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 李玉志 email:394023466@qq.com
 * @date 开发时间: 2017年7月18日 下午3:15:36
 * 
 */
public class DateFormatter implements Formatter<Date> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.format.Printer#print(java.lang.Object,
	 * java.util.Locale)
	 */
	@Override
	public String print(Date object, Locale locale) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.format.Parser#parse(java.lang.String,
	 * java.util.Locale)
	 */
	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		if (StringUtils.isNotBlank(text)) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(text);
		}
		return null;
	}

}
