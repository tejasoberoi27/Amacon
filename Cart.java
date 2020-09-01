package lab5;
import java.util.*;
final public class Cart {

	private ArrayList<Product>products;
	private HashMap<Product, Integer> quant = new HashMap<>();
	private Database db;

	public Cart(Database db) {
		super();
		this.products = null;
		this.quant = null;
		this.db = db;
	}

	public void addProduct(String ProdName,int quantity)
	{
		Product p = db.search(ProdName);
		if(p!=null)
		{
			quant.put(p,quantity);
		}
	}

	public void checkOut(int funds) throws insufficientUnitsException, insufficientFundsException
	{
		int n = products.size();
		for(int i=0;i<n;i++)
		{			

			Set<Product> keys = quant.keySet();
			int size = keys.size();
			Iterator<Product> itr = keys.iterator();
			while(itr.hasNext())
			{
				Product currentProd =itr.next();
				int quantity = quant.get(itr.next());
				int transaction =  db.sale(currentProd,quantity,funds);
				funds-=(transaction);
			}
		}
	}
}