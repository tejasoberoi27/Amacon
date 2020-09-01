package lab5;
import java.util.*;

final public class Category {
	private final String name;
	private final Category parent;
	private HashMap<String, Category> cmap = new HashMap<>();
	private HashMap<String, Product> pmap = new HashMap<>();
	private Product product;
	public Category(String name,Category parent) {
		super();
		this.name= name;
		this.parent = parent;
	}

	public void addSubCategory(String scat,Category cat)
	{
		cmap.put(scat,cat);
	}

	public void addProduct(Product p)
	{
		pmap.put(p.getName(),p);
	}

	public boolean containsSubCategory(String s)
	{
		return (cmap.containsKey(s));
	}

	public Category getSubCategory(String s)
	{
		return cmap.get(s);
	}
	public Product getProduct(String s)
	{
		return pmap.get(s);
	}

	public boolean containsProduct(String s)
	{
		return (pmap.containsKey(s));
	}

	public void removeSubCategory(String s)
	{
		cmap.remove(s);
	}
	public void removeProduct(String s)
	{
		pmap.remove(s);
	}

	public String getName() {
		return name;
	}

	public Product searchProduct(String productName) {

		Set<String> keys = cmap.keySet();
		int size = keys.size();
		Product search = null;
		for(int i=0;i<size;i++)
		{
			Iterator<String> itr = keys.iterator();
			while(itr.hasNext())
			{
				Category current = cmap.get(itr.next());
				if(current.cmap.keySet().size()==0)
				{
					if(current.containsProduct(productName))
					{
						search = current.getProduct(productName);
						search.displayPath();
						search.displayDetails();
					}
				}
				else
				{
					search = current.searchProduct(productName);
				}
			}
		}
		return search;



	}
}
