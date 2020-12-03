package JDBC.Connection;

import DAO.AccountDAO;

import java.sql.Connection;

public class Main {
	public static void main(String[] args) {

		DatabaseConnection connection = DatabaseConnection.getInstance();
		Connection con = connection.getConnection();

		DAO.AccountDAO dao = new AccountDAO(con);
		//Entities.Account account = dao.getAccount("1");

		System.out.println(dao.getAccountBalance("1"));

/*
		int ID = 1;
		int PIN = 8618;
		dbConnection connection = dbConnection.getInstance();
		Connection con = connection.getConnection();

		DAO.CustomerDAO dao = new DAO.CustomerDAO(con);
		Entities.Customer customer = dao.validateCredentials(ID, PIN);

		if(customer != null) {
			System.out.println(customer);
		}

		System.out.println(customer.getAccounts());
*/

	}
}