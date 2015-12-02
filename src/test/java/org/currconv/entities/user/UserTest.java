package org.currconv.entities.user;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest {

	@Test()
	public void testUserConstructor() {
        final String username = "username";
        final String password = "password";
		User user = new User(username, password);
		Assert.assertNotEquals(user.getPassword(), user.getPassHash());
	}
    
}