package Server.Commands;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

public class LSCommand extends AbstractCommand {

	LSCommand(Path currentPath) 
	{
		super(currentPath);
	}

	@Override
	public String executeCommand(String[] command) 
	{
		// should current dir be passed instead of currentpath
		File currentDir = new File(System.getProperty("user.dir"));
		String[] dirFilesNames = currentDir.list();
		return Arrays.toString(dirFilesNames);
	}

}
