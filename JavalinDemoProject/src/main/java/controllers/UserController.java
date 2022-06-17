package controllers;

import java.util.List;

import services.UserService;
import io.javalin.http.Context;
import models.User;
public class UserController {
	
	private static UserService us = new UserService();
	public static void getAllUsers(Context ctx) {
		ctx.status(200);
		List<User> users = us.getAllUsers();
		ctx.json(users);
	}
	
	public static void createNewUser(Context ctx) {
		ctx.status(201);
		User userFromRequestBody = ctx.bodyAsClass(User.class);
		User u = us.createUser(userFromRequestBody); // unmarshalling
		ctx.json(u);
	}

	public static void getUserById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
	
		User u = null;
	
		try {
			u = us.getUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
		
	}

	public static void deleteUser(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		us.deleteUser(id);
	}

	public static void updateUser(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		User uChanged = ctx.bodyAsClass(User.class); //unmarshalling
		//System.out.println("updateUser -= " + uChanged);
		us.updateUser(id, uChanged);
	}
//	public static void updatePassword(Context ctx) {
//		int id = Integer.parseInt(ctx.pathParam("id"));
//		User u = ctx.bodyAsClass(User.class); // {"password": "newPassword"}
//		//System.out.println(u.getPassword());
//		us.updatePassword(id, u.getPassword());
//	}	
	
	
}


