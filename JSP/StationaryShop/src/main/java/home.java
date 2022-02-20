import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(urlPatterns = {"/login"})
public class home extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(500);
		out.print(
				"<html><head><title>Products</title><link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3' crossorigin='anonymous'></head>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/stationary";
			Connection conn = DriverManager.getConnection(url, "root", "201102");
			Statement stm = conn.createStatement();
			response.setContentType("text/html");
			String mobile = (String) request.getParameter("mobile");
			String dob = (String) request.getParameter("dob");
		
			String cusId = null;
			String uname = null;
			String email = null;
			String last = null;
			
			String query = "select * from user where mobile = '" + mobile + "' and dob = '" + dob + "'";
			ResultSet rs = stm.executeQuery(query);
			if (rs.next()) {
				out.print("<script type='text/javascript'>function preventBack() {window.history.forward();}setTimeout('preventBack()', 0);window.onunload = function () { null };</script>");
				out.print("<nav class='navbar navbar-dark bg-primary'><div class='container-fluid'><a class='navbar-brand' href='#'>Stationary Shop</a><a class='navbar-brand navbar-right' href='logout'>Log out</a></div></nav>");
				cusId = rs.getString("id");
				uname = rs.getString("name");
				email = rs.getString("email");
				dob = rs.getString("dob");
				last = rs.getString("last_login");

				query = "select * from admin where id = '" + cusId + "'";
				ResultSet rs1 = stm.executeQuery(query);
				if (rs1.next()) {
					session.setAttribute("mob", mobile);
					response.sendRedirect("admin.jsp");
				}
				String s = "<form class='container' style='max-width:50%;'><table class='table'><tr><th>Your Details</th></tr><tr><th>Name</th><td>"
						+ uname + "</td></tr><tr><th>Email</th><td>" + email + "</td></tr><tr><th>Contact No</th><td>"
						+ mobile + "</td></tr><tr><th>DOB</th><td>" + dob + "</td></tr><tr><th>Last Login</th><td>"
						+ last
						+ "</td></tr><tr><th><a href='changedetail.jsp' class='btn btn-primary'>Edit Your Detail</button></th></tr></table>";
				out.print(s);

				String s3 = "<a class='btn btn-primary' href='allorder'>All order details</a>";
				String s4 = "<a class='btn btn-primary' href='itemlist'>Buy From Our Store</a>";
				String s5 = "<br><br><a class='btn btn-primary' href='feedback.jsp'>Raise Issue?</a>";
				String s6 = "<a class='btn btn-primary' href='pastfeed'>Your Issues</a>";
				out.print(s3 + " " + s4 + s5 + " " + s6);
				session.setAttribute("mob", mobile);
				session.setAttribute("name", uname);
				Date d = new Date();
				String q = "update user set last_login='" + d + "' where mobile='" + mobile + "'";
				stm.executeUpdate(q);
			}

			else {
				request.setAttribute("logerror", "Mobile No and Password does not match!!!");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
//				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
