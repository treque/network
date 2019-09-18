package Server.Commands;

import java.nio.file.Path;

public class DOWNLOADCommand extends AbstractCommand {

	DOWNLOADCommand(Path currentPath) 
	{
		super(currentPath);
	}
	
	@Override
	public String executeCommand(String[] command) {
		// TODO Auto-generated method stub
		return "this is what DONWLOAD does";
	}

}
