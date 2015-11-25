package org.currconv.entities.user;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.currconv.entities.user.User;

public class UserTest {

	@Test()
	public void testUserConstructor() {
        final String username = "username";
        final String password = "password";
        final String hashedPassword = "79613d39bf4faeaa0f1a55da623d2bd6b0499ab5b1a50c17aa4d7deea155bbd6";
		User user = new User(username, password);
		Assert.assertEquals(hashedPassword, user.getPassHash());
	}
    
}