package org.se.lab;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class ModelToObjectDiagram {
	public String toDiagram(Object rootNode) {
		StringWriter sr = new StringWriter();
		PrintWriter writer = new PrintWriter(sr);
		writer.println("@startuml");

		Map<Object, String> writtenObjects = new HashMap<>();
		Map<Class<?>, Integer> objectIndex = new HashMap<>();
		writeObject(writtenObjects, objectIndex, writer, rootNode);

		writer.println("@enduml");
		writer.close();

		return sr.toString();
	}

	private String writeObject(Map<Object, String> writtenObjects, Map<Class<?>, Integer> objectIndex,
			PrintWriter writer, Object obj) {
		if (writtenObjects.containsKey(obj)) {
			return writtenObjects.get(obj);
		}

		String name = obj.getClass().getSimpleName() + nextIndex(obj.getClass(), objectIndex);
		writtenObjects.put(obj, name);

		writer.print("object " + name);
		writeFields(writer, obj);
		writer.println();

		Map<String, List<Object>> children = getChildObjects(obj);
		for (Entry<String, List<Object>> field : children.entrySet()) {
			for (Object child : field.getValue()) {
				String childName = writeObject(writtenObjects, objectIndex, writer, child);
				writer.println(name + " o-- " + childName + " : " + field.getKey());
			}
		}

		return name;
	}

	private void writeFields(PrintWriter writer, Object obj) {
		List<Field> simpleFields = getFieldsWithGetters(obj).stream().filter(this::isSimpleType)
				.collect(Collectors.toList());
		if (!simpleFields.isEmpty()) {
			writer.println("{");
			for (Field field : simpleFields) {
				writeField(writer, obj, field);
			}
			writer.println("}");
		} else {
			writer.println();
		}
	}

	private void writeField(PrintWriter writer, Object obj, Field field) {
		try {
			field.setAccessible(true);
			writer.print("\t");
			writer.print(field.getName());
			writer.print(" = ");
			if (String.class.isAssignableFrom(field.getType())) {
				writer.print("\"");
			}
			writer.print(field.get(obj).toString());
			if (String.class.isAssignableFrom(field.getType())) {
				writer.print("\"");
			}
			writer.println();
		} catch (IllegalArgumentException | IllegalAccessException ex) {
			throw new RuntimeException("Error writing field " + field + " of class " + obj.getClass(), ex);
		}
	}

	private String nextIndex(Class<? extends Object> clazz, Map<Class<?>, Integer> objectIndex) {
		if (!objectIndex.containsKey(clazz)) {
			objectIndex.put(clazz, 0);
		}
		int index = objectIndex.get(clazz) + 1;
		objectIndex.put(clazz, index);
		return String.format("%03d", index);
	}

	private Map<String, List<Object>> getChildObjects(Object parent) {
		List<Field> fieldsWithGetter = getFieldsWithGetters(parent);
		List<Field> nonSimpleFields = fieldsWithGetter.stream().filter(field -> !isSimpleType(field))
				.collect(Collectors.toList());
		return nonSimpleFields.stream()
				.collect(Collectors.toMap(field -> field.getName(), field -> expandList(parent, field)));
	}

	private List<Field> getAllFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<>();
		fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
		if (clazz.getSuperclass() != null) {
			fields.addAll(getAllFields(clazz.getSuperclass()));
		}
		return fields;
	}

	private List<Field> getFieldsWithGetters(Object parent) {
		List<Field> fields = getAllFields(parent.getClass());

		List<Field> fieldsWithGetter = fields.stream().filter(field -> !Modifier.isStatic(field.getModifiers()))
				.filter(field -> hasGetter(parent, field)).collect(Collectors.toList());
		return fieldsWithGetter;
	}

	private boolean isSimpleType(Field field) {
		Class<?> type = field.getType();
		return Number.class.isAssignableFrom(type) || String.class.isAssignableFrom(type)
				|| boolean.class.isAssignableFrom(type) || type.isEnum() || int.class.isAssignableFrom(type);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Object> expandList(Object parent, Field field) {
		try {
			field.setAccessible(true);
			Object obj = field.get(parent);
			List<Object> result = new ArrayList<>();
			if (obj instanceof Collection) {
				result.addAll((Collection) obj);
			} else {
				result.add(obj);
			}
			return result;
		} catch (IllegalArgumentException | IllegalAccessException ex) {
			throw new RuntimeException(
					"Error expanding field " + field.getName() + " in class " + parent.getClass().getName(), ex);
		}
	}

	private boolean hasGetter(Object parent, Field field) {
		String getterName = (boolean.class.isAssignableFrom(field.getType()) ? "is" : "get")
				+ StringUtils.capitalize(field.getName());
		try {
			parent.getClass().getMethod(getterName);
			return true;
		} catch (NoSuchMethodException ex) {
			// exception content ignored on purpose
			return false;
		}
	}
}
