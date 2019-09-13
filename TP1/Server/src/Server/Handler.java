package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import Server.Commands.CommandReader;

public class Handler extends Thread {

	private Socket socket;
	private CommandReader commandReader;
	public Handler(Socket socket)
	{
		this.socket = socket;
		this.commandReader = new CommandReader(Paths.get("./"));
	}
	@Override
	public void run()
	{
		System.out.println(socket);
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			while (true)
			{
				System.out.println(in.readUTF());
				commandReader.executor(in.readUTF());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
