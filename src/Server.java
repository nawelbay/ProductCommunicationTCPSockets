import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

		private static List<Product> products = new ArrayList<>();

		public static void main(String[] args) {
			// Initializing the list of products
			products.add(new Product("Computer-ASSUS", 99));
			products.add(new Product("Computer-Acer", 59));
			products.add(new Product("Mouse", 29));
			products.add(new Product("Keyboard-Dell", 49));
			products.add(new Product("Computer-Dell", 99));

			try {
				ServerSocket ss = new ServerSocket(1234);
				System.out.println("Server started, waiting for connection...");
				while (true) {
					Socket s = ss.accept();
					System.out.println("New connection from " + s.getRemoteSocketAddress());
					communication(s);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Method to get the price of a product by name
		public static int getPriceByName(String name) {
			for (Product product : products) {
				if (product.getName().equalsIgnoreCase(name)) {
					return product.getPrice();
				}
			}
			return -1;
		}

		// Communication method to handle client-server interaction
		private static void communication(Socket sck) {
			try {
				InputStream is = sck.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String pdname = br.readLine();  // Reading product name from client
                System.out.println("Server received: " + pdname);
				int price = getPriceByName(pdname); // Fetching the price of the product
				System.out.println("Product price: " + price);
				Product p = new Product(pdname, price); // Creating product object with name and price

				OutputStream os = sck.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(p); // Sending product object back to client
				oos.flush();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

