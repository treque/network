package Server.Commands;

import java.io.File;

public class UPLOADCommand extends AbstractCommand 
{

	UPLOADCommand(File currentDir) 
	{
		super(currentDir);
	}
	
	@Override
	public String executeCommand(String[] command) 
	{
		// TODO Auto-generated method stub
		return "filename " + command[1];
	}

}
