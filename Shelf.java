package Assignment_2;

import java.io.*;
import java.util.ArrayList;

public class Shelf implements Serializable
{
	private static final long serialVersionUID = 1L;
	 ArrayList<Item> shelfs;//arraylist items
	 ArrayList<String> keys;//arraylist code of all the items
	transient Console input = new Console();
	
	public Shelf()//constructor. declares the arraylists
	{
		shelfs = new ArrayList<Item>();
		keys = new ArrayList<String>();
	}
	public void addItem(Item a)
	//add item from the parameter to the arraylist 'shelfs' 
	//and the code of the item into the 'keys' arraylist
	{
		keys.add(a.code);
		shelfs.add(a);
	}
	
	public boolean checkCode(Item a)//check if the code exists already
	{
		for(int i=0; i<shelfs.size(); i++)
				if(a.getCode().equals(shelfs.get(i).getCode()))
					return true;
		return false;
	}
	
	public Item Find(String ref)
	//find the item with the same code as the parameter. if not found return null
	{
		int index = keys.indexOf(ref);
		if(index == -1)
			return null;
		else
			return shelfs.get(index);
	}
	
	public void printItems()//print items in the arraylist
	{
		for(int i = 0; i < shelfs.size(); i++) 
			System.out.println(shelfs.get(i).toString());//use of the toString method
	}
	
	public int countItems()//return the number of items in the arraylist
	{
		return shelfs.size();
	}
	
	public int countItemsByCategory(String a)//counts the items with the given category
	{
		int count = 0;
		for(int i = 0; i < shelfs.size(); i++)
			if(shelfs.get(i).getCategory().equals(a))//comparing all the categories with the one entered
				count++;//counter
		return count;
	}
	
	public Item findTheBiggest()//compares all the items prices and display the biggest
	{
		Item max = shelfs.get(0);
		
		for(int i = 0; i < shelfs.size(); i++)
            if(max.getPrice() < shelfs.get(i).getPrice())
                max = shelfs.get(i);
            
		return max;
		
	}
	
	public void printCheapItems(double a)//display the items cheaper than the one entered
	{
		boolean flag = false;//flag used to display if the price entered is too small or not
		for(int i = 0; i < shelfs.size(); i++)
			if(shelfs.get(i).getPrice() <= a)
			{
				System.out.println(shelfs.get(i).toString());
				flag = true;
			}
		if(!flag)
			System.out.println("\nThe price you entered is too small. There are no items below that price");
	}
	
	public void deleteItem(String a)//delete an item by the given code
	{
		if(Find(a) != null)//first research the item with Find method
		{
			shelfs.remove(Find(a));//declared method to remove an object from the list
			System.out.println("\nItem deleted. Here your updated list: \n");
			printItems();
		}
		else
			System.out.println("\nItem not found.");
	}
	
	public boolean updateItem(String a)//update an item by given code
	{
		int index = keys.indexOf(a);
		if(index != -1)//first find the item, then update
		{
			//could have done with set or askDetails() as well
			System.out.print("\nItem found !!");
			System.out.print("\nInsert the new details.\n\nCategory: ");
			shelfs.get(index).category = input.askString("");
			
			System.out.print("Code: ");
			shelfs.get(index).code = input.askString("");
			
			System.out.print("Price: £");
			shelfs.get(index).price = input.askDouble("");
			return true;
		}
		else
		{
			System.out.println("Item not found.");
			return false;
		}
			
	}
	
	public void sortLowestToGreatestPrice()//this method won't affect the original arraylist, it makes a copy and display it
	{
		Item item = new Item("0","0",0.0);//had to create an empty item and modify later with sorting loop
		
		ArrayList<Item> sub = new ArrayList<Item>(); //create another arraylist to make a copy of the original one
		
		for(int i = 0;i<shelfs.size();i++)
		{
			sub.add(new Item(shelfs.get(i)));//add all the items of the original array list to the copy
		}
		
		
		for (int i = 0; i < sub.size(); i++) //sorting loop
        {
            for (int j = i + 1; j < sub.size(); j++)
            {
                if (sub.get(i).getPrice() > sub.get(j).getPrice())
                {
                	item.setItem(sub.get(i));
					sub.get(i).setItem(sub.get(j));
					sub.get(j).setItem(item);
                }
            }
        }
		
		for(int i = 0; i < sub.size(); i++) //display the copy array list
			System.out.println(sub.get(i).toString());
	}
	
	@SuppressWarnings("resource")
	public void save(String fileName) throws Exception//save and close file
	{
		FileOutputStream outFile;
		try // to open file
		{
			outFile = new FileOutputStream(fileName);
		}
		catch (IOException io)
		{
			throw new Exception("Cannot create " + fileName);
		}
		ObjectOutputStream dataFile;
		try  // to write file
		{
			dataFile = new ObjectOutputStream(outFile);
			dataFile.writeObject(keys);
			dataFile.writeObject(shelfs);
		}
		catch (IOException io)
		{
			throw new Exception("Cannot write to " + fileName);
		}
		try // to close file
		{
			dataFile.close();
		}
		catch (IOException io)
		{
			throw new Exception("Cannot close " + fileName);
		}
	}


		@SuppressWarnings({ "resource", "unchecked" })
	public void open(String fileName) throws Exception
	{
		FileInputStream inFile;
		try // to open file
		{
			inFile = new FileInputStream(fileName);
		}
		catch (FileNotFoundException e)
		{
			throw new Exception("File " + fileName + " not found.");
		}

		ObjectInputStream dataFile;
		try // to read file
		{
			dataFile = new ObjectInputStream(inFile);
			keys = (ArrayList<String>)dataFile.readObject();
			shelfs = (ArrayList<Item>)dataFile.readObject();
		}
		catch (IOException e)
		{
			throw new Exception("Error reading from " + fileName);
		}
		catch (ClassNotFoundException e)
		{
			throw new Exception("Error reading from " + fileName);
		}
		try
		{
			// to close file
			dataFile.close();
		}
		catch (IOException e)
		{
			throw new Exception("Cannot close " + fileName);
		}
	} // end open
}
