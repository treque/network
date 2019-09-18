package Server.Commands;

import java.io.DataOutputStream;
import java.nio.file.Path;

public class CDCommand extends AbstractCommand {
	
	CDCommand(Path currentPath) {
		super(currentPath);
	}

	@Override
	public String executeCommand(String[] command) {
		return "Showing you what cd command does";
	}
	
	

}
