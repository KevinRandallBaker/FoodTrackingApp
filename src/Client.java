
public class Client 
{
	private Stack<Food> stack;
	private String fName;
	private String lName;
	private String clientID;
	
	
	Client()
	{
		clientID = "default";
		fName = "default";
		lName = "default";
		this.stack = new Stack();
	}
	
	Client(String clientID)
	{
		this.clientID = clientID;
		fName = "default";
		lName = "default";
		this.stack = new Stack();
	}
	
	Client(String clientID, String fName, String lName)
	{
		this.stack = new Stack();
		this.clientID = clientID;
		this.fName = fName;
		this.lName = lName;
	}
	
	public void addFood(Food food)
	{
		stack.push(food);
		
	}
	
	public Stack<Food> getStack()
	{
		return this.stack;
	}
	
	public String printHistory()
	{
		String result = "";
		Food tempFood = new Food();
		
		int tempTop = this.stack.getTop(); // Saves top of stack
		
		for(int i = 0; i < tempTop + 1; i ++) // Dinner loop
		{
			tempFood = this.stack.pop();
			
			if(tempFood.getType().equals("Dinner") ||tempFood.getType().equals("dinner"))
			{
				result += tempFood.getName() + " ";
			}
		}
		
		this.stack.setTop(tempTop); // Resets top of stack to it's original value
		
		for(int i = 0; i < tempTop + 1; i ++) // Lunch loop
		{
			tempFood = this.stack.pop();
			
			if(tempFood.getType().equals("Lunch") || tempFood.getType().equals("lunch"))
			{
				result += tempFood.getName() + " ";
			}
		}
		
		this.stack.setTop(tempTop); // Resets top of stack to it's original value
		
		for(int i = 0; i < tempTop + 1; i ++) // Breakfast loop
		{
			tempFood = this.stack.pop();
			
			if(tempFood.getType().equals("Breakfast") || tempFood.getType().equals("breakfast"))
			{
				result += tempFood.getName() + " ";
			}
		}
		
		this.stack.setTop(tempTop); // Resets top of stack to it's original value
		
		return result;
	}
	
	public int getCalories()
	{
		int result = 0;
		Food temp = new Food();
		int tempTop = this.stack.getTop();
		
		for(int i = 0; i < tempTop + 1; i++)
		{
			temp = this.stack.pop();
			result += temp.getCalorie() * temp.getQuantity();
		}
		
		this.stack.setTop(tempTop);
		
		return result;
	}
	
	public void getFoodTypes()
	{
		int tempTop = this.stack.getTop();
		Food[] foods = new Food[tempTop + 1];
		String[] result = new String[tempTop + 1];
		int count = 0;
		boolean found = false;
				
		for(int i = 0; i < tempTop + 1; i++)
		{
			foods[i] = this.stack.pop();
		}
		
		this.stack.setTop(tempTop);
		
		for(int i = 0; i < tempTop + 1; i++)
		{
			for(int j = 0; j < tempTop + 1; j++)
			{
				if(foods[i].getName().equals(result[j]))
				{
					found = true;
				}
			}
			
			if(found == false)
			{
				result[count] = foods[i].getName();
				count++;
			}
			
			found = false;
		}
		
		
		for(int i = 0; i < count - 1; i++)
		{
			System.out.print(result[i] + ", ");
		}
		
		System.out.println(result[count - 1]);
		
 	}
	
	public void getPopDailyFood()
	{
		int tempTop = this.stack.getTop();
		Food[] foods = new Food[tempTop + 1];
		int count = 0;
		int highest = 0;
		int popular = -1; // Saves an index
		
		for(int i = 0; i < tempTop + 1; i++)
		{
			foods[i] = this.stack.pop();
		}
		
		this.stack.setTop(tempTop);
		
		for(int i = 0; i < tempTop + 1; i++)
		{
			count += foods[i].getQuantity();
			
			for(int j = 0; j < tempTop + 1; j++)
			{
				if(foods[i].getName().equals(foods[j].getName()))
				{
					count += foods[j].getQuantity();
				}
			}
			
			if(highest < count)
			{
				highest = count;
				popular = i;
			}
				
		}
		
		System.out.println("Most popular food is " + foods[popular].getName());
		
	}
	
	public void getMinMax()
	{
		int tempTop = this.stack.getTop();
		Food[] foods = new Food[tempTop + 1];
		
		for(int i = 0; i < tempTop + 1; i++)
		{
			foods[i] = this.stack.pop();
		}
		
		this.stack.setTop(tempTop);
		
		//Food[] resultArray = new Food[tempTop + 1];
		
		for(int i = 0; i < tempTop + 1; i++)
		{
			for(int j =  i + 1; j < tempTop + 1; j++)
			{
				if(foods[i].getCalorie() > foods[j].getCalorie())
				{
					Food temp = foods[i];
					foods[i] = foods[j];
					foods[j] = temp;
					
				}
			}
		}
		
		for(int i = 0; i < tempTop; i++)
		{
			System.out.print(foods[i].getName() + ", ");
		}
		
		System.out.println(foods[tempTop].getName());
	}
	

	@Override
	public String toString() 
	{
		return "Client [ID=" + " " + clientID + "]";
	}
	
	
}
