package Server.Commands;

import java.io.File;
import java.net.Socket;

public abstract class AbstractCommand {
	
	protected File currentDir;
	protected Socket socket;
	
	AbstractCommand(File currentDir, Socket socket)
	{
		this.currentDir = currentDir;
		this.socket = socket;
	}

	public File getCurrentPath() {
		return currentDir;
	}

	public void setCurrentPath(File currentDir) {
		this.currentDir = currentDir;
	}
	
	public abstract String executeCommand(String[] command);
	
	

}
