package ServletControllers;

import JDBC.Connection.DatabaseConnection;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CustomerSession")

public class CustomerSession extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int ID = Integer.parseInt(request.getParameter("ID"));
		String PIN = request.getParameter("password");

		DAO.CustomerDAO dao = new DAO.CustomerDAO(DatabaseConnection.getInstance().getConnection());
		Entities.Customer customer = dao.validateCredentials(ID, PIN);

		if(customer != null) {
			System.out.println("Customer Found");
			HttpSession session = request.getSession();
			session.setAttribute("currentCustomer", customer);
			response.sendRedirect("home.jsp");
		}

		else {
			String errorMessage = "Please provide valid credentials.";
			request.getRequestDispatcher("login.html").include(request, response);
			out.println("<script> alert('" + errorMessage + "'); </script>");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession currentSession = request.getSession();
		currentSession.removeAttribute("currentCustomer");
		currentSession.invalidate();
		response.sendRedirect("login.html");
	}
}
