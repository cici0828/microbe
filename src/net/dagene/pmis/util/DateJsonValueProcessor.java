package net.dagene.pmis.util;

import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor {
	private String format;

	public DateJsonValueProcessor(String format) {
		this.format = format;
	}

	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object processObjectValue(String key, Object value, JsonConfig arg2) {
		// TODO Auto-generated method stub
		if (value == null) {
			return "";
		}
		if (value instanceof java.sql.Timestamp) {
			if (format == "")
				return ((java.util.Date) value).getTime();
			else
				return new SimpleDateFormat(format)
						.format((java.sql.Timestamp) value);
		}
		if (value instanceof java.util.Date) {
			if (format == "")
				return ((java.util.Date) value).getTime();
			else
				return new SimpleDateFormat(format)
						.format((java.util.Date) value);
		}

		return value.toString();
	}

}
