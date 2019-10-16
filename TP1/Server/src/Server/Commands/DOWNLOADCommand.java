package Server.Commands;

import java.io.File;

public class DOWNLOADCommand extends AbstractCommand {

	DOWNLOADCommand(File currentDir) 
	{
		super(currentDir);
	}
	
	@Override
	public String executeCommand(String[] command) {
		File newFile = new File(currentDir.getAbsolutePath() + File.separator + command[1]);
		if (newFile.exists())
		{
			return "size " + Double.toString(newFile.length());
		}
		return "Error, file does not exist.";
	}

}
