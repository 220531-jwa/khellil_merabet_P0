package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;
import utils.ConnectionUtilP0;

public class UserDAOP0 {

	private static ConnectionUtilP0 cu = ConnectionUtilP0.getConnectionUtil();

	public User createUser(User u) {

		String sql = "insert into users values (default, ?, ?, ?, ?) returning *";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getUsername());
			ps.setString(4, u.getPassword());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new User(
						rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("username"),
						rs.getString("pass")
						);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<User> getAllUsers() {
		// create an empty array list that will hold all the users returned from the database
		List<User> users = new ArrayList<>();
		
		// this is the sql statement that we'll be executing
		String sql = "select * from users";
		
		// try with resources - this will auto close any resources we need without a finally block
		try (Connection conn = cu.getConnection()) {
			// prepare our statement using the connection object
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// execute our statement and store the result set in a reference variable
			ResultSet rs = ps.executeQuery();
			
			// iterate over the result set, to get the values stored in each column and creating a Java Object with them
			while(rs.next()) {
				// use the getXXX() methods to retrieve the values stored in each column of this row of the result set
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				
				User u = new User(id, firstName, lastName, username, pass);
				
				users.add(u);
			}
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();	
		} 
		return null;
	}
	
	public User getUserById(int id) {
		
		String sql = "select * from users where id = ?"; // this question mark symbolizes and IN parameter for our statement
		
		try (Connection conn = cu.getConnection()) {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id); // here we are setting the the "?" in our sql string to be the int id that's passed in to this method as an argument
		
			ResultSet rs = ps.executeQuery();
			
			// if the result set has a row/record
			if (rs.next()) {
				return new User(
						rs.getInt("id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("username"),
						rs.getString("pass")
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null; // Optional Class -> can help avoid NullPointer Exceptions (if any one is curious)
	}
	
	public User getUserByUsername(String username) {

		return null;
	}

	public  User updateUser(int id, User uChanged) {

		String sql = "update users set first_name = ?, last_name = ?, username = ?, pass = ?, where id = ? returning *";
  
		try (Connection conn = cu.getConnection()) {
			
			//if (getUserById(id) == null) {
				//return null;
			//}
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uChanged.getFirstName());
			ps.setString(2, uChanged.getLastName());
			ps.setString(3, uChanged.getUsername());
			ps.setString(4, uChanged.getPassword());
			ps.setInt(5, uChanged.getId());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new User(
						rs.getInt("id"),
						rs.getString("firstname"),
						rs.getString("lastname"),
						rs.getString("username"),
						rs.getString("pass")
						);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteUser(int id) {
		String sql = "delete from users where id = ?";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public User updateUserPassword(int id, String pass) {
//
//		String sql = "update users set pass = ? where id = ? returning *";
//
//		try (Connection conn = cu.getConnection()) {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, pass);
//			ps.setInt(2, id);
//
//			ResultSet rs = ps.executeQuery();
//
//			if (rs.next()) {
//				return new User(
//						rs.getInt("id"),
//						rs.getString("first_name"),
//						rs.getString("last_name"),
//						rs.getString("username"),
//						rs.getString("pass")
//						);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
