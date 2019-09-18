package Server.Commands;

import java.nio.file.Path;

public class MKDIRCommand extends AbstractCommand {

	MKDIRCommand(Path currentPath) 
	{
		super(currentPath);
	}
	
	@Override
	public String executeCommand(String[] command) 
	{
		// TODO Auto-generated method stub
		return "MAKE DIR COMMAND";
	}

}
