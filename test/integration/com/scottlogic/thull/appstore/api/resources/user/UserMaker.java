package integration.com.scottlogic.thull.appstore.api.resources.user;

import com.scottlogic.thull.appstore.api.domains.User;

public class UserMaker {

	static int uniqueCounter = 0;
	
	public static String getNextName() {
		String next = "test" + uniqueCounter;
		uniqueCounter++;
		return next;
	}
	
	public static User makeMeAUser() {
		User user = new User();
		user.setUsername(getNextName());
		user.setEmail( user.getUsername() + "@test.test");
		
		return user;
	}
}
