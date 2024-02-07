package onlinefood;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginPage {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner (System.in);
		System.out.println("------------------------------------------------");
		System.out.println("----------------- Login Page --------------------");
		System.out.println("------------------------------------------------");
		
		String ofd;
		OnlineFoodCore ofc=new OnlineFoodCore();
			System.out.println("\t\t 1)Login \r\n"
				             + "\t\t 2)Sign Up"); 
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("Enter your choice:");
		int choice=sc.nextInt();
		switch(choice)
		{
		
		case 1:	ofc.login();
				break;
		case 2:	ofc.signUp();
				break;
	    default:System.out.println("Wrong choice!!"); 		   
	
}
}	
	}
		