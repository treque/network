package Server.Commands;

import java.io.File;
import java.nio.file.Path;

public class DOWNLOADCommand extends AbstractCommand {

	DOWNLOADCommand(File currentDir) 
	{
		super(currentDir);
	}
	
	@Override
	public String executeCommand(String[] command) {
		// TODO Auto-generated method stub
		return "this is what DONWLOAD does";
	}

}
