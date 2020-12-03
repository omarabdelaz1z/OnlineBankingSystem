package ServletControllers;

import DAO.TransactionDAO;
import Entities.Transaction;
import JDBC.Connection.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(name = "/TransactionController")
public class TransactionController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Entities.Customer currentCustomer = (Entities.Customer) session.getAttribute("currentCustomer");
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        Connection connection = databaseConnection.getConnection();
        TransactionDAO dao = new TransactionDAO(connection);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Entities.Customer currentCustomer = (Entities.Customer) session.getAttribute("currentCustomer");

        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        Connection connection = databaseConnection.getConnection();
        TransactionDAO dao = new TransactionDAO(connection);

        ArrayList<Transaction> accountTransactions = dao.getAccountTransactions(currentCustomer.getCustomerID());
        currentCustomer.getCustomerAccount().setTransactions(accountTransactions);

        session.setAttribute("currentCustomer", currentCustomer);
        response.sendRedirect("home.jsp");
    }
 }
