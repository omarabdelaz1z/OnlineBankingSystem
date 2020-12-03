package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
	private static final String getCustomer = "SELECT * FROM customers WHERE CustomerID = ? AND PIN = ?";
	private Connection connection;

	public CustomerDAO(Connection connection){
		this.connection = connection;
	}

	public Entities.Customer validateCredentials(int customerID, String PIN) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(getCustomer);
			preparedStatement.setInt(1, customerID);
			preparedStatement.setString(2, PIN);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				return getCustomer(resultSet);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Entities.Customer getCustomer(ResultSet resultSet) throws SQLException {
		Entities.Customer customer = new Entities.Customer();

		String customerID = resultSet.getString("CustomerID");
		String name = resultSet.getString("Name");
		String address  = resultSet.getString("Address");
		String phoneNumber = resultSet.getString("PhoneNumber");
		String PIN = resultSet.getString("PIN");
			
		customer.setCustomerID(customerID);
		customer.setName(name);
		customer.setAddress(address);
		customer.setPhoneNumber(phoneNumber);
		customer.setPIN(PIN);

		return customer;
	}
}
