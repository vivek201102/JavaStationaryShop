import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/update"})
public class updatestock extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/stationary";
			Connection conn = DriverManager.getConnection(url,"root","201102");
			Statement stm = conn.createStatement();
			String product = (String) request.getParameter("product");
			String id = (String) request.getParameter("id");
			int stock = Integer.parseInt(request.getParameter("stock"));
			boolean updated = false;
			if(product.equals("Book"))
			{
				
				String query = "update book b set stock = b.stock +'" + stock +"' where pId = '" + id + "'";
				stm.executeUpdate(query);
				query = "select * from book where pId = '" + id + "'";
				ResultSet rs = stm.executeQuery(query);
				if(rs.next())
				{
					out.print("product found");
					updated = true;
				}
				
			}
			
			else if(product.equals("Pen"))
			{
				String query = "update pen p set stock = p.stock +'" + stock +"' where pId = '" + id + "'";
				stm.executeUpdate(query);
				query = "select * from pen where pId = '" + id + "'";
				ResultSet rs = stm.executeQuery(query);
				if(rs.next())
				{
					updated = true;
				}
			}
			
			else if(product.equals("Desk"))
			{
				String query = "update desk d set stock = d.stock +'" + stock +"' where pId = '" + id + "'";
				stm.executeUpdate(query);
				query = "select * from desk where pId = '" + id + "'";
				ResultSet rs = stm.executeQuery(query);
				if(rs.next())
				{
					updated = true;
				}
			}
			
			else {
				String query = "update calc c set stock = c.stock +'" + stock +"' where pId = '" + id + "'";
				stm.executeUpdate(query);
				query = "select * from calc where pId = '" + id + "'";
				ResultSet rs = stm.executeQuery(query);
				if(rs.next())
				{
					updated = true;
				}
			}
			
			if(updated == false)
			{
				request.setAttribute("updateerror","Product Do not Exist!!!" );
				RequestDispatcher rd1 = request.getRequestDispatcher("updatestock.jsp");
				rd1.include(request, response);
			}
			else
			{
				response.sendRedirect("index.jsp");
			}
			
		}
		
		catch(Exception e)
		{
			out.print(e.getLocalizedMessage());
		}
	}
}
