package lab5;
import java.util.*;
import java.lang.*;
final public class Amacon {
	private static Database db;	
	private static Scanner sc;
	public static void insert() throws NotNewException
	{
		db.insert(sc.next(),sc.next());
	}
	public static void delete() throws NotExistingException
	{
		db.delete(sc.next());
	}
	public static void search() 
	{
		Product search = db.search(sc.next());
	}
	public static void modify() 
	{
		db.modify(sc.next());
	}

	public static void main(String[] args) throws NotNewException, NotExistingException, insufficientUnitsException, insufficientFundsException {
		db= new Database();
		System.out.println("Enter 1 if you want to act as Administrator");
		System.out.println("Enter 2 if you want to act as Customer");
		sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice) {

		case 1:
			while(true)
			{
				Admin a = new Admin(db);
				char c = sc.next().charAt(0);
				if(c=='a')
				{
					try
					{insert();}
					catch (NotNewException e)
					{
						System.out.println(e.getMessage());
					}
				}
				else if(c=='b')
				{
					try
					{
						delete();
					}
					catch (NotExistingException e)
					{
						System.out.println(e.getMessage());	
					}
				}
				else if(c=='c')
				{
						search();
				}
				else if(c=='d')
				{
					modify();
				}
				else if(c=='e')
				{
					System.out.println(db.calcRevenue());
					System.exit(0);
				}
				else
				{
					System.out.println("Enter correct input");
				}
			}
		case 2:
			Customer cust = new Customer(db);
			while(true)
			{
				char c = sc.next().charAt(0);
				if(c=='a')
				{
					cust.addFunds(sc.nextInt());
					
				}
				else if(c=='b')
				{
					cust.addProduct(sc.next(), sc.nextInt());
				}
				else if(c=='c')
				{
					try {
					cust.CheckOut();
					}
					catch (insufficientUnitsException e)
					{
						System.out.println(e.getMessage());
					}


					catch(insufficientFundsException e)
					{
						System.out.println(e.getMessage());
					}
				}
				else if(c=='d')
				{
					System.out.println(db.calcRevenue());
					System.exit(0);
				}
				else
				{
					System.out.println("Enter correct input");
				}

			}
		default:
			break;
		}

	}

}
