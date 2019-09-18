package Server.Commands;

import java.nio.file.Path;
// java.nio.file.Files

public abstract class AbstractCommand {
	
	protected Path currentPath;
	
	AbstractCommand(Path currentPath)
	{
		this.currentPath = currentPath;
	}

	public Path getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(Path currentPath) {
		this.currentPath = currentPath;
	}
	
	public abstract String executeCommand(String[] command);
	
	

}
