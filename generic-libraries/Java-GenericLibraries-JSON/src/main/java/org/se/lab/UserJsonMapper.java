package org.se.lab;

public class UserJsonMapper {
	public String toJSON(User user) {
		StringBuilder builder = new StringBuilder();
		builder.append("\"");
		builder.append("user");
		builder.append("\":{");
		builder.append(convertFields(user));
		builder.append("}");
		return builder.toString();
	}

	private String convertFields(User user) {
		StringBuilder builder = new StringBuilder();
		writeField(builder, "username", user.getUsername());
		writeField(builder, "password", user.getPassword());
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
