package onlinefood;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




public class JdbcConnectionClass implements CustomerDao
{
	@Override
	public List<Customer> findAll() throws ClassNotFoundException, SQLException 
	{
		ArrayList<Customer> customerlist=new ArrayList<Customer>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/10398batch","root","Root");
		PreparedStatement ps=con.prepareStatement("select * from customer");
		
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
		
			 Customer cust=new Customer();
			 cust.setId(rs.getLong(1));
			 cust.setFirstName(rs.getString(2));
			 cust.setLastName(rs.getString(3)); 
			 cust.setPhoneNumber(rs.getLong(4));
			  cust.setEmail(rs.getString(5)); 
			  cust.setAddress(rs.getString(6));
			 cust.setPassword(rs.getString(7));
			 customerlist.add(cust);
		}
		return customerlist;
	}

		@Override
		public void insertNew(Customer c) throws ClassNotFoundException, SQLException {
			
			try 
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/10398batch","root","Root");
				PreparedStatement ps=con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?)");
				ps.setLong(1, c.getId());
				ps.setString(2, c.getFirstName());
				ps.setString(3, c.getLastName());
				ps.setLong(4, c.getPhoneNumber());
				ps.setString(5, c.getEmail());
				ps.setString(6, c.getAddress());
				ps.setString(7, c.getPassword());
				ps.setBoolean(8,c.isAdmin());
				ps.executeUpdate();
				con.close();
				System.out.println("inserted successfully");
			}
			catch (Exception ex) 
			{
				if(ex.getMessage().contains("doesn't exist")) {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/10398batch","root","Root");
					Statement st=con.createStatement();
					String query="create table customer(id long,firstName varchar(80),lastName varchar(80),phoneNumber long,email varchar(80),address varchar(80),password varchar(80),isAdmin TinyInt(1))";
					st.execute(query);
					con.close();
					System.out.println("Created Sucessfully");
				}
			}
			
		}

		
	@Override
	public void updateCustomer(Customer c) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/10398batch","root","Root");
		PreparedStatement ps=con.prepareStatement("update user set firstname=?,lastname=?,phoneNumber=?,email=?,address=? where Id=?");
		ps.setLong(1, c.getId());
		ps.setString(2, c.getFirstName());
		ps.setString(3, c.getLastName());
		ps.setLong(4,c.getPhoneNumber());
		ps.setString(5, c.getEmail());
		ps.setString(6,c.getAddress());
		
		ps.executeUpdate();
		con.close();
		System.out.println("update successfully");		
	}
	
	@Override
	public void viewCustomerById(Customer c) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/10398batch","root","Root");
		PreparedStatement ps=con.prepareStatement("select * from user where Id=?");	
		ps.setLong(1, c.getId());
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			System.out.println(rs.getInt(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(3));
			System.out.println(rs.getString(4));
		}
	}

	@Override
	public void deleteCustomerById(long Id) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/10398batch","root","Root");
		PreparedStatement ps=con.prepareStatement("delete from user where Id=?");
		
		
		ps.setLong(1, Id);
		ps.executeUpdate();
		con.close();
		System.out.println("Deleted successfully");		
		
	}
	
	@Override
	public void deleteAllCustomer() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/10398batch","root","Root");
		PreparedStatement ps=con.prepareStatement("truncate table customer");
		
		
		ps.executeUpdate();
		con.close();
		System.out.println("Delete all successfully");		
		
	}

	/*
	 * public Boolean veirfyEmail(Customer cust) throws ClassNotFoundException,
	 * SQLException {
	 * 
	 * if(cust.getEmail()!= null) { ArrayList<Customer> customerlist=new
	 * ArrayList<Customer>(); Class.forName("com.mysql.jdbc.Driver"); Connection
	 * con=DriverManager.getConnection("jdbc:mysql://localhost:3306/10398batch",
	 * "root","Root"); PreparedStatement
	 * ps=con.prepareStatement("select * from customer where customer.email ="+cust.
	 * getEmail()); ResultSet rs=ps.executeQuery(); if(rs.next()) { String s =
	 * rs.getString(3); return false; } else { return true; } } return false; }
	 */

	public void verifyUser(String userEmail, String password) throws ClassNotFoundException {
		OnlineFoodCore foodcore = new OnlineFoodCore();
		OnlineFood menuClass = new OnlineFood();
		Customer cust=new Customer();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/10398batch","root","Root");
			PreparedStatement ps=con.prepareStatement("select * from customer where customer.email=?");
			ps.setString(1, userEmail);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString(7));
				
				
				if(rs.getString(7).equals(password)) {
				    cust.setId(rs.getLong(1));
				    cust.setFirstName(rs.getString(2));
				    cust.setLastName(rs.getString(3));
				    cust.setPhoneNumber(rs.getLong(4));
				    cust.setEmail(rs.getString(5));
				    cust.setAddress(rs.getString(6));
				    cust.setPassword(rs.getString(7));
				    cust.setAdmin(rs.getBoolean(8));
					menuClass.menuPage(cust);
				}
				else {
					System.out.println("Password Incorrect, Please try again");
					
					foodcore.login();
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
		}
