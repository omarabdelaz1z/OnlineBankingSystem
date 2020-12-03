package DAO;

import Entities.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionDAO {
    private static final String depositMoneyIntoAccount = "UPDATE accounts SET CurrentBalance = CurrentBalance + ? WHERE CustomerID = ?";
    private static final String withdrawMoneyFromAccount = "UPDATE accounts SET CurrentBalance = CurrentBalance - ? WHERE CustomerID = ?";
    private static final String createTransaction = "INSERT INTO transactions (TransactionDate, Amount, SenderID, RecipientID) values(?, ?, ?, ?)";
    private static final String getTransactions = "SELECT * FROM transactions WHERE senderID = ?";
    private PreparedStatement depositStatement;
    private PreparedStatement withdrawStatement;
    private PreparedStatement transactionCreation;
    private PreparedStatement transactions;

    private Connection connection;

    public TransactionDAO(Connection connection){
        try {
            this.connection = connection;
            depositStatement = connection.prepareStatement(depositMoneyIntoAccount);
            withdrawStatement = connection.prepareStatement(withdrawMoneyFromAccount);
            transactionCreation = connection.prepareStatement(createTransaction, PreparedStatement.RETURN_GENERATED_KEYS);
            transactions = connection.prepareStatement(getTransactions);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public Transaction deposit(String senderID, String recipientID, double givenAmount) throws SQLException {
        Transaction transaction = new Transaction();

        depositStatement.setDouble(1, givenAmount);
        depositStatement.setInt(2, Integer.parseInt(recipientID));

        withdrawStatement.setDouble(1, givenAmount);
        withdrawStatement.setInt(2, Integer.parseInt(senderID));

        if(depositStatement.executeUpdate() > 0)
        {
            transactionCreation.setDate(1, transaction.getTransactionDate());
            transactionCreation.setDouble(2, givenAmount);
            transactionCreation.setInt(3, Integer.parseInt(senderID));
            transactionCreation.setInt(4, Integer.parseInt(recipientID));
            transactionCreation.executeUpdate();

            try (ResultSet generatedKeys = transactionCreation.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transaction.setTransactionID(Long.toString(generatedKeys.getLong(1)));
                    transaction.setAmount(givenAmount);
                    transaction.setSenderID(senderID);
                    transaction.setRecipientID(recipientID);
                    return transaction;
                }

                else {
                    throw new SQLException("Error Occurred During Transaction.");
                }
            }
        }
        return null;
    }

    public Transaction withdraw(String senderID, String recipientID, double givenAmount) throws SQLException {
        Transaction transaction = new Transaction();

        withdrawStatement.setDouble(1, givenAmount);
        withdrawStatement.setInt(2, Integer.parseInt(recipientID));

        depositStatement.setDouble(1, givenAmount);
        depositStatement.setInt(2, Integer.parseInt(senderID));

        if(withdrawStatement.executeUpdate() > 0)
        {
            depositStatement.executeUpdate();

            transactionCreation.setDate(1, transaction.getTransactionDate());
            transactionCreation.setDouble(2, givenAmount);
            transactionCreation.setInt(3, Integer.parseInt(senderID));
            transactionCreation.setInt(4, Integer.parseInt(recipientID));
            transactionCreation.executeUpdate();

            try (ResultSet generatedKeys = transactionCreation.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transaction.setTransactionID(Long.toString(generatedKeys.getLong(1)));
                    transaction.setAmount(givenAmount);
                    transaction.setSenderID(senderID);
                    transaction.setRecipientID(recipientID);
                    return transaction;
                }

                else {
                    throw new SQLException("Error Occurred During Transaction.");
                }
            }
        }
        return null;
    }

    public ArrayList<Transaction> getAccountTransactions(String customerID){
        ArrayList<Transaction> accountTransactions = new ArrayList<>();
        try{
            transactions.setInt(1, Integer.parseInt(customerID));
            ResultSet resultSet = transactions.executeQuery();
            while(resultSet.next()){
                Transaction transaction = new Transaction();

                transaction.setSenderID(customerID);
                transaction.setRecipientID(resultSet.getString("RecipientID"));
                transaction.setTransactionID(resultSet.getString("TransactionID"));
                transaction.setAmount(resultSet.getDouble("Amount"));
                transaction.setTransactionDate(resultSet.getDate("TransactionDate"));

                accountTransactions.add(transaction);
            }
            return accountTransactions;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
