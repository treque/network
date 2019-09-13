package Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import Client.Validator;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

		Socket clientSocket = null;
		try {
			// Création d'un socket client vers le serveur. Ici 127.0.0.1 est indicateur que
			// le serveur s'exécute sur la machine locale. Il faut changer 127.0.0.1 pour
			// l'adresse IP du serveur si celui-ci ne s'exécute pas sur la même machine. Le port est 5000.
			boolean isPortValid = false;
			boolean isHostValid = false;
			String serverAddress = null;
			int portNumber = 0;
			Scanner input = new Scanner(System.in);
			do{
				System.out.println("IP: ");
				
				serverAddress = input.nextLine();
				System.out.println(serverAddress);
				isHostValid = Validator.isIPValid(serverAddress);
				if(!isHostValid) {
					System.out.println("IP not valid!");
				}
				System.out.println("PORT: ");
				String port = input.nextLine();
				isPortValid = Validator.isPortValid(port);
				
				if (isPortValid) {
					portNumber = Integer.parseInt(port);

				} else {
					System.out.println("Port not valid!");
				}
			}
			while(!isPortValid ||  !isHostValid);
			
			clientSocket = new Socket(serverAddress, portNumber);
			
			String value = "";
			DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
			while (value != "quit") 
			{
				value = input.nextLine();
				dos.writeUTF(value); // faire un check
				
				dos.flush();
			}
			dos.close();
		} finally {
			// Fermeture du socket.
			clientSocket.close();
		}
	}
}
