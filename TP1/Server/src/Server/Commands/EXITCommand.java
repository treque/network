package Server.Commands;

import java.nio.file.Path;

public class EXITCommand extends AbstractCommand 
{

	EXITCommand(Path currentPath) 
	{
		super(currentPath);
	}
	
	@Override
	public String executeCommand(String[] command) 
	{
		// TODO Auto-generated method stub
		return "EXIT??? more like quit??";
	}

}
