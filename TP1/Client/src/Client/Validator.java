package Client;

import java.util.List;

public class Validator {
	
	public static boolean isPortValid(String portInput)
	{
		
		try {
			  Integer result = Integer.parseInt(portInput);
			  if (result >= 5000 && result <= 5050)
			  {
				  return true;
			  }
			  return false;
			}
		catch (NumberFormatException e)
		{
		   return false;
		}
		
	}
	
	// refactor s t pppppppppppppp
	public static boolean isIPValid(String IPInput)
	{
		String[] IPsChar = IPInput.split("\\.");
		boolean isIPValid = false;
		if (IPsChar.length == 4) 
		{
			isIPValid = true;
		
			for (String IPChar : IPsChar) 
			{
				try {
					  Integer result = Integer.parseInt(IPChar);
					  if (result > 255 || result < 0)
					  {
						  isIPValid = false;
						  break;
					  }
					}
				catch (NumberFormatException e)
				{
					isIPValid = false;
				}
			
			isIPValid = true;
			}
		}
		else
		{
			isIPValid = false;
			return isIPValid;
		}
		return isIPValid;
	}
	
}
