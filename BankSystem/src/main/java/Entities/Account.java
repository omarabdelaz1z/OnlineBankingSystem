package Entities;

import java.sql.Date;
import java.util.ArrayList;

public class Account {
	private String accountID;
	private Date creationDate;
	private double currentBalance;
	private String customerID;

	private ArrayList<Transaction> transactions;

	public Account(String customerID){
		transactions = new ArrayList<>();
		currentBalance = 1000;
		creationDate = new Date(new java.util.Date().getTime());
		this.customerID = customerID;
	}
	
	public Account() {

	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", creationDate=" + creationDate + ", currentBalance="
				+ currentBalance + ", customerID=" + customerID + "]" + "\n";
	}
	
}
