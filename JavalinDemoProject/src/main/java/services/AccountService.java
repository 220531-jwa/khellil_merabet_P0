package services;

import java.util.List;

import models.Account;
import repositories.AccountDAOP0;

public class AccountService {

	// login an existing user
		
	private static AccountDAOP0 accountDao = new AccountDAOP0();
		
		
		//login
	//	public Account login(String username, String password) {
			
	//		Account a = accountDao.getAccountByUsername(username);
			
	//		if (a.getPassword().equals(password)) {
	//			return a;
	//		}
	//		return null;
	//	}

		//public Account updatePassword(int id, String password) {
			// check if that user exists
			//return AccountDAOP0.updateAccountPassword(id, password);
	//	}

		
		
		// register / create user
		public Account createAccount(Account a) {
			Account createdAccount = accountDao.createAccount(a);
			return createdAccount;
		}
		public List<Account> getAllAccounts() {
			return accountDao.getAllAccounts();
		}

		
		public Account getAccountByUserId(int id) throws Exception {
			// this is where you could put some business logic 
			// for example checking if the User returned by userDao.getUserById(id) is null 
			Account a = accountDao.getAccountById(id);

			if (a == null) {
				throw new Exception("Account not found");
			}

			return a;
		}
		
		

		public void deleteAccount(int accountId) {
			accountDao.deleteAccount(accountId);
		}

		public void updateAccount(Account uChanged) {
			accountDao.updateAccount(uChanged);
		}
		

}
