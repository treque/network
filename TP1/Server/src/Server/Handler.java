package Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import Server.Commands.CommandReader;

public class Handler extends Thread {

	private Socket socket;
	private CommandReader cmdReader;
	
	public Handler(Socket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void run()
	{
		System.out.println("Socket: " + socket + " connection established");
		cmdReader = new CommandReader(new File(System.getProperty("user.dir")), socket);
		DataInputStream in = null;
		DataOutputStream dos = null;
		// upload
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		// download
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		int bytesRead;
		int currentBytes = 0;
		
		SimpleDateFormat dateFormatter= new SimpleDateFormat(" yyyy-MM-dd'@'HH:mm:ss");
		
		try {
			in = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			String clientInput = "abc";
			
			while (!clientInput.equals("exit"))
			{
				clientInput = in.readUTF();
				Date date = new Date(System.currentTimeMillis());
				System.out.println("[" + socket.getInetAddress().toString() + ":" + socket.getPort() + dateFormatter.format(date) + "]: " + clientInput); // a format
				String response = cmdReader.executor(clientInput);
				
				// uploading file to client
				if (response.startsWith("size"))
				{
					File newFile = new File(cmdReader.currentDir.getPath() + File.separator + clientInput.split(" ")[1]);
					byte [] byteArray = new byte[(int)newFile.length()];
					fis = new FileInputStream(newFile);
					bis = new BufferedInputStream(fis);
					bis.read(byteArray, 0, byteArray.length);
					
					dos.writeUTF(response);
					dos.flush();
					dos.write(byteArray, 0, byteArray.length);
					dos.flush();
				}
				
				// receiving file from client
				else if (response.startsWith("filename"))
				{	
					dos.writeUTF(response);
					dos.flush();
					clientInput = in.readUTF();
					int size = (int) Double.parseDouble(clientInput.split(" ")[1]);
					byte [] byteArray = new byte[size];
					fos = new FileOutputStream("uploads" + File.separator + response.split(" ")[1]);
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
				}
				
				else {
					dos.writeUTF(response);
				}

				dos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in!=null) in.close();
				if (dos!=null) dos.close();
				if (fis!=null) fis.close();
				if (bis!=null) bis.close();	
				if (fos!=null) fos.close();
				if (bos!=null) bos.close();		
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			
		}
	}
}
