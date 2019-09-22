package Server.Commands;

import java.io.File;
import java.nio.file.Path;

public class EXITCommand extends AbstractCommand 
{

	EXITCommand(File currentDir) 
	{
		super(currentDir);
	}
	
	@Override
	public String executeCommand(String[] command) 
	{
		// TODO Auto-generated method stub
		return "EXIT??? more like quit??";
	}

}
