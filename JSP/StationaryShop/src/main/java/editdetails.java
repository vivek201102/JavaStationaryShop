import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/editconsumer"})
public class editdetails extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/stationary";
			Connection conn = DriverManager.getConnection(url,"root","201102");
			Statement stm = conn.createStatement();
			response.setContentType("text/html");
			String name = (String)request.getParameter("name");
			String email = (String)request.getParameter("email");
			String mobile = (String)session.getAttribute("mob");
			String dob = (String)request.getParameter("dob");
			String hno = (String)request.getParameter("hno");
			String add1 = (String)request.getParameter("add1");
			String add2 = (String)request.getParameter("add2");
			String city = (String)request.getParameter("city");
			String pincode = (String)request.getParameter("pincode");
			
		
			
			String query = "update user set name = '"+name+"', email = '"+email+"', dob = '"+dob+"', hNo = '"+hno+"', add1 = '"+add1+"', add2 = '"+add2+"', city = '"+city+"', pincode = '"+pincode+"' where mobile = '"+mobile+"'";
				stm.executeUpdate(query);
				response.sendRedirect("index.jsp");
		}
		
		catch(Exception e)
		{
			out.print(e.getLocalizedMessage());
		}
	}
}
