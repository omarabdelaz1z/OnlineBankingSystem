package ServletControllers;

import DAO.AccountDAO;
import JDBC.Connection.DatabaseConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/Account")
public class Account extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Connection con = connection.getConnection();
        HttpSession session = request.getSession();

        Entities.Customer currentCustomer = (Entities.Customer) session.getAttribute("currentCustomer");

        DAO.AccountDAO dao = new AccountDAO(con);
        Entities.Account account = dao.addAccount(currentCustomer.getCustomerID());

        if(account != null){
            currentCustomer.setCustomerAccount(account);
            session.setAttribute("currentCustomer", currentCustomer);
            response.sendRedirect("home.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DatabaseConnection instance = DatabaseConnection.getInstance();
        AccountDAO dao = new AccountDAO(instance.getConnection());

        HttpSession session = request.getSession();
        Entities.Customer currentCustomer = (Entities.Customer) session.getAttribute("currentCustomer");
        Entities.Account customerAccount = dao.getAccount(currentCustomer.getCustomerID());

        currentCustomer.setCustomerAccount(customerAccount);
        session.setAttribute("currentCustomer", currentCustomer);
        response.sendRedirect("MyAccount.jsp");
    }
}
