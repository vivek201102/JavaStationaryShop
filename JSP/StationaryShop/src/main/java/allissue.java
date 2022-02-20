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

@WebServlet(urlPatterns = {"/pastfeed"})

public class allissue extends HttpServlet{

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/stationary";
			Connection conn = DriverManager.getConnection(url, "root", "201102");
			Statement stm = conn.createStatement();
			Statement stm1 = conn.createStatement();
			response.setContentType("text/html");
			out.print("<html><head><title>Products</title><link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3' crossorigin='anonymous'></head>");
			
			String mobile =(String) session.getAttribute("mob");
			out.print("<nav class='navbar navbar-dark bg-primary'><div class='container-fluid'><a class='navbar-brand' href='#'>Stationary Shop</a><a class='navbar-brand navbar-right' href='index.jsp'>Log out</a></div></nav>");
			
			
			
			String query = "select * from complaint where mobile = '" + mobile +"'";
			ResultSet rs = stm.executeQuery(query);
			out.print("<div class='container' style='max-width:70%'><h4>Your all Complains</h4>");
			while(rs.next())
			{
			
				out.print("<table class='table table-striped'>");
				out.print("<tr><th>Token ID</th><td>"+rs.getString("token")+"</td></tr><tr><th>Complain Date</th><td>"+ rs.getString("cdate") + "</td></tr><tr><th>Issue</th><td>"+ rs.getString("issue")+"</td></tr><tr><th>Detail</th><td>"+rs.getString("detail")+"</td></tr><tr><th>Status of Complain</th><td style='color:red'>"+ rs.getString("status") +"</td></tr>");
				if(rs.getString("status").equals("closed"))
				{
					query = "select * from solved where token = '"+ rs.getString("token") + "'";
					ResultSet rs2 = stm1.executeQuery(query);
					if(rs2.next())
					{
						out.print("<tr><th>Complaint Closing Date</th><td>"+ rs2.getString("sdate")+"</td></tr><tr><th>Complaint Feedback</th><td>" + rs2.getString("solmsg") +"</td></tr>");
					}
				}
				out.print("</table><br><br>");
			}
			
			out.print("</div>");
			

		}
		
		catch(Exception e)
		{
			out.print(e.getLocalizedMessage());
		}
	
	}
}