import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class sessionId implements Serializable{
	private static final long serialVersionUID = 1L;
	public sessionId ser;
	public long change = 185745;
	
	public sessionId() {}
	
	String getRandomStringValue(long change)
	{
		String fixed = "ORD_ID_";
		fixed = fixed + change;
		return fixed;
	}
	
	public String getSessionId(long n)
	{
		this.change = ++n;
		return getRandomStringValue(change);
	}
	
	public void serialise(sessionId ran) throws IOException
	{
		FileOutputStream file = new FileOutputStream("sessionid.txt");
		ObjectOutputStream output = new ObjectOutputStream(file);
		output.writeObject(ran);
		output.close();
		file.close();
	}
	
	public String deserialise() throws IOException, ClassNotFoundException
	{
		FileInputStream file = new FileInputStream("sessionid.txt");
		ObjectInputStream input = new ObjectInputStream(file);
		sessionId ran = (sessionId)input.readObject();
		this.ser = ran;
		input.close();
		file.close();
		return ser.getSessionId(ser.change);
	}
}
