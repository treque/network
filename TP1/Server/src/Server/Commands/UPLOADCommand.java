package Server.Commands;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UPLOADCommand extends AbstractCommand 
{
	
	private DataOutputStream dos;
	private DataInputStream in;
	
	UPLOADCommand(File currentDir, Socket socket, DataOutputStream dos, DataInputStream in) 
	{
		super(currentDir, socket);
		this.dos = dos;
		this.in = in;
	}
	
	@Override
	public String executeCommand(String[] command) 
	{
		int bytesRead;
		int currentBytes = 0;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			// respond to the client the name of the file they sent
			dos.writeUTF("filename " + command[1]);
			dos.flush();
			// wait for file size response from client
			String clientResponse = in.readUTF();
			int size = (int) Double.parseDouble(clientResponse.split(" ")[1]);
			byte [] byteArray = new byte[size];
			fos = new FileOutputStream(currentDir + File.separator + command[1]);
			bos = new BufferedOutputStream(fos);
			bytesRead = in.read(byteArray, 0, byteArray.length);
			currentBytes = bytesRead;
			do {
				System.out.println("...");
				bytesRead = in.read(byteArray, currentBytes, (byteArray.length - currentBytes));
				if (bytesRead >= 0)
				{
					currentBytes += bytesRead;
				}
			} while (currentBytes < size);
			
			bos.write(byteArray, 0, currentBytes);
			bos.flush();
			
			return "";
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bos !=null) bos.close();
				if(fos !=null) fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

}
