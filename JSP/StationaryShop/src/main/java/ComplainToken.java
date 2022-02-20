import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class ComplainToken implements Serializable {
	private static final long serialVersionUID = 1L;
	public ComplainToken ser;
	public long change = 3578;
    public ComplainToken(){}
    String getRandomStringValue(long change){
        String fixed = "COMPTOKEN0199580";
        fixed = fixed + String.valueOf(change);
        return fixed;
    }
    public String getComplainToken(long n){
        this.change = ++n;
        return getRandomStringValue(change);
    }
    
    public void serialise( ComplainToken ran) throws IOException {
    	FileOutputStream file = new FileOutputStream("ComplainToken.txt");
    	ObjectOutputStream out = new ObjectOutputStream(file);
    	out.writeObject(ran);  
    	            out.close();
    	            file.close();
    }
    
    public String deserialiseAndGiveUniqId() throws IOException, ClassNotFoundException {
    	FileInputStream file = new FileInputStream("ComplainToken.txt");
    	ObjectInputStream in = new ObjectInputStream(file);
    	ComplainToken ran = (ComplainToken)in.readObject();
    	this.ser = ran;
    	            in.close();
    	            file.close();
    	return ran.getComplainToken(ran.change);
    }
    public static void main(String args[]) throws IOException, ClassNotFoundException {
    	ComplainToken t = new ComplainToken();
    	String token = t.deserialiseAndGiveUniqId();
    	System.out.println(token);
    	t.serialise(t.ser);
    }
}
