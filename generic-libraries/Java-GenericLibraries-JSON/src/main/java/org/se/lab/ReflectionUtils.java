package org.se.lab;

import java.lang.reflect.Field;
import java.util.SortedMap;
import java.util.TreeMap;

public class ReflectionUtils {
	public static String getJSONMapping(Class<?> clazz) {
		JSONMapping mapping = clazz.getAnnotation(JSONMapping.class);
		if (mapping == null) {
			throw new IllegalArgumentException("Class " + clazz + " cannot be mapped to json");
		}
		return mapping.name();
	}

	public static SortedMap<String, Object> getMappedJsonValues(Object obj) {
		try {
			SortedMap<String, Object> result = new TreeMap<>();
			for (Field field : obj.getClass().getDeclaredFields()) {
				JSONMapping mapping = field.getAnnotation(JSONMapping.class);
				if (mapping != null) {
					field.setAccessible(true);
					result.put(mapping.name(), field.get(obj));
				}
			}
			return result;
		} catch (IllegalArgumentException | IllegalAccessException ex) {
			throw new RuntimeException("Error reading json values from " + obj, ex);
		}
	}
}
