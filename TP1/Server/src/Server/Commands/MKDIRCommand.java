package Server.Commands;

import java.io.File;
import java.net.Socket;
import java.util.Arrays;

public class MKDIRCommand extends AbstractCommand {

	MKDIRCommand(File currentDir, Socket socket) 
	{
		super(currentDir, socket);
	}
	
	@Override
	public String executeCommand(String[] command) 
	{
		String[] directories = Arrays.copyOfRange(command, 1, command.length);
		for(String argument: directories) {
			File file = new File(this.currentDir.getPath() + File.separator + argument);
	        if (!file.exists()) 
	        {
	            if (!file.mkdir()) 
	            {
	            	return "Failed to create directory! " + argument;
	            }
	        }
	        else 
	        {
	            return  "Failure: directory " + argument + " already exists!";
	        }
		}
		 if (command.length == 2) {
			 return "Le dossier " + directories[0] + " a ete cree.";
		 } else {
			 return "Multiples dossiers ont ete crees";
		 }
		

	}
}
