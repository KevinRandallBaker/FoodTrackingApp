
public class Food 
{
	private String name;
	private int quantity;
	private int calorie;
	private String type;
	
	public Food()
	{
		
	}
	
	public Food(String name, int calorie, int quantity, String type)
	{
		this.name = name;
		this.calorie = calorie;
		this.quantity = quantity;
		this.type = type;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getQuantity()
	{
		return this.quantity;
	}
	
	public int getCalorie()
	{
		return this.calorie;
	}
	
	public String getType()
	{
		return this.type;
	}
}
