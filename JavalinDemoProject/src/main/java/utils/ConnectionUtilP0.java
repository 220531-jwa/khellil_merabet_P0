package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// This class will define the methods needed to create a connection to our DB
// we are going to make ConnectionUtil using the Singleton Design Pattern
// to ensure that only one instance of the class exists throughout the program
public class ConnectionUtilP0 {

	private static ConnectionUtilP0 cu;
	private static Properties dbProps;

	// private constructor
	private ConnectionUtilP0() {
		// initialize the Properties object to hold our db credentials
		dbProps = new Properties();

		// Stream the credentials from our connection.properties file to this Object
		InputStream props = ConnectionUtilP0.class.getClassLoader().getResourceAsStream("connection.properties");

		try {
			dbProps.load(props);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	// public getter to return us an instance of this class -> a ConnectionUtil
	public static synchronized ConnectionUtilP0 getConnectionUtil() {
		// first check if an instance does not already exists
		if (cu == null) {
			cu = new ConnectionUtilP0();
		}
		// otherwise return the existing instance
		return cu;
	}

	// Method to actually establish a connection to the db
	public Connection getConnection() {

		Connection conn = null;

		// if you're getting Driver not found or something similar - here's a hot fix
		// this is forcing our PostgreeSQL Driver to load
		try {
			Class.forName(dbProps.getProperty("driver"));
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}



		// get our credentials from the ConnectionUtil's properties object that we created in the constructor
		String url = dbProps.getProperty("url");
		String username = dbProps.getProperty("username");
		String password = dbProps.getProperty("password");

		// use those credentials and the DriverManager to connect to our DB instance
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}

