package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import Client.Validator;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		boolean isPortValid = false;
		boolean isIPValid = false;
		Socket clientSocket = null;
		Scanner input = null;
		try {
			// Création d'un socket client vers le serveur. Ici 127.0.0.1 est indicateur que
			// le serveur s'exécute sur la machine locale. Il faut changer 127.0.0.1 pour
			// l'adresse IP du serveur si celui-ci ne s'exécute pas sur la même machine. Le port est 5000.
			String serverAddress = null;
			int portNumber;
			input = new Scanner(System.in);
			do {
				System.out.println("IP: \n");
				serverAddress = input.nextLine();
				isIPValid = Validator.isIPValid(serverAddress);
				if (!isIPValid) 
				{
					System.out.println("The ip you entered is not valid!");
				}
			} while (!isIPValid);
			do {
				System.out.println("PORT: \n");
				String inputString = input.nextLine();
				 // verifier l'erreur avec le parse
				isPortValid = Validator.isPortValid(inputString);
				portNumber = Integer.parseInt(inputString);
				if (!isPortValid) 
				{
					System.out.println("The port you entered is not valid!");
				}
			} while (!isPortValid);
			clientSocket = new Socket(serverAddress, portNumber);

			String value = "jdkfhjksdhfk";
			DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			while (value != "exit") 
			{
				value = input.nextLine();
				dos.writeUTF(value); // faire un check
				dos.flush();
				System.out.println(in.readUTF());
			}
			
		} finally {
			// Fermeture du socket.
			input.close();
			clientSocket.close();
			
		}
	}
}
