package Server.Commands;
import java.io.File;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;

public class CommandReader {
	
	// Il doit y avoir une classe qui tiendrait en memoire l'etat courant(ou le thread est rendu dans l'arborescence)
	// https://docs.oracle.com/javase/tutorial/essential/io/dirs.html
	
	private File currentDir;
	
	public CommandReader(File currentDir) {
		this.currentDir = currentDir;
	}
	
	public String executor(String input) {
		String[] command = input.split(" ");
		switch(command[0]) {
		case("ls"):
			LSCommand lsExecutor = new LSCommand(currentDir);
			return lsExecutor.executeCommand(command);
		case("cd"):
			CDCommand cdExecutor = new CDCommand(currentDir);
			this.currentDir = new File(cdExecutor.executeCommand(command));
			return this.currentDir.getPath();
		case("mkdir"):
			MKDIRCommand mkdirExecutor = new MKDIRCommand(currentDir);
			return mkdirExecutor.executeCommand(command);
		case("upload"):
			UPLOADCommand uploadExecutor = new UPLOADCommand(currentDir);
			return uploadExecutor.executeCommand(command);
		case("download"):
			DOWNLOADCommand downloadExecutor = new DOWNLOADCommand(currentDir);
			return downloadExecutor.executeCommand(command);
		case("exit"):
			EXITCommand exitExecutor = new EXITCommand(currentDir);
			return exitExecutor.executeCommand(command);
		default:
			return "Unrecognized command";
			//throw new IllegalArgumentException(input + "is not recognized as an internal or external command, operable program or batch file.");
		}
		
	};
	

	
}
