package controllers;

import java.util.List;

import io.javalin.http.Context;
import models.Account;

import services.AccountService;

public class AccountController {
	
		
		private static AccountService as = new AccountService();
		
		public static void getAllAccounts(Context ctx) {
			ctx.status(200);
			List<Account> accounts = as.getAllAccounts();
			ctx.json(accounts);
		}
		
		public static void createAccount(Context ctx) {
     //	ctx.status(201);

			Account accountFromReqBody = ctx.bodyAsClass(Account.class);
			Account a = as.createAccount(accountFromReqBody); // unmarshalling
			if (a != null) {
				ctx.json(a);
				ctx.status(201);	
			}
			else {
				ctx.status(404);
			}
		}

		public static void getAccountById(Context ctx) {
			int accountid = Integer.parseInt(ctx.pathParam("accountid"));
			
		
			//Account a = null;
		
			try {
				Account a = null;
				a = as.getAccountByUserId(accountid);
			
			if(a != null) {
				//List<Account> a = as.getAccountByUserId(accountid);
				ctx.json(a);
				ctx.status(200);
			}
			   else {
				ctx.status(404);	
			    }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			//ctx.status(200);
			//ctx.json(a);
		}

		public static void deleteAccount(Context ctx) {
			int accountid = Integer.parseInt(ctx.pathParam("accountid"));
			as.deleteAccount(accountid);
		}

		public static void updateAccount(Context ctx) {
			Account aChanged = ctx.bodyAsClass(Account.class); //unmarshalling
			//System.out.println("updateAccount -= " + aChanged);
			as.updateAccount(aChanged);
		}
		

}
