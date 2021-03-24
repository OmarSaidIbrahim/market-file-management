package Assignment_2;

import java.io.*; //library needed for file

@SuppressWarnings("serial")
public class Item implements Serializable//serialization implemented for file
{
	//variables for object to enter in the market
	 String category;
	 String code;
	 double price;
	
	transient Console input = new Console();
	
	public Item()//constructor
	{
		askDetails();
	}
	public Item(Item i)//constructor with object as parameter
	{
		category = i.getCategory();
		code = i.getCode();
		price = i.getPrice();
	}
	public Item(String a, String b, double d)//constructor with multiple parameters
	{
		category = a;
		code = b;
		price = d;
	}
	
	public void askDetails()//ask user for details new item
	{
		System.out.print("\nEnter the details of a new item.\n\nCategory: ");
		category = input.askString("");
		
		System.out.print("Code: ");
		code = input.askString("");
		
		System.out.print("Price: £");
		price = input.askDouble("");
	}
	
	public void printDetails()//print details of an item
	{
		System.out.println("Category: "+ category +"\nCode: "+ code +"\nPrice: £"+ price + "\n");
	}
	//get methods
	public String getCategory()
	{
		return category;
	}
	public String getCode()
	{
		return code;
	}
	public double getPrice()
	{
		return price;
	}
	//sets
	public void setCategory(String a)
	{
		category = a;
	}
	public void setCode(String b)
	{
		code = b;
	}
	public void setPrice(double d)
	{
		price = d;
	}
	//over ride method to display the item correctly
	public String toString()
	{
		return "Category: "+ category + "\nCode: " + code + "\nPrice: £" + price + "\n";
	}
	//set item different from the constructor
	public void setItem(Item i)
	{
		category = i.getCategory();
		code = i.getCode();
		price = i.getPrice();
	}
}
