package org.se.lab;

import java.util.Map;
import java.util.Map.Entry;

public class GenericJsonMapper<T> {
	public String toJSON(T obj) {
		StringBuilder builder = new StringBuilder();
		builder.append("\"");
		builder.append(ReflectionUtils.getJSONMapping(obj.getClass()));
		builder.append("\":{");
		builder.append(convertFields(obj));
		builder.append("}");
		return builder.toString();
	}

	private String convertFields(T obj) {
		StringBuilder builder = new StringBuilder();
		Map<String, Object> mappings = ReflectionUtils.getMappedJsonValues(obj);
		for (Entry<String, Object> entry : mappings.entrySet()) {
			writeField(builder, entry.getKey(), entry.getValue());
		}
		return builder.toString();
	}

	private void writeField(StringBuilder builder, String name, Object value) {
		if (builder.length() > 0) {
			builder.append(",");
		}
		builder.append("\"");
		builder.append(name);
		builder.append("\":\"");
		builder.append(value);
		builder.append("\"");
	}
}
