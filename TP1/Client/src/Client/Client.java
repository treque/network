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
			
			Scanner input = new Scanner(System.in);

			System.out.println("IP: ");
			String serverAddress = input.nextLine();
			
			System.out.println("PORT: ");
			int portNumber = Integer.parseInt(input.nextLine());
			
			System.out.println("ASDFASDFASDFASDFASDFSAD");
			clientSocket = new Socket(serverAddress, portNumber);
			System.out.println(clientSocket.getPort());
			clientSocket.close();
			System.out.println(clientSocket.getPort());
			String value = "jdkfhjksdhfk";
			DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
			while (value != "quit") 
			{
				value = input.nextLine();
				dos.writeUTF(value); // faire un check
				dos.flush();
			}
		} finally {
			// Fermeture du socket.
			//clientSocket.close();
		}
	}
}
