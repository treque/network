package Server;

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
		boolean isIPValid = true;
		System.out.println(IPsChar.length);
		if (IPsChar.length == 4) 
		{
			
			for (String IPChar : IPsChar) 
			{
				try {
					  Integer result = Integer.parseInt(IPChar);
					  System.out.println(result);
					  if (result > 255 || result < 0)
					  {
						  isIPValid = false;
						  break;
					  }
					  
					}
				catch (NumberFormatException e)
				{
					e.printStackTrace();
					isIPValid = false;
				}
			}
		}
		else
		{
			isIPValid = false;
		}
		return isIPValid;
	}

}
