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

@WebServlet(urlPatterns = {"/allcus"})
public class allcustomer extends HttpServlet{
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
			out.print("<nav class='navbar navbar-dark bg-primary'><div class='container-fluid'><a class='navbar-brand' href='#'>Stationary Shop</a><a class='navbar-brand navbar-right' href='index.jsp'>Log out</a></div></nav>");

			
			String start="<div class='container' style='max-width:50%;margin-top:4.2%;'>",start2; 
			
			out.print(start);

			String query = "select * from user u where u.id not in(select id from admin)";
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()){
				start2 = "<table border='2px solid green' class='table table-striped'><tr><th>Customer Unique Id</th><td>"+rs.getString("id")+"</td></tr><tr><th>Name</th><td>"+rs.getString("name")+"</td></tr><tr><th>Contact No.</th><td>"+rs.getString("mobile")+"</td></tr><tr><th>Customer Last login </th><td>"+rs.getString("last_login")+"</td></tr></table>";
				out.print(start2);
			}
			String home = "<button class='btn btn-primary' style='allign-item:center;' onclick='history.back()'>Go Back</button></div>";
			out.print(home);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
