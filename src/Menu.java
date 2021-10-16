import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu
{
	private boolean loggedIn;
	private Client currentClient;
	private BufferedReader console;
	private BufferedReader reader;
	private BufferedWriter writer;
	private File clientList = new File("c:/Users/iamsl/eclipse-workspace/CSC311_Project3/src/clientList.txt");
	
	public Menu() throws IOException
	{
		loggedIn = false;
		console = new BufferedReader(new InputStreamReader(System.in));
		reader = new BufferedReader(new FileReader(clientList));
		writer = new BufferedWriter(new FileWriter(clientList, true)); // Puts clientList file into BuffWriter
	}
	
	public void start() throws IOException
	{
		int input = -1;

		if(!loggedIn)
		{
			System.out.println("Please select the following.");
			System.out.println("[1] Login");
			System.out.println("[2] Register");
			
			while(input == -1)
			{
				input = Integer.parseInt(console.readLine());
				if(input != 1 && input != 2)
				{
					input = -1;
					System.out.println("Please enter either [1] for login or [2] for register.");
				}
			}
			
			if(input == 1)
				login();
			if(input ==2)
				register();
		}
		else
		{
			System.out.println("Please select the following.");
			System.out.println("[3] Add food");
			System.out.println("[4] Food history");
			System.out.println("[5] Food types");
			System.out.println("[6] Total calories");
			System.out.println("[7] Min -> max calorie intake");
			System.out.println("[8] Popular daily food");
			System.out.println("[9] Logout");
			
			while(input == -1)
			{
				input = Integer.parseInt(console.readLine());
				if(input != 3 &&
						input != 4 &&
						input != 5 &&
						input != 6 &&
						input != 7 &&
						input != 8 &&
						input != 9)
				{
					input = -1;
					System.out.println("Please select an integer 3 - 9");
				}
			}
			
			if(input == 3)
				addFood();
			if(input == 4)
				foodHistory();
			if(input == 5)
				foodTypes();
			if(input == 6)
				totalCalories();
			if(input == 7)
				minMax();
			if(input == 8)
				popDailyFood();
			if(input == 9)
				logout();
			
			
		}
		
		
	}
	
	public void login() throws IOException // [1]
	{
		String current = "";
		reader.mark(50); // Marks list at beginning
		
		
		if(reader.readLine() == null) // If file is empty simply create client
		{
			System.out.println("No users registered currently, please register.");
			register();
			reader.reset();
		}
		

		reader.reset(); 

		System.out.println("Please enter username.");
		String user = console.readLine();
		
		current = reader.readLine(); 
		
		while(current != null)
		{
			if(current.equals(user))
			{
				this.loggedIn = true;
				System.out.println("Welcome back " + user);
				this.currentClient = new Client(user);
				reader.reset();
				start();
			}
			
			current = reader.readLine();
		}
		reader.reset(); // Reset correctly by this point
		
		
		
		System.out.println("No users with that name, please register");
		register();
		
		
	}
	
	private boolean searchFile(String item) throws IOException
	{
		boolean found = false;
		reader.mark(50);
		reader.reset();
		String current = reader.readLine();
		
		while(current != null && found == false)
		{
			if(current.equals(item))
			 {
				 found = true;
			 }
			
			current = reader.readLine();
		}
		
		reader.reset();
		return found;
	}
	
	public void register() throws IOException // [2]
	{
		int attempts = 2;
		String clientID = "";
		String fName = "";
		String lName = "";
		String newName = "";
		String current = "";
		reader.mark(50);
		current = reader.readLine();
		reader.reset();
		
		System.out.print("Please enter first name: ");
		fName = console.readLine();
		
		System.out.print("Plase enter last name: ");
		lName = console.readLine();
		
		
		
		System.out.print("Please select desired user name: ");
		clientID = console.readLine();
		
		if(reader.readLine() == null) // If file is empty simply create client
		{
			writer.write(clientID + "\n");
			writer.close();
			this.currentClient = new Client(clientID, fName, lName);
			this.loggedIn = true;
			start();
		}
		else
		{
			reader.reset();
			boolean found = searchFile(clientID);
					
			while(found && attempts > 0)
			{
				attempts--;
				System.out.println("ID taken, please try again.");
				clientID = console.readLine();
				
				found = searchFile(clientID);
			}
		
		}
		
		if(attempts == 0)
		{
			System.out.print("Attempts exceeded your userID will be : ");
			newName += fName.charAt(0);
			newName += lName.charAt(0);
			
			for(int i = 2; i < 8; i++)
			{	
				newName += (int)(Math.random() * (9 - 1) + 1);	
			}
			
			System.out.println(newName);
			writer.write(newName + "\n");
			writer.close();
			this.currentClient = new Client(newName, fName, lName);
		}
		else
		{
			writer.write(clientID + "\n");
			writer.close();
			this.currentClient = new Client(clientID, fName, lName);
		}
		
		
		this.loggedIn = true;
		start();
		
	}
	
	public void addFood() throws IOException // [3]
	{
		String name = "";
		int calorie = -1;
		int quantity = -1;
		String type = "";
		boolean addMore = false;
		boolean error = false;
		String input = "";
		
		do
		{
			System.out.print("Please add food name: ");
			name = console.readLine();
			
			System.out.print("Please enter how many calories that is: ");
			calorie = Integer.parseInt(console.readLine());
			
			System.out.print("Please enter how many of that food you ate: ");
			quantity = Integer.parseInt(console.readLine());
			
			System.out.print("Please enter what meal that was for: ");
			type = console.readLine();
			
			this.currentClient.addFood(new Food(name, calorie, quantity, type));
			
			
			do
			{
				System.out.println("Would you like to add another food? (Y/N)");
				input = console.readLine();
				
				if(!input.equals("y") && !input.equals("Y") && !input.equals("n") && !input.equals("N"))
				{
					error = true;
					System.out.println("Please print either a Y for yes or or a N for no");
				}
				
			} while(error == true);
			
			if(input.equals("Y") || input.equals("y"))
				addMore = true;
			if(input.equals("N") || input.equals("n"))
				addMore = false;
			
		} while(addMore == true);
		
		
		start();
		
	}
	
	public void foodHistory() throws IOException // [4]
	{
		if(this.currentClient.getStack().isEmpty())
		{
			System.out.println("No food has been entered, returning you to menu screen.");
			start();
		}
		
		System.out.println(this.currentClient.printHistory());
		start();
	}
	
	public void foodTypes() throws IOException // [5]
	{
		if(this.currentClient.getStack().isEmpty())
		{
			System.out.println("No food has been entered, returning you to menu screen.");
			start();
		}
		
		this.currentClient.getFoodTypes();
		start();
	}
	
	public void totalCalories() throws IOException // [6]
	{
		if(this.currentClient.getStack().isEmpty())
		{
			System.out.println("No food has been entered, returning you to menu screen.");
			start();
		}
		
		System.out.println("Total Calories: " + this.currentClient.getCalories());
		start();
	}
	
	public void minMax() throws IOException // [7]
	{
		if(this.currentClient.getStack().isEmpty())
		{
			System.out.println("No food has been entered, returning you to menu screen.");
			start();
		}
			
		this.currentClient.getMinMax();
		start();
	}
	
	public void popDailyFood() throws IOException // 8
	{
		if(this.currentClient.getStack().isEmpty())
		{
			System.out.println("No food has been entered, returning you to menu screen.");
			start();
		}
		
		this.currentClient.getPopDailyFood();
		start();
	}
	
	public void logout() throws IOException // 9
	{
		this.loggedIn = false;
		start();
	}
	
		
		
}
