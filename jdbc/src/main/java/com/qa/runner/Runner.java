package com.qa.runner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import com.qa.dbconnection.DBConnection;
import com.qa.utils.DBConfig;

public class Runner {
	
	private static Scanner scanner = new Scanner(System.in);
	private static boolean flag = true;
	
	public static void main(String[] args) throws IllegalStateException {

		try {
		DBConnection dbc = new DBConnection();
		do {
		System.out.println("SELECT ANY OPTION TO CONTINUE\n1\tADD PERSON\n2\tUPDATE PERSON\n3\tDELETE PERSON\n4\tREAD PERSON\n5\tREAD ALL\n6\tEXIT PROGRAM");
		String res1 = scanner.nextLine();
		
		switch(res1){
		case "1":
			System.out.println("Enter a name for new person");
			String newName = scanner.nextLine();
			dbc.create(newName);
			break;
		case "2":
			System.out.println("Enter the ID of the person you'd like to update");
			Integer id = Integer.parseInt(scanner.nextLine());
			System.out.println("Enter the person's new name");
			String updatedName = scanner.nextLine();
			dbc.update(id, updatedName);
			break;
		case "3":
			System.out.println("Enter the ID of the person you'd like to delete");
			dbc.delete(Integer.parseInt(scanner.nextLine()));
			break;
		case "4":
			System.out.println("Enter the ID of the person you'd like to read");
			dbc.read(Integer.parseInt(scanner.nextLine()));
			break;
		case "5":
			dbc.read();
			break;
		case "6":
			flag = false;
			System.out.println("Exiting program");
			dbc.tearDown();
			break;
		default:
			System.out.println("Please enter a valid seclection");
			break;
		}
		}while(flag);
		scanner.close();

	} catch(SQLException e) {
		e.printStackTrace();
	}

}
}
