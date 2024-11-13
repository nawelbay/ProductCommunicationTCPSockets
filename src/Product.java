import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 7142258954350841612L;

	 private int price;
	 String name;
	
	public Product(String name, int price)
	{
		this.name=name;
		this.price=price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
