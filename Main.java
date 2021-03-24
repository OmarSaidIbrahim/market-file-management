package Assignment_2;

public class Main {

	static Shelf market = new Shelf(); //market array list from class Shelf. Used in the main class methods (static)

	public static void main(String[] args) throws Exception //exception used to open the file
	{
		System.out.println("-- WELCOME TO OMAR'S MARKET --"); 
		
		start();//call start method to open the file
		//variables
		Console input = new Console();//input
		boolean finished = false;//while menu
		Item item;//create a new item in option 1 then add to the arraylist
		String option;//enter the option for the menu
		String categ;//variable for option 5
		String code;//variable for all options requiring a code
		double price;//variable for option 3
		
		//START WHILE LOOP AND MENU
		while (!finished) //until the finished variable is not true, the loop will keep going
		{
			
			System.out.println("\nPlease choose an option from the menu below...\n");
			menu();//display menu
			System.out.print("Option: ");
			
			option = input.askString("");//input option
			
			switch(option)
			{
				
				case "1": 	item = new Item();//create new item
							while(market.checkCode(item))//checkCode check if the code inserted in the new item already exist in the array list
							{
								System.out.println("\nItem's code inserted is not unique. Try again.");
								item = new Item();//if true, code is not unique, else it is 
							}
							market.addItem(item);//add to the arraylist
							System.out.println("\nItem inserted correctly.");
					break;
					
				case "2":	if(market.countItems() == 0) //everytime i check if the array list is empty or not
								System.out.print("\nThere is no item in the market.\n");
							else 
							{
								System.out.println("\nList of all the items in the market.\n");
								market.printItems();//display all the items of the arraylist
							}
					break;
					
				case "3":	if(market.countItems() == 0)
								System.out.print("\nThere is no item in the market.\n");
							else 
							{
								price = input.askDouble("\nEnter the price: £");//enter price
								System.out.print("\n");
								market.printCheapItems(price);//print all the items with a smaller price than the one entered
							}
					break;
					
				case "4":	System.out.println("\nThere are "+market.countItems()+" items in the market");//Count all the item of the market
					break;
					
				case "5":	if(market.countItems() == 0)
								System.out.print("\nThere is no item in the market.\n");
							else 
							{
								System.out.println("\nWhich category would you like to count?");
								categ = input.askString("");//Count all the item of a specific category
								if(market.countItemsByCategory(categ) == 0)//check if there are items with that category
									System.out.print("\nThere is no item in the market with that category.\n");
								else 
									System.out.println("\nThere are "+market.countItemsByCategory(categ)+" "+categ+" items in the market");
							}
					break;
					
				case "6":	System.out.print("\n");
							if(market.countItems() == 0)
								System.out.print("There is no item in the market.\n");
							else
								market.findTheBiggest().printDetails();//Print the most expensive item of the market
					break;
					
				case "7":	if(market.countItems() == 0)
								System.out.print("\nThere is no item in the market.\n");
							else 
							{
								System.out.println("\nInsert the code of the item to delete: ");
								code = input.askString("");//by inserting the code
								market.deleteItem(code);//Remove a specific item from the market
							}
					break;
					
				case "8":	if(market.countItems() == 0)
								System.out.print("\nThere is no item in the market.\n");
							else 
							{
								boolean found = false;
								while(!found)//to update an item we first need to find it. so we start a loop until it is not found or the list is ended
								{
									code = input.askString("Enter the code of the Item to modify: ");//enter the code
									if(market.updateItem(code))//if found loop end
									{
										System.out.println("\nItem updated.");
										found = true;
									}
									else//else ask user to try again
									{
										System.out.print("Would you like to try again ? Y/N\n");
										char answer = input.askChar("");
										while(answer != 'Y' && answer != 'N')//if answer not Y or N try again
										{
											System.out.print("Error. Try again.");
											answer = input.askChar("");
										}
										if(answer == 'N')//if N exit the loop
											found = true;
									}
								}
								
							}
					break;
					
				case "9":	if(market.countItems() == 0)
								System.out.print("\nThere is no item in the market.\n");
							else 
							{
								System.out.println("\nList of all the items sorted by the lowest price.\n");
								market.sortLowestToGreatestPrice();//sort array from lowest to highest price
							}
					break;
					
				case "0": 	finished = true;
							quit();//save and close file
					break;
					
				default : 	System.out.println("\nERROR: Invalid option");//if an invalid input is entered, display error
					break;
			}
		}
	}
	public static void menu()//menu
	{
		System.out.print("1- Add a new item to the market\n"
				+ "2- Print all the items of the market\n"
				+ "3- Print all the items cheaper than the price you enter\n"
				+ "4- Count all the item of the market\n"
				+ "5- Count all the item of a specific category\n"
				+ "6- Print the most expensive item of the market\n"
				+ "7- Remove a specific item from the market by code\n"
				+ "8- Update item by code\n"
				+ "9- Order items from the lowest price to the highest price\n\n"
				+ "0- Exit\n\n");
	}
	public static void start()
	{
		System.out.println("\nThis program uses Reading and Writing on File.");
		System.out.println("Once you start this program, it will look for \na file called 'items.dat' inside your project folder.");
		System.out.println("If it finds it, it will display a confirmation message, \notherwise it will create one at the end of the execution.\n");
		try
		{
			market.open("items.dat");// pre-defined name
			System.out.println("FILE IS OPENED AND READY TO BE DISPLAYED.");
		}
		catch (Exception e)
		{
			System.out.println("THE FILE DOES NOT EXIST INTO THE PROJECT FOLDER. I AM GOING TO CREATE ONE RIGHT NOW.");
		}
	}
	
	public static void quit() throws Exception
	{
		  // finished
		System.out.println("\nApplication will automatically save data file");
		System.out.println("Good bye!");
		System.out.println("End of application");
		market.save("items.dat");//save file
	}
}
