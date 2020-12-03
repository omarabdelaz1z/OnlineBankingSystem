package DAO;

import Entities.Account;
import java.sql.*;

public class AccountDAO {
	private static final String addAccount = "INSERT INTO accounts (CreationDate, CurrentBalance, CustomerID) VALUES (?, ?, ?)";
	private static final String getAccount = "SELECT * FROM accounts WHERE CustomerID = ?";
	private static final String getAccountBalance = "SELECT * FROM accounts WHERE CustomerID = ?";

	public Connection connection;

	public AccountDAO(Connection connection) {
		this.connection = connection;
	}

	public Entities.Account addAccount(String customerID) {
		Entities.Account account = new Entities.Account(customerID);

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(addAccount, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setDate(1, account.getCreationDate());
			preparedStatement.setDouble(2, account.getCurrentBalance());
			preparedStatement.setInt(3, Integer.parseInt(customerID));

			preparedStatement.executeUpdate();

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					account.setAccountID(Long.toString(generatedKeys.getLong(1)));
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}

			return account;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Entities.Account getAccount(String customerID) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(getAccount);
			preparedStatement.setInt(1, Integer.parseInt(customerID));

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Entities.Account customerAccount = new Account(customerID);

				String accountID = resultSet.getString("AccountID");
				Date creationDate = resultSet.getDate("CreationDate");
				double currentBalance = resultSet.getDouble("CurrentBalance");

				customerAccount.setAccountID(accountID);
				customerAccount.setCreationDate(creationDate);
				customerAccount.setCurrentBalance(currentBalance);

				return customerAccount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public double getAccountBalance(String customerID) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(getAccountBalance);
			preparedStatement.setInt(1, Integer.parseInt(customerID));
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next())
				return resultSet.getDouble("CurrentBalance");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;
	}
}

