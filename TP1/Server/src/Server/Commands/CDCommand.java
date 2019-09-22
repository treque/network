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
			// jai decide que sil passe plus de 2 arguments, que les arguments qui suivent font partie du nom du repertoire...
			File directory = new File(this.currentDir.getCanonicalPath() + String.join("", command).substring(3));
			if(directory.isDirectory()) 
			{
				return directory.getCanonicalPath(); 
			} 
			else
			{
				return "The directory you requested does not exist";
			}
		} catch (IOException e) 
		{
			return "Error getting the canonical path... ";
		}
	}
	
}
