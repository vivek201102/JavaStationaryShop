import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(urlPatterns = {"/cart"})
public class cart extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request,HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
		HttpSession session = request.getSession();
		res.setContentType("text/html");
		
		try {
			out.print("<nav class='navbar navbar-dark bg-primary'><div class='container-fluid'><a class='navbar-brand' href='#'>Stationary Shop</a><a class='navbar-brand navbar-right' href='index.jsp'>Log out</a></div></nav>");
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/stationary";
			Connection conn = DriverManager.getConnection(url, "root", "201102");
			Statement stm = conn.createStatement();
			out.print("<html><head><title>Cart</title><link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3' crossorigin='anonymous'></head>");
			
			String s1 = "<form class='container' style='max-width:50%' action='bill'><h1>Cart</h1><table class='table' ><tr><th>Product ID</th><th>Item Name</th><th>Quantity</th><th>Price</th><th>Remove</th></tr>", s2;
			out.print(s1);
			
			ArrayList<prods> prodsInCart = new ArrayList<>();
			
			String query = "select * from book";
			
			ResultSet rs = stm.executeQuery(query);
			
			while(rs.next())
			{
				String id = request.getParameter(rs.getString("pId"));
				if(!id.equals(""))
				{
					int items = Integer.parseInt(id);
					if(items != 0)
					{
						prodsInCart.add(new prods(rs.getString("pId"), rs.getString("pName"), items, rs.getInt("price")));
						s2 = "<tr><td>" + rs.getString("pId") + "</td><td>" + rs.getString("pName") + "</td><td>" + items + "</td><td>" + rs.getInt("price") + "</td><td><input type='number' name = '" + rs.getString("pId") + "' max = '"+ items +"' min = '0'></td></tr>";
						out.print(s2);
					}
				}
			}
			
			query = "select * from pen";
			
			rs = stm.executeQuery(query);
			
			while(rs.next())
			{
				String id = request.getParameter(rs.getString("pId"));
				if(!id.equals(""))
				{
					int items = Integer.parseInt(id);
					if(items != 0)
					{
						prodsInCart.add(new prods(rs.getString("pId"), rs.getString("pName"), items, rs.getInt("price")));
						s2 = "<tr><td>" + rs.getString("pId") + "</td><td>" + rs.getString("pName") + "</td><td>" + items + "</td><td>" + rs.getInt("price") + "</td><td><input type='number' name = '" + rs.getString("pId") + "' max = '"+ items +"'min = '0'></td></tr>";
						out.print(s2);
					}
				}
			}
			
			query = "select * from desk";
			
			rs = stm.executeQuery(query);
			
			while(rs.next())
			{
				String id = request.getParameter(rs.getString("pId"));
				if(!id.equals(""))
				{
					int items = Integer.parseInt(id);
					if(items != 0)
					{
						prodsInCart.add(new prods(rs.getString("pId"), rs.getString("pName"), items, rs.getInt("price")));
						s2 = "<tr><td>" + rs.getString("pId") + "</td><td>" + rs.getString("pName") + "</td><td>" + items + "</td><td>" + rs.getInt("price") + "</td><td><input type='number' name = '" + rs.getString("pId") + "' max = '"+ items +"'min = '0'></td></tr>";
						out.print(s2);
					}
				}
			}
			
			query = "select * from calc";
			
			rs = stm.executeQuery(query);
			
			while(rs.next())
			{
				String id = request.getParameter(rs.getString("pId"));
				if(!id.equals(""))
				{
					int items = Integer.parseInt(id);
					if(items != 0)
					{
						prodsInCart.add(new prods(rs.getString("pId"), rs.getString("pName"), items, rs.getInt("price")));
						s2 = "<tr><td>" + rs.getString("pId") + "</td><td>" + rs.getString("pName") + "</td><td>" + items + "</td><td>" + rs.getInt("price") + "</td><td><input type='number' name = '" + rs.getString("pId") + "' max = '"+ items +"'min = '0'></td></tr>";
						out.print(s2);
					}
				}
			}			
			
			
			out.print("</table><br><button type='submit' class='btn btn-primary'>BILLING</button></form>");
			String home = "<hr><h4 style='margin-left:25.5%;'><button class='btn btn-primary' onclick=\"history.back()\" >Go Back</button></h4>";
			out.print(home);
			out.print("</html>");
			
			session.setAttribute("allOrd", prodsInCart);
		}
		
		catch(Exception e)
		{
			System.out.println(e.getLocalizedMessage());
		}
	}
}