package Server.Commands;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;

public class CommandReader {
	
	// Il doit y avoir une classe qui tiendrait en memoire l'etat courant(ou le thread est rendu dans l'arborescence)
	// https://docs.oracle.com/javase/tutorial/essential/io/dirs.html
	
	private Path currentPath;
	
	public CommandReader(Path currentPath) {
		this.currentPath = currentPath;
	}
	
	public String executor(String input) {
		String[] command = input.split(" ");
		switch(command[0]) {
		case("ls"):
			LSCommand lsExecutor = new LSCommand(currentPath);
			return lsExecutor.executeCommand(command);
		case("cd"):
			CDCommand cdExecutor = new CDCommand(currentPath);
			return cdExecutor.executeCommand(command);
		case("mkdir"):
			MKDIRCommand mkdirExecutor = new MKDIRCommand(currentPath);
			return mkdirExecutor.executeCommand(command);
		case("upload"):
			UPLOADCommand uploadExecutor = new UPLOADCommand(currentPath);
			return uploadExecutor.executeCommand(command);
		case("download"):
			DOWNLOADCommand downloadExecutor = new DOWNLOADCommand(currentPath);
			return downloadExecutor.executeCommand(command);
		case("exit"):
			EXITCommand exitExecutor = new EXITCommand(currentPath);
			return exitExecutor.executeCommand(command);
		default:
			return "Unrecognized command";
			//throw new IllegalArgumentException(input + "is not recognized as an internal or external command, operable program or batch file.");
		}
		
	};
	

	
}
