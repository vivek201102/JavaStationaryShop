import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/itemlist"})
public class items extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		out.print("<html><head><title>Products</title><link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3' crossorigin='anonymous'></head>");

		try {
			out.print("<nav class='navbar navbar-dark bg-primary'><div class='container-fluid'><a class='navbar-brand' href='#'>Stationary Shop</a><a class='navbar-brand navbar-right' href='logout'>Log out</a></div></nav>");
//			out.print("<script type='text/javascript'>function preventBack() {window.history.forward();}setTimeout('preventBack()', 0);window.onunload = function () { null };</script>");

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/stationary";
			Connection conn = DriverManager.getConnection(url, "root", "201102");
			Statement stm = conn.createStatement();
			String s1 = "<form action='cart' class='container' style='max-width:50%;' method='POST'><h1>Avaliable Products:</h1><h3>Book :-</h3><table class='table'><thead class='thead-dark'><tr><th>ID</th><th>Name</th><th>Description</th><th>Author</th><th>Pages</th><th>Price</th><th>Stock</th><th>BUY</th></tr></thead>";
			out.print(s1);
			String q = "select * from book;";
			ResultSet rs = stm.executeQuery(q);
			while (rs.next()) {
				String s2 = "<tr><td>" + rs.getString("pId") + "</td><td>" + rs.getString("pName") + "</td><td>" + rs.getString("description") + "</td><td>" + rs.getString("author") + "</td><td>" + rs.getString("noOfpages") + "</td><td>" + rs.getString("price") + "</td><td>" + rs.getString("stock") + "</td><td><input type='number' name='"+ rs.getString("pId")+"' max ='"+ rs.getInt("stock")+"' min = '0'></td></tr>";
				out.print(s2);
			}
			out.print("</table>");
			
			out.print("<h3>Pen :-</h3>");
			s1 = "<table class='table'><thead class='thead-dark'><tr><th>ID</th><th>Name</th><th>Description</th><th>Company Name</th><th>Color</th><th>Price</th><th>Stock</th><th>BUY</th></tr></thead>";
			out.print(s1);
			
			q = "select * from pen;";
			rs = stm.executeQuery(q);
			while (rs.next()) {
				String s2 = "<tr><td>" + rs.getString("pId") + "</td><td>" + rs.getString("pName") + "</td><td>" + rs.getString("description") + "</td><td>" + rs.getString("compName") + "</td><td>" + rs.getString("color") + "</td><td>" + rs.getString("price") + "</td><td>" + rs.getString("stock") + "</td><td><input type='number' name='"+ rs.getString("pId")+"' max = '"+ rs.getInt("stock")+"' min = '0'></td></tr>";
				out.print(s2);
			}
			out.print("</table>");
			
			out.print("<h3>Desk :-</h3>");
			s1 = "<table class='table'><thead class='thead-dark'><tr><th>ID</th><th>Name</th><th>Description</th><th>Company Name</th><th>Material</th><th>Price</th><th>Stock</th><th>BUY</th></tr></thead>";
			out.print(s1);
			
			q = "select * from desk;";
			rs = stm.executeQuery(q);
			while (rs.next()) {
				String s2 = "<tr><td>" + rs.getString("pId") + "</td><td>" + rs.getString("pName") + "</td><td>" + rs.getString("description") + "</td><td>" + rs.getString("compName") + "</td><td>" + rs.getString("material") + "</td><td>" + rs.getString("price") + "</td><td>" + rs.getString("stock") + "</td><td><input type='number' name='"+ rs.getString("pId")+"' max = '"+ rs.getInt("stock")+"' min = '0'></td></tr>";
				out.print(s2);
			}
			out.print("</table>");
			
			out.print("<h3>Calculator :-</h3>");
			s1 = "<table class='table'><thead class='thead-dark'><tr><th>ID</th><th>Name</th><th>Description</th><th>Company Name</th><th>Type</th><th>Price</th><th>Stock</th><th>BUY</th></tr></thead>";
			out.print(s1);
			
			q = "select * from calc;";
			rs = stm.executeQuery(q);
			while (rs.next()) {
				String s2 = "<tr><td>" + rs.getString("pId") + "</td><td>" + rs.getString("pName") + "</td><td>" + rs.getString("description") + "</td><td>" + rs.getString("compName") + "</td><td>" + rs.getString("type") + "</td><td>" + rs.getString("price") + "</td><td>" + rs.getString("stock") + "</td><td><input type='number' name='"+ rs.getString("pId")+"' max = '"+ rs.getInt("stock")+"' min = '0'></td></tr>";
				out.print(s2);
			}
			out.print("</table><br><button class = 'btn btn-primary' type='submit'>Show Cart</button></form>");
			String home = "<div class='container' style='max-width:50%'><hr><h4 style='margin-right:0%;'><button class='btn btn-primary' onclick=\"history.back()\" >Go Back</button></h4></div>";
			out.print(home);

			
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
