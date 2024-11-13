import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {

	public static void main(String[] args) {
		
		try {
			Socket s= new Socket("localhost", 1234);
			
			OutputStream os= s.getOutputStream();
			PrintWriter pw = new PrintWriter(os,true);
			System.out.println("Give the name of the product");
			Scanner sc = new Scanner(System.in);
			String rep=sc.nextLine();
			//send name product to Server
			pw.println(rep); 
			
			InputStream is = s.getInputStream(); 
			ObjectInputStream ois = new ObjectInputStream(is);

			//receive product object from to Server
			Product pd= (Product)ois.readObject();

           System.out.println("The price of the product: " + pd.getPrice());
			
		} catch (Exception e) {
		
			e.printStackTrace();
		} 
		

	}

}
