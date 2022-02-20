
public class prods {
	public String id, name;
	public int items, price, total;
	public prods()
	{}
	public prods(String id, String name, int items, int price) {
		this.id = id;
		this.name = name;
		this.items = items;
		this.price = price;
	}
	
	public void reduceItems(int i)
	{
		this.items -= i;
	}
	
	public void calculateTotal()
	{
		this.total = items * price;
	}
		
}
