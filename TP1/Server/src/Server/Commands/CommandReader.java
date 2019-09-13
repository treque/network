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
			LSCommand lsexecutor = new LSCommand(currentPath);
			return lsexecutor.excecuteCommand(command);
		case("cd"):
			CDCommand cdexecutor = new CDCommand(currentPath);
			return cdexecutor.excecuteCommand(command);
		case("mkdir"):
			MKDIRCommand mkdirexecutor = new MKDIRCommand(currentPath);
			return mkdirexecutor.excecuteCommand(command);
		case("upload"):
			UPLOADCommand uploadexecutor = new UPLOADCommand(currentPath);
			return uploadexecutor.excecuteCommand(command);
		case("download"):
			DOWNLOADCommand downloadexecutor = new DOWNLOADCommand(currentPath);
			return downloadexecutor.excecuteCommand(command);
		case("exit"):
			EXITCommand exitexecutor = new EXITCommand(currentPath);
			return exitexecutor.excecuteCommand(command);
		default:
			throw new IllegalArgumentException(command[0] + "is not recognized as an internal or external command, operable program or batch file.");
		}
		
	};
	

	
}
