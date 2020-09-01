package lab5;
import java.util.*; 

/*
 * 			insert(input by admin)
			If the database already has that product
			under that category then program should raise a custom exception.
 * delete(product/subcateg),
			. {If invalid path is specified in delete API then program
			should raise a custom exception.search(product)},

			search(product)
			{
				If the product does not exist, the program should raise a custom
			exception.
			}


						modify(product[prop.s input from admin]){call search},

			​			sale(product,quantity,remaining_funds_with_customer)​.
			{If
			that much of stock is unavailable or if funds are insufficient to buy this product the
			program should raise appropriate custom exceptions.}

			methods (undecided)-

 */

final class NotNewException extends Exception{
	public NotNewException(String message)
	{
		super(message);
	}
}

final class NotExistingException extends Exception{
	public NotExistingException(String message)
	{
		super(message);
	}
}

final 	public class Database {
	
	private Category root;
	private int revenue;

	

	public Database() {
		this.root = new Category("",null);
		this.revenue = revenue;
	}

	public void insert(String path,String insertion) throws NotNewException
	{
		int found =0;
		int n = insertion.length();
		int s = 0;
		int last = 0;
		String cat;
		Category current = root;
		Category parent =root;
		Category subcategory = null;
		String prodPath = "";

		for(int i=0;i<n;i++)
		{
			if(path.charAt(i)=='>')
			{	
				found = 1;
				cat =  path.substring(s,i);
				s=i+1;
				last =i;
				if( !parent.containsSubCategory(cat))
				{
					current = new Category(cat,parent);
					parent.addSubCategory(cat,current);
				}
				parent = current;
				prodPath+=(parent.getName()+">");

			}
			if(i==n-1||found==0)
			{
				cat = path.substring(last,n);
				if( !parent.containsSubCategory(cat))
				{
					current = new Category(cat,parent);
					parent.addSubCategory(cat,current);
				}
				parent = current;
				prodPath+=(parent.getName()+"");
			}


		}

		if(parent.containsProduct(insertion))
		{
			throw new NotNewException("Product/category already exists");
		}
		else
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter price and number of units of "+insertion+" separated by single space");
			int price = sc.nextInt();
			int NumUnits = sc.nextInt(); 
			Product product = new Product(insertion,prodPath,price,NumUnits);
			parent.addProduct(product);
		}

	}

	public void delete(String path) throws NotExistingException
	{
		int found =0;
		int n = path.length();
		int s = 0;
		int last = 0;
		String cat;
		Category parent =root;
		Category subcategory = null;
		for(int i=0;i<n;i++)
		{
			if(path.charAt(i)=='>')
			{	
				found = 1;
				cat =  path.substring(s,i);
				s=i+1;
				last =i;
				if(!parent.containsSubCategory(cat))
				{
					throw new NotExistingException("Product/subcategory does not exist");
				}
				parent = parent.getSubCategory(cat);
			}
			
			
			
			if(i==n-1||found==0)
			{
				cat = path.substring(last,n);
				if( !parent.containsSubCategory(cat))
				{
					if(parent.containsProduct(cat))
					{
						parent.removeProduct(cat);
					}
					else
					{
						throw new NotExistingException("Product/subcategory does not exist");
					}					
				}
				else
				{
					parent.removeSubCategory(cat);
				}
			}
		}
	}
	
	public Product search(String productName)
	{
		Product search = null;
		Category parent = root;		
		return parent.searchProduct(productName);
	}
	
	public void modify(String productName)
	{
		Product search = search(productName);
		if(search==null)
		{
			System.out.println("No such product exists");
		}
		else
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter new price and number of units of "+productName+" separated by single space");
			int price = sc.nextInt();
			int NumUnits = sc.nextInt();	
			search.setPrice(price);
			search.setNumUnits(NumUnits);
			sc.close();
		}
	}

	public int sale(Product product,int quantity,int funds_left) throws insufficientUnitsException, insufficientFundsException
	{
		int total =product.getPrice()*quantity; 
		if(product.getNumUnits()< quantity)
		{
			throw new insufficientUnitsException("insufficient number of units");
		}
		else if(total<funds_left)
		{
			throw new insufficientFundsException("insufficient funds left with the customer ");
		}
		product.setNumUnits(product.getNumUnits()-quantity);
		revenue+=(total);
		return total;

	}
	
	public int calcRevenue()
	{
		return revenue;
	}
	
	
}
