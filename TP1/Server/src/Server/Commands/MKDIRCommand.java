package Server.Commands;

import java.io.File;

public class MKDIRCommand extends AbstractCommand {

	MKDIRCommand(File currentDir) 
	{
		super(currentDir);
	}
	
	@Override
	public String executeCommand(String[] command) 
	{
		// TODO Auto-generated method stub
		return "MAKE DIR COMMAND";
	}

}
