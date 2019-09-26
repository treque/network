package Server.Commands;

import java.io.File;
import java.io.IOException;

public class CDCommand extends AbstractCommand 
{
	
	CDCommand(File currentDir) 
	{
		super(currentDir);
	}

	@Override
	public String executeCommand(String[] command) {

			   String cdArgument = command[1];
			   if (!currentDir.getPath().endsWith(File.separator) && !command[1].startsWith(File.separator)) 
			   {
				   cdArgument = File.separator + command[1];
			   }
			   System.out.println(this.currentDir.getPath() + cdArgument);
			   File directory = new File(this.currentDir.getPath() + cdArgument);
			   if(directory.isDirectory())
			   {
				   try 
				   {
					   return directory.getCanonicalPath();
				   } catch (IOException e) 
					{
						return directory.getPath();
					}
			   }
			   else
			   {
				   throw new IllegalArgumentException( "The directory you requested does not exist");
			   }

		
	}
	
}
