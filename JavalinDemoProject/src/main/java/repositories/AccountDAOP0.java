package repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Account;

//import models.User;
import utils.ConnectionUtilP0;

public class AccountDAOP0 {


		private static ConnectionUtilP0 cu = ConnectionUtilP0.getConnectionUtil();

		public Account createAccount(Account a) {

			String sql = "insert into accounts values (?, ?, ?, ?) returning *";

			try (Connection conn = cu.getConnection()) {
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setInt(1, a.getAccountid());
				ps.setString(2, a.getType());
				ps.setDouble(3, a.getBalance());
				ps.setInt(4, a.getUserid());

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					return new Account(
							rs.getInt("accountid"),
						
							rs.getString("type"),
							rs.getDouble("balance"),
							rs.getInt("userid")
							);	
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return null;
		}
		
		//  ************************************

		public List<Account> getAllAccounts() {
			// create an empty array list that will hold all the users returned from the database
			List<Account> accounts = new ArrayList<>();
			
			// this is the SQL statement that we'll be executing
			String sql = "select * from accounts";
			
			// try with resources - this will auto close any resources we need without a finally block
			try (Connection conn = cu.getConnection()) {
				// prepare our statement using the connection object
				PreparedStatement ps = conn.prepareStatement(sql);
				
				// execute our statement and store the result set in a reference variable
				ResultSet rs = ps.executeQuery();
				
				// iterate over the result set, to get the values stored in each column and creating a Java Object with them
				while(rs.next()) {
					// use the getXXX() methods to retrieve the values stored in each column of this row of the result set
					int accountid = rs.getInt("accountid");
					String type = rs.getString("type");
					double balance = rs.getDouble("balance");
					int userid = rs.getInt("userid");
					
					Account a = new Account(accountid, type, balance, userid);
					
					accounts.add(a);
				}
				return accounts;
				
			} catch (SQLException e) {
				e.printStackTrace();	
			} 
			return null;
		}
		
		public Account getAccountById(int accountid) {
			
			String sql = "select * from accounts where accountid = ?"; // this question mark symbolizes and IN parameter for our statement
			
			try (Connection conn = cu.getConnection()) {
				
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, accountid); // here we are setting the the "?" in our SQL string to be the int id that's passed in to this method as an argument
			
				ResultSet rs = ps.executeQuery();
				
				// if the result set has a row/record
				if (rs.next()) {
					return new Account(
							rs.getInt("accountid"),
							rs.getString("type"),
							rs.getDouble("balance"),
							rs.getInt("userid")
							);	
						}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null; // Optional Class -> can help avoid NullPointer Exceptions (if any one is curious)
		}
		

		public Account getAccountByUserId(int id) {
			
			//String sql = "select * from accounts where user_id = ?"; // this question mark symbolizes and IN parameter for our statement
			String sql = "select * from accounts u left join accounts a on u.id = a.userid where u.id = ?";		
			try (Connection conn = cu.getConnection()) {
				
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, id); // here we are setting the the "?" in our sql string to be the int id that's passed in to this method as an argument
			
				ResultSet rs = ps.executeQuery();
				
				// if the result set has a row/record
				if (rs.next()) {
					return new Account(
							rs.getInt("accountid"),
							
							rs.getString("type"),
							rs.getDouble("balance"),
							rs.getInt("userid")
							);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null; 
		}

		
		
		
		public void updateAccount(Account aChangeBalance) {
			
			String sql = "update accounts set balance = ? where userid = ? and id = ?";

			//String sql = "update accounts set accountid = ?, type = ?, balance = ?  where userId = ? and id = ?";
            
			try (Connection conn = cu.getConnection()) {
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setInt(1, aChangeBalance.getAccountid());
	            ps.setString(2, aChangeBalance.getType());
				ps.setDouble(3, aChangeBalance.getBalance());
				ps.setInt(4, aChangeBalance.getUserid());

				ps.executeUpdate();


			   } catch (SQLException e) {
				e.printStackTrace();
			}

		}
		

		public void deleteAccount(int id) {
			String sql = "delete from accounts where accountid = ?";
           //  accountId or accountid as in my DB?????
			try (Connection conn = cu.getConnection()) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
 }


