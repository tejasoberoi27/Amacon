package lab5;

final public class Customer {
	private int funds;
	private Cart shoppingCart;
	public Customer(Database db)
	{
		funds =0;
		shoppingCart= new Cart(db);
	}
	
	public void addFunds(int funds)
	{
		this.funds+=funds;
	}
	
	public void addProduct(String ProdName,int quantity)
	{
		shoppingCart.addProduct(ProdName, quantity);
	}

	public void CheckOut() throws insufficientUnitsException, insufficientFundsException
	{
		shoppingCart.checkOut(funds);
	}
}

