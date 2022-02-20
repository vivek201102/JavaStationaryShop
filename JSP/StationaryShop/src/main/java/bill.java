import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/bill"})
public class bill extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		out.print("<html><head><title>Products</title><link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3' crossorigin='anonymous'></head>");
		try {
			out.print("<nav class='navbar navbar-dark bg-primary'><div class='container-fluid'><a class='navbar-brand' href='#'>Stationary Shop</a><a class='navbar-brand navbar-right' href='index.jsp'>Log out</a></div></nav>");
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/stationary";
			Connection conn = DriverManager.getConnection(url,"root","201102");
			Statement stm = conn.createStatement();
			Date dNow = new Date();
			String date = dNow.toString();
			
			updateDatabase udb = new updateDatabase();
			String name=null, email=null, hno=null, add1=null, add2=null, city=null, pincode=null;
			sessionId sidget = new sessionId();
//			String sid = sidget.getSessionId(sidget.change);
			String sid = sidget.deserialise();
			sidget.serialise(sidget.ser);
			String mobile = (String)session.getAttribute("mob");
			out.print("<form class='container style='max-width:50%;'>");
			String query = "select * from user where mobile = '"+mobile+"'";
			int cusid = -1;
			out.print("<h4 style='text-align:center; font-family: Georgia, serif;'>INVOICE</h4>");
			ResultSet rs = stm.executeQuery(query);
			if(rs.next())
			{
				cusid = rs.getInt("id");
				name = rs.getString("name");
				email = rs.getString("email");
				hno = rs.getString("hno");
				add1 = rs.getString("add1");
				add2 = rs.getString("add2");
				city = rs.getString("city");
				pincode = rs.getString("pincode");
			}
//			out.print("<table class='table table-striped'><tbody><tr><td>Name</td><td>Email</td><td>Mobile</td><td>Date</td></tr>");
//			out.print("<tr><td>" + name + "</td><td>" + email + "</td><td>" + mobile + "</td><td>" + date +"</td></tr></tbody></table>");
			out.print("<table class='table table-success container' border: '2px solid brown' style='max-width:65%; margin-top:5px;'><tr><th>Name</th><td>"+name+"</td></tr><tr><th>Email</th><td>" + email + "</td></tr><tr><th>Contact No</th><td>" + mobile + "</td></tr><tr><th>Order Date</th><td>" + date + "</td></tr><th>Order ID</th><td>" + sid +"<tr></tr><tr><th rowspan='3'>Address</th><td>"+ hno + "," + add1 + "</td></tr><tr><td>" + add2 + "</td></tr><tr><td>" + city + "-"+ pincode +"</td></tr></table>");
			out.print("<table class='table container table-striped' style='max-width:65%'><tr><th>Product Id</th><th>Product Name</th><th>Quantity</th><th>Price</th></tr>");
			ArrayList<prods> prodsInCart= new ArrayList<>();
			int total = 0;
			prodsInCart = (ArrayList<prods>)session.getAttribute("allOrd");
			for(prods p: prodsInCart)
			{
				String id = request.getParameter(p.id);
				if(!id.equals(""))
				{
					int item = Integer.parseInt(id);
					p.reduceItems(item);
					p.calculateTotal();
				}
				else {
					p.calculateTotal();
				}
				
				if(p.items != 0)
				{
					out.print("<tr><td>" + p.id + "</td><td>" + p.name + "</td><td>" + p.items + "</td><td>" + p.price + "</td></tr>");
					total += p.total;
					boolean updated = false;
					updated = udb.update("book", p.id, p.items);
					if(!updated) updated = udb.update("pen", p.id, p.items);
					if(!updated) updated = udb.update("desk", p.id, p.items);
					if(!updated) updated = udb.update("calc", p.id, p.items);
					
				}
				else {
					prodsInCart.remove(p);
				}
			}
			if(prodsInCart.size() != 0)
			{
				for(prods p: prodsInCart)
				{
					if(p.items == 0)
						prodsInCart.remove(p);
					else
						break;
				}
			}
			
			out.print("<tr><th></th><td></td><th>Total Ammount:</th><td>" + total + "/-Rs.</td></table>");
			if(prodsInCart.size()!= 0)
			{
				query = "insert into orderhistory values('"+cusid+"', '"+sid+"','"+total+"', '"+dNow+"')";
				stm.executeUpdate(query);
				for(prods p: prodsInCart)
				{
					query = "insert into orderdetails values('"+p.id+"', '"+p.name+"', '"+p.items+"', '"+cusid+"', '"+sid+"', '"+p.total+"')";
					stm.executeUpdate(query);
				}
				
				session.setAttribute("mob", mobile);
				
				out.print("<h5 style='text-align:center'>Order registered successfully</h5>");
			}
			out.print("</form>");
			out.print("<p style='text-align:center'><button class='btn btn-primary' style='margin-left:8%;' onclick='window.print()'>Print</button></p>");
			
		}
		
		
		catch(Exception e)
		{
			out.print(e.getLocalizedMessage());
		}
	}
}