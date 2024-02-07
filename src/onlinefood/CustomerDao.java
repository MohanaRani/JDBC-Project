package onlinefood;

import java.sql.SQLException;
import java.util.List;


public interface CustomerDao 

     {
	public List<Customer>findAll() throws ClassNotFoundException, SQLException;
	public void insertNew(Customer c) throws ClassNotFoundException, SQLException;
	public void updateCustomer(Customer c) throws ClassNotFoundException, SQLException;
   public void viewCustomerById(Customer c) throws ClassNotFoundException, SQLException;
   public void deleteCustomerById(long Id) throws ClassNotFoundException, SQLException;
   public void deleteAllCustomer() throws ClassNotFoundException, SQLException;
}
