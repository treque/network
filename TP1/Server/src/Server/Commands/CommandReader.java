package Server.Commands;
import java.io.File;
import java.net.Socket;

public class CommandReader {
	
	// Il doit y avoir une classe qui tiendrait en memoire l'etat courant(ou le thread est rendu dans l'arborescence)
	// https://docs.oracle.com/javase/tutorial/essential/io/dirs.html
	
	public File currentDir;
	private Socket socket;
	
	public CommandReader(File currentDir, Socket socket) {
		this.currentDir = currentDir;
		this.socket = socket;
	}
	
	public String executor(String input) {
		String[] command = input.split(" ");
		switch(command[0]) {
		case("ls"):
			if (hasRightArgumentNumber(1, command)) {
				LSCommand lsExecutor = new LSCommand(currentDir, socket);
				return lsExecutor.executeCommand(command);
			}
		case("cd"):
			if (hasRightArgumentNumber(2, command)) {
				CDCommand cdExecutor = new CDCommand(currentDir, socket);
				try {
					this.currentDir = new File(cdExecutor.executeCommand(command));
				} catch (IllegalArgumentException e) {
					return "your input doesnt make sense";
				}
				return "Vous etes dans le dossier: " + currentDir.getPath();
			}

		case("mkdir"):
			if(command.length > 1) {
				MKDIRCommand mkdirExecutor = new MKDIRCommand(currentDir, socket);
				return mkdirExecutor.executeCommand(command);
			}
		case("upload"):
			if (hasRightArgumentNumber(2, command)) {
				UPLOADCommand uploadExecutor = new UPLOADCommand(currentDir, socket);
				return uploadExecutor.executeCommand(command);
			}
		case("download"):
			if (hasRightArgumentNumber(2, command)) {
				DOWNLOADCommand downloadExecutor = new DOWNLOADCommand(currentDir, socket);
				return downloadExecutor.executeCommand(command);
			}
		case("exit"):
			if (hasRightArgumentNumber(1, command)) {
				return "Client " + socket.getPort() + " has disconnected";
			}
		default:
			return "Unrecognized command";
			}
		
	}
	
	private boolean hasRightArgumentNumber(int supposedLength, String[] command) {
		   return command.length == supposedLength;
	}
	

	
}
