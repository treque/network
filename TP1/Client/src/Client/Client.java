package Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

		Socket clientSocket = null;
		try {
			// Création d'un socket client vers le serveur. Ici 127.0.0.1 est indicateur que
			// le serveur s'exécute sur la machine locale. Il faut changer 127.0.0.1 pour
			// l'adresse IP du serveur si celui-ci ne s'exécute pas sur la même machine. Le port est 5000.
			String serverAddress = "127.0.0.1";
			int portNumber = 5000;
			clientSocket = new Socket(serverAddress, portNumber);
			Scanner input = new Scanner(System.in);
			// if ou while?
			String value = "jdkfhjksdhfk";
			if(input.hasNextLine()) 
			{
				
				value = input.nextLine();
			}
			
			DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
			dos.writeUTF(value); // faireun check
		} finally {
			// Fermeture du socket.
			clientSocket.close();
		}
	}
}
