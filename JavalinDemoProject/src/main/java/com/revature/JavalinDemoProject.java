package com.revature;
import io.javalin.Javalin;
import controllers.AccountController;
import controllers.UserController;

import static io.javalin.apibuilder.ApiBuilder.*;
//import io.javalin.http.Context;
//import io.javalin.http.Handler;

public class JavalinDemoProject {
 
		
		public static void main(String[] args) {
			
			Javalin app = Javalin.create();
			//Javalin app = Javalin.create().start(8085);
			
			app.start(8089);
			
			// Javalin provides us with a Context Class (ctx) that represents information 
			// about BOTH the Http Request AND Http Response Objects
			// we'll be using methods from the context class to handle our incoming http requests
			// and to send our http resonses
			
			// lambdas - introduced functional programming to Java
			// (parameter) -> {// implementation}
			
			app.routes(() -> {
				path("/users", () -> {
					get(UserController::getAllUsers);
				    post(UserController::createNewUser);
					path("/{id}", () -> {
						get(UserController::getUserById);
						delete(UserController::deleteUser);
						put(UserController::updateUser); 
						//patch(UserController::updatePassword);
					
						path("/accounts", () -> {
							get(AccountController::getAllAccounts);
							//get(AccountController::getAccountById);
							post(AccountController::createAccount);
							path("/{accountid}", () -> {
								get(AccountController::getAccountById);
								delete(AccountController::deleteAccount);
								put(AccountController::updateAccount); 
								
							
							});		
						});
						
					});
				});
			
			});
//		
//			app.exception(Exception.class, (e, ctx) -> {
//			    ctx.status(404);
//			    ctx.result("User not found");
//			});



			app.get("/test", ctx -> {
				ctx.status(200);
				String name = ctx.queryParam("name");
				ctx.result("Test successful! Hello " + name);
			});
			
			app.get("/{name}", ctx -> {
				ctx.status(200);
				String name = ctx.pathParam("name");
				ctx.result("Hello, " + name);
			});
			
		
			
		}
}
	
