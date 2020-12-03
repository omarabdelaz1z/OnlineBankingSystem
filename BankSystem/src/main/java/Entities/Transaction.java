package Entities;

import java.sql.Date;

public class Transaction {
	private String transactionID;
	private Date transactionDate;
	private double amount;
	private String senderID;
	private String recipientID;

	public Transaction(){
		transactionDate = new Date(new java.util.Date().getTime());
	}

	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setRecipientID(String recipientID) {
		this.recipientID = recipientID;
	}

	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}

	public String getRecipientID() {
		return recipientID;
	}

	public String getSenderID() {
		return senderID;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"transactionID='" + transactionID + '\'' +
				", transactionDate=" + transactionDate +
				", amount=" + amount +
				", senderID='" + senderID + '\'' +
				", recipientID='" + recipientID + '\'' +
				'}';
	}
}