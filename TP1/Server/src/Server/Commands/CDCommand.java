package Server.Commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public class CDCommand extends AbstractCommand {
	
	CDCommand(File currentDir) 
	{
		super(currentDir);
	}

	@Override
	public String executeCommand(String[] command) {
		try {
			   System.out.println(this.currentDir.getPath());
			   System.out.println(command);
			   if (command.length != 2) {
			   throw new ArrayIndexOutOfBoundsException( "You have to have one and only one argument with cd");
			   }
			   String cdArgument = command[1];
			   if (currentDir.getPath().charAt((int) (currentDir.getPath().length()-1)) != File.separatorChar && command[1].charAt(0) != File.separatorChar) {
				   cdArgument = File.separator + command[1];
			   }
			   System.out.println(this.currentDir.getPath() + cdArgument);
			   File directory = new File(this.currentDir.getCanonicalPath() + cdArgument);
			   if(directory.isDirectory())
			   {
				   return directory.getCanonicalPath();
			   }
			   else
			   {
				   throw new IllegalArgumentException( "The directory you requested does not exist");
			   }

		} catch (IOException e) 
		{
			return this.currentDir.getPath();
		}
	}
	
}
