package org.se.lab;

import org.junit.Assert;
import org.junit.Test;

public class TestGenericJsonMapper {
	@Test
	public void testUser() {
		Assert.assertEquals("\"user\":{\"password\":\"secretpassword\",\"username\":\"student\"}",
				new GenericJsonMapper<User>().toJSON(new User("student", "secretpassword")));
	}

	@Test
	public void testBook() {
		Assert.assertEquals("\"book\":{\"isbn\":\"12345689\",\"title\":\"Generics for Dummies\"}",
				new GenericJsonMapper<Book>().toJSON(new Book("Generics for Dummies", "12345689")));
	}
}
