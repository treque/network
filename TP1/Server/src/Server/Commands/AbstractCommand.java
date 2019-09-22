package Server.Commands;

import java.io.File;

public abstract class AbstractCommand {
	
	protected File currentDir;
	
	AbstractCommand(File currentDir)
	{
		this.currentDir = currentDir;
	}

	public File getCurrentPath() {
		return currentDir;
	}

	public void setCurrentPath(File currentDir) {
		this.currentDir = currentDir;
	}
	
	public abstract String executeCommand(String[] command);
	
	

}
