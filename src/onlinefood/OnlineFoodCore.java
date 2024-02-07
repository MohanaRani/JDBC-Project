package onlinefood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Random;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Statement;

public class OnlineFoodCore {

	JdbcConnectionClass jdbc = new JdbcConnectionClass();
	OnlineFood ofood = new OnlineFood();
	
	public void login() throws ClassNotFoundException, SQLException 
	{
	Scanner sc = new Scanner(System.in);
	System.out.println("------------------Login Page-------------");
	
	System.out.println("UserEmail: ");
    String userEmail=sc.next();
	System.out.println("Password: ");
	String password=sc.next();
	System.out.println("-----------------------------------------");
	jdbc.verifyUser(userEmail,password);
	}

	public void signUp() throws ClassNotFoundException, SQLException {
		Scanner sc =new Scanner(System.in);
	System.out.println("--------------------Sign Up Page-------------------");
    long id=generatorId() ;
	System.out.println("FirstName : ");
	String firstName =sc.next();
	System.out.println("LastName : ");
	String lastname =sc.next();
	System.out.println("Phone Number");
	long phoneNumber =sc.nextLong();
	System.out.println("Email : ");
	String email=sc.next();
	System.out.println("Address :");
	String address=sc.next();
	System.out.println("Password : ");
	String password =sc.next();
	System.out.println("Confirm Password");
	String confirmPassword =sc.next();
	System.out.println("IsAdmin");
	boolean isAdmin=sc.nextBoolean();
	if (isPasswordConfirmed(password, confirmPassword)) {
        System.out.println("Password confirmed successfully!");
        Customer cust= new Customer(id, firstName,lastname,phoneNumber,email,address,password,isAdmin);
        	  jdbc.insertNew(cust);
        	 }
	//calling login method
	login();
}

	public int generatorId() {
	    Random r = new Random( System.currentTimeMillis() );
	    return ((1 + r.nextInt(2)) * 500 + r.nextInt(450));
	}
private static boolean isPasswordConfirmed(String password, String confirmPassword) {

    return password.equals(confirmPassword);
	
}


public void viewFood(Customer cust) throws ClassNotFoundException, SQLException {
	Scanner sc =new Scanner(System.in);
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/10398batch","root","Root");
	PreparedStatement ps=con.prepareStatement("select * from fooditems");
	ResultSet rs=ps.executeQuery();
        // Execute the query
        // Display food information
        System.out.println("Food Information:");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-5s %-20s %-15s %-10s%n", "foodid", "itemName", "Category", "Price","quantity");
        System.out.println("--------------------------------------------------");

        while (rs.next()) {
            int foodid = rs.getInt("foodId");
            String itemName = rs.getString("itemName");
            String category = rs.getString("category");
            double price = rs.getDouble("price");
            int quantity=rs.getInt("quantity");

            System.out.printf("%-5d %-20s %-15s %-10.2f%n", foodid, itemName, category, price,quantity);
        }
        System.out.println("--------------------------------------------------");
	    System.out.println("Do you to go to Main menu Yes or No");
	    String result =sc.next();
	    if(result.equalsIgnoreCase("Yes"))
	    {
	    	ofood.menuPage(cust);
	    }
	}
	
     catch (SQLException e) {
         e.printStackTrace();
     }
	
}

public void addFood(Customer cust) throws ClassNotFoundException, SQLException {
	Scanner sc = new Scanner(System.in);
    System.out.println("Enter the details for the new food item:");

    FoodItems newItems=new FoodItems();
    int foodId=generatorId() ;
    newItems.setFoodId(foodId);
    
    System.out.print("itemName: ");
    String itemName = sc.nextLine();
    
    newItems.setItemName(itemName);

    System.out.print("Category: ");
    String category = sc.nextLine();
    newItems.setCategory(category);

    System.out.print("Price: ");
    int price = sc.nextInt();
    newItems.setPrice(price);
    
    System.out.print("quantity: ");
    int quantity=sc.nextInt();
   newItems.setQuantity(quantity);
   
    try
    {
    Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/10398batch","root","Root");
	PreparedStatement ps=con.prepareStatement("insert into fooditems values(?,?,?,?,?)");
        ps.setInt(1,foodId);
        ps.setString(2, itemName);
        ps.setString(3, category);
        ps.setDouble(4, price);
        ps.setInt(5,quantity);

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("New food item added successfully!");
        } else {
            System.out.println("Failed to add the new food item.");
        }

    } catch (SQLSyntaxErrorException e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
		
		  if(e.getMessage().contains("doesn't exist")) 
		  {
			  Class.forName("com.mysql.cj.jdbc.Driver"); 
			  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/10398batch","root","Root"); 
			  java.sql.Statement st=con.createStatement(); 
			  String query="create table fooditems(foodId int,itemName varchar(80),category varchar(80),price int,quantity int)"; 
			  st.execute(query); 
			  con.close(); 
			  System.out.println("Created Sucessfully");
			 
		  }		 
    }
    System.out.println("-------------------------------------------------");
    System.out.println("Do you want to add more items :");
    System.out.println("Click on Yes or No route to main page :");
    String option = sc.next();
    System.out.println(option);
    if(option.equalsIgnoreCase("Yes"))
    ofood.menuPage(cust);
    else if (option.equalsIgnoreCase("no")) 
    {
   System.out.println("Existing....");
    }
}

public void removeFood(Customer cust) throws ClassNotFoundException , SQLException {
	Scanner sc = new Scanner(System.in);

    System.out.print("Enter the foodid of the food item to delete: ");
    int foodId = sc.nextInt();

    try  
    {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/10398batch","root","Root");
    	PreparedStatement ps=con.prepareStatement("DELETE FROM fooditems WHERE foodid = ?");
        ps.setInt(1, foodId);

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Food item deleted successfully!");
        } else {
            System.out.println("No food item found with the given ID.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    System.out.println("-------------------------------------------------");
    System.out.println("Do you want to add more items :");
    System.out.println("------------------------------------------------------");
    System.out.println("Click on Yes else  Click on No to route to main page :");
    String option = sc.next();
    System.out.println(option);
    if(option.equalsIgnoreCase("Yes"))
    ofood.menuPage(cust);
    }

public void modifyfood(Customer cust) throws ClassNotFoundException, SQLException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the details for the new food item:");

    FoodItems newItems = new FoodItems();

    System.out.print("Food Id: ");
    int foodId = sc.nextInt();
    newItems.setFoodId(foodId);

    System.out.print("itemName: ");
    String itemName = sc.next();
    newItems.setItemName(itemName);
    sc.nextLine();

    System.out.print("Category: ");
    String category = sc.nextLine();
    newItems.setCategory(category);

    System.out.print("Price: ");
    int price = sc.nextInt();
    newItems.setPrice(price);

    System.out.print("Quantity: ");
    int quantity = sc.nextInt();
    newItems.setQuantity(quantity);

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/10398batch", "root", "Root");
        PreparedStatement ps = con.prepareStatement("update fooditems set itemname=?, category=?, price=?, quantity=? where foodId=?");

        ps.setString(1, itemName);
        ps.setString(2, category);
        ps.setInt(3, price);
        ps.setInt(4, quantity);
        ps.setInt(5, foodId);

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Modified successfully!");
        } else {
            System.out.println("Failed to modify.");
        }

    } catch (SQLSyntaxErrorException e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
    }    System.out.println("-------------------------------------------------");
    System.out.println("Do you want to add more items :");
    System.out.println("------------------------------------------------------");
    System.out.println("Click on Yes else  Click on No to route to main page :");
    String option = sc.next();
    System.out.println(option);
    if(option.equalsIgnoreCase("Yes"))
    ofood.menuPage(cust);
}
}
