package Assignment_2;

import java.util.Scanner;
/**
*Develop console class for scanner input
*Input for String, int, double and char
*/
public class Console {
    Scanner console = new Scanner(System.in);
    
    /**method for string input*/
    public String askString(String aPrompt) {
		System.out.print(aPrompt); // aPrompt is prompt string to user 
		
		String reply = console.next();// input held in variable reply
		return reply;
    }
    
    /**method for integer input*/
    public int askInt(String aPrompt)  {
		String reply = askString(aPrompt);
		
		//parseInt(...) converts string to integer;
		return Integer.parseInt(reply); 
    }
    
    /**method for double input*/
    public double askDouble(String aPrompt)  {
    	String reply = askString(aPrompt);;
    	while(!reply.matches("[0-9, .,]+"))
    	{
    		System.out.println("Error. Only numbers allowed. Try again.");
    		reply = askString(aPrompt);
    	}
		//parseDouble(...) converts string to double
		return Double.parseDouble(reply); 
    }
    
    /**method for one character input*/
    public char askChar(String aChar)  {
  	  String reply = askString(aChar);
  	  // Take first character of string and convert to Uppercase
	  return Character.toUpperCase(reply.trim().charAt(0));
    }
}
