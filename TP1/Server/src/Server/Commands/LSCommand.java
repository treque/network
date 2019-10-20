package Server.Commands;

import java.io.File;
import java.net.Socket;
import java.util.Arrays;

public class LSCommand extends AbstractCommand {

	LSCommand(File currentDir, Socket socket) 
	{
		super(currentDir, socket);
	}

	@Override
	public String executeCommand(String[] command) 
	{
		// should current dir be passed instead of currentpath
		// File currentDir = new File(System.getProperty("user.dir"));
		String fileList = "";
		for (File file : currentDir.listFiles())
		{
			if (file.isFile())
			{
				fileList += "[File] " + file.getName() + "\n";
			}
			else
			{
				fileList += "[Folder] " + file.getName() + "\n";
			}
		}
		return fileList;
	}

}
