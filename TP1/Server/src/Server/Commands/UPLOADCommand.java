package Server.Commands;

import java.nio.file.Path;

public class UPLOADCommand extends AbstractCommand 
{

	UPLOADCommand(Path currentPath)
	{
		super(currentPath);
	}
	
	@Override
	public String executeCommand(String[] command) 
	{
		// TODO Auto-generated method stub
		return "upload something hihi";
	}

}
