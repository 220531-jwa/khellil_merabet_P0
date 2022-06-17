package services;

import java.util.List;

import models.User;
import repositories.UserDAOP0;

// this UserService class is where we write methods
// to perform any business logic that our app needs to function 
// appropriate
public class UserService {

// login an existing user
	
private static UserDAOP0 userDao = new UserDAOP0();
	
	
	//login
	public User login(String username, String password) {
		
		User u = userDao.getUserByUsername(username);
		
		if (u.getPassword().equals(password)) {
			return u;
		}
		return null;
	}

//	public User updatePassword(int id, String password) {
//		// check if that user exists
//		return userDao.updateUserPassword(id, password);
//	}

	// register / create user
	public User createUser(User u) {
		User createdUser = userDao.createUser(u);
		return createdUser;
	}
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	
	public User getUserById(int id) throws Exception {
		// this is where you could put some business logic 
		// for example checking if the User returned by userDao.getUserById(id) is null 
		User u = userDao.getUserById(id);

		if (u == null) {
			throw new Exception("User not found");
		}

		return u;
	}

	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}

	public void updateUser(int id, User uChanged) {
		userDao.updateUser (id, uChanged);
	}
	
}
