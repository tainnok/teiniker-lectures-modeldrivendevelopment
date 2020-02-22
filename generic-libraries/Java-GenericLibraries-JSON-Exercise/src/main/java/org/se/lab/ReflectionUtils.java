package org.se.lab;

import java.util.SortedMap;
import java.util.TreeMap;

public class ReflectionUtils {
	public static String getJSONMapping(Class<?> clazz) {
		// TODO: return the name of the JSON mapping assigned to the class and throw an
		// error if the class has no json mapping
		return null;
	}

	public static SortedMap<String, Object> getMappedJsonValues(Object obj) {
		try {
			SortedMap<String, Object> result = new TreeMap<>();
			// TODO: add the values and json mapping names of all fields having a json
			// mapping to the map,
			// where the mapping is the key and the value of the field the value
			// TIPP: don't forget to set the field accessible
			return result;
		} catch (IllegalArgumentException | IllegalAccessException ex) {
			throw new RuntimeException("Error reading json values from " + obj, ex);
		}
	}
}
