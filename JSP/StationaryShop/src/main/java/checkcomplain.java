import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/addcomplain"})
public class checkcomplain extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/stationary";
			Connection conn = DriverManager.getConnection(url, "root", "201102");
			Statement stm = conn.createStatement();
			response.setContentType("text/html");
			String mobile = (String) session.getAttribute("mob");
			String oid = (String) request.getParameter("ordid");
			
			String query = "select * from orderhistory where sid = '" + oid + "' and id in (select id from user where mobile = '" + mobile +"')";
			ResultSet rs = stm.executeQuery(query);
			
			if(rs.next())
			{
				String topic = (String) request.getParameter("option");
				String des = (String) request.getParameter("details");
				ComplainToken cmptoken = new ComplainToken();
//				String token = cmptoken.getComplainToken(cmptoken.change);
				String token = cmptoken.deserialiseAndGiveUniqId();
				cmptoken.serialise(cmptoken.ser);
				String email = null;
				query = "select * from user where mobile = '"+ mobile + "'";
				Date dnow = new Date();
				String date = dnow.toString();
				ResultSet rs1 = stm.executeQuery(query);
				if(rs1.next())
					email = rs1.getString("email");
				query = "insert into complaint (email, status, mobile, issue, token, cdate, oid, detail) values ('"+ email + "', 'pending' ,'" + mobile+ "', '" + topic+ "', '" + token + "', '" + date+ "', '" + oid+ "', '" + des+ "')";
				stm.executeUpdate(query);
				request.setAttribute("mob", mobile);
			}
			
			else {
				request.setAttribute("errorinord", "Your Order not Found!!!");
				RequestDispatcher rd = request.getRequestDispatcher("feedback.jsp");
				rd.include(request, response);
			}
		}
		
		catch(Exception e)
		{
			out.print(e.getLocalizedMessage());
		}
	}
}
