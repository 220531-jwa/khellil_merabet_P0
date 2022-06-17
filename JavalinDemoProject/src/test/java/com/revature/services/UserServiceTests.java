package com.revature.services;

import repositories.UserDAOP0;
import services.UserService;

public class UserServiceTests {

// an instance
	private static UserService userService;
	// since
	@Mock
	private static UserDAOP0 mockUserDao;
	
	// 
//	@BeforeAll
//	public static void setUp() {
//		userService = new UserService();
//		// this is
//		mockUserDao = mock(UserDAOP0.class);
//	}
	
	@Test
	public void should_ReturnAllUsers() {
		// given 3 users in db
		List<User> users = new ArrayList<>();
		usersMock.add(new User(1,"Manny", "Mock", "user", "pass"));
		usersMock.add(new User(2,"Debbie", "Mock", "user", "pass"));
		usersMock.add(new User(3,"Gigi", "Mock", "user", "pass"));
		
		// 
		when(mockUserDao.getAllUsers()).thenReturn(usersMock);
		assertEquals(usersMock, userService.getAllUsers());
		
		
	}
	
	
		
	
}
