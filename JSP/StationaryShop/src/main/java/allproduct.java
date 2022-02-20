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

@WebServlet(urlPatterns = {"/allprod"})
public class allproduct extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(500);
		
		try {
			out.print("<html><head><title>Products</title><link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3' crossorigin='anonymous'></head>"); 
			
			out.print("<nav class='navbar navbar-dark bg-primary'><div class='container-fluid'><a class='navbar-brand' href='#'>Stationary Shop</a><a class='navbar-brand navbar-right' href='login.jsp'>Log out</a></div></nav><br><br>");
		
			String s1="<div class='container' style='max-width:50%;'><h3>Book:-</h3><table border='2px solid brown' class='table table-striped'><thead class='thead-dark'><tr><th>ID</th><th>Name</th><th>Description</th><th>Author</th><th>Pages</th><th>Price</th><th>Stock</th></tr></thead>";
		out.print(s1);
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/stationary";
		Connection conn = DriverManager.getConnection(url,"root","201102");
		Statement stm = conn.createStatement();
		String q = "select * from book";
		ResultSet rs = stm.executeQuery(q);
		String s2;
		while(rs.next()){
		s2 = "<tr><td>"+rs.getString("pId")+"</td><td>"+rs.getString("pName")+"</td><td>"+rs.getString("description")+"</td><td>"+rs.getString("author")+"</td><td>"+rs.getString("noofpages")+"</td><td>"+rs.getString("price")+"</td><td>"+rs.getString("stock")+"</td></tr>";
		out.print(s2);
		}
		out.print("</table>");
		s1="<table border='2px solid brown' class='table table-striped'><h3>Calculator :-</h3><thead class='thead-dark'><tr><th>ID</th><th>Name</th><th>Description</th><th>Model</th><th>Companey</th><th>Price</th><th>Stock</th></tr></thead>";
		out.print(s1);
		q = "select * from calc";
		rs = stm.executeQuery(q);
		while(rs.next()){
		s2 = "<tr><td>"+rs.getString("pId")+"</td><td>"+rs.getString("pName")+"</td><td>"+rs.getString("description")+"</td><td>"+rs.getString("type")+"</td><td>"+rs.getString("compName")+"</td><td>"+rs.getString("price")+"</td><td>"+rs.getString("stock")+"</td></tr>";
		out.print(s2);
		}
		out.print("</table>");
		s1="<table border='2px solid brown' class='table table-striped'><h3>Desk :-</h3><thead class='thead-dark'><tr><th>ID</th><th>Name</th><th>Description</th><th>Type</th><th>Companey</th><th>Price</th><th>Stock</th></tr></thead>";
		out.print(s1);
		q = "select * from desk";
		rs = stm.executeQuery(q);
		while(rs.next()){
		s2 = "<tr><td>"+rs.getString("pId")+"</td><td>"+rs.getString("pName")+"</td><td>"+rs.getString("description")+"</td><td>"+rs.getString("material")+"</td><td>"+rs.getString("compName")+"</td><td>"+rs.getString("price")+"</td><td>"+rs.getString("stock")+"</td></tr>";
		out.print(s2);
		}
		out.print("</table>");
		s1="<table border='2px solid brown' class='table table-striped'><h3>Pen :-</h3><thead class='thead-dark'><tr><th>ID</th><th>Name</th><th>Description</th><th>Color</th><th>Companey</th><th>Price</th><th>Stock</th></tr></thead>";
		out.print(s1);
		q = "select * from pen";
		rs = stm.executeQuery(q);
		while(rs.next()){
		s2 = "<tr><td>"+rs.getString("pId")+"</td><td>"+rs.getString("pName")+"</td><td>"+rs.getString("description")+"</td><td>"+rs.getString("color")+"</td><td>"+rs.getString("compName")+"</td><td>"+rs.getString("price")+"</td><td>"+rs.getString("stock")+"</td></tr>";
		out.print(s2);
		}
		out.print("</table></div>");
		String home = "<div class='container' style='max-width:50%;'><h4><button class='btn btn-primary' onclick=\"history.back()\" >Go Back</button></h4></div>";
		out.print(home);
		}
		
		catch(Exception e)
		{
			out.print(e.getLocalizedMessage());
		}
	}
}
