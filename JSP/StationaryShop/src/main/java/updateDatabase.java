import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class updateDatabase {
	String url;
	Connection conn;
	Statement stm;
	public updateDatabase() throws SQLException, ClassNotFoundException{
	url = "jdbc:mysql://localhost:3306/stationary";
	Class.forName("com.mysql.cj.jdbc.Driver");
	conn = DriverManager.getConnection(url,"root","201102");
	stm = conn.createStatement();
	}
	public boolean update(String tName,String id,int newStock) throws SQLException {
		String q = "update "+tName+" t set stock = t.stock - "+newStock+" where pId = '"+id+"'";
		int affectedRows = stm.executeUpdate(q);
		if(affectedRows != 0) return true;
		return false;
	}
}
