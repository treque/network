package Server.Commands;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class DOWNLOADCommand extends AbstractCommand {

	DOWNLOADCommand(File currentDir, Socket socket) 
	{
		super(currentDir, socket);
	}
	
	@Override
	public String executeCommand(String[] command) {
		File newFile = new File(currentDir.getAbsolutePath() + File.separator + command[1]);
		if (newFile.exists())
		{
			BufferedInputStream bis = null;
			DataOutputStream dos = null;
			FileInputStream fis = null;
			
			try {
				byte [] byteArray = new byte[(int)newFile.length()];
				fis = new FileInputStream(newFile);
				bis = new BufferedInputStream(fis);
				bis.read(byteArray, 0, byteArray.length);
				dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF("size " + Double.toString(newFile.length()));
				dos.flush();
				dos.write(byteArray, 0, byteArray.length);
				dos.flush();
				
				
				return "";
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
					try {
						if(bis !=null) bis.close();
						if(dos !=null) dos.close();
						if(fis !=null) fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
		return "Error, file does not exist.";
	}

}
