package onlinefood;

import java.sql.SQLException;
import java.util.Scanner;

public class OnlineFood {

	public void menuPage(Customer cust) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner (System.in);
		
		System.out.println("------------------------------------------------");
		System.out.println("----------------- Online Food Delivery Management--------------------");
		System.out.println("------------------------------------------------");
		
		String ofd;
		OnlineFoodCore ofc=new OnlineFoodCore();
		
		System.out.println("--------------------------------------------------");
		System.out.println("----------------Select Your Choice----------------");
		System.out.println("---------------------------------------------------");
		String ofd1;
	 if(cust.isAdmin())
	 {
		System.out.println("\t\t 1)View Food \r\n"
				+ "\t\t 2)Add Food \r\n"
				+ "\t\t 3)Remove Food\r\n"
				+ "\t\t 4)Modify Food ");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("Enter your choice:");
        int choice1=sc.nextInt();
		switch(choice1)
		{
		
		
		  case 1: ofc.viewFood(cust); 
		  break; 
		  case 2: ofc.addFood(cust);
		  break; 
		  case 3: ofc.removeFood(cust); 
		  break; 
		  case 4:ofc.modifyfood(cust); 
		  break;  
		  default:System.out.println("Wrong choice!!");
		 
		}
		}
		/*
		 * else { ofc.viewFood(cust); }
		 * System.out.println("Do you want to continue? (Y-Yes / N-No)"); ofd=sc.next();
		 */
       }
    
}
		