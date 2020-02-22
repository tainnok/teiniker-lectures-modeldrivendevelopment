package org.se.lab;

import org.junit.Assert;
import org.junit.Test;

public class TestUserJsonMapper {
	@Test
	public void testUser() {
		Assert.assertEquals("\"user\":{\"username\":\"student\",\"password\":\"secretpassword\"}",
				new UserJsonMapper().toJSON(new User("student", "secretpassword")));
	}
}
