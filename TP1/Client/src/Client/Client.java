package Client;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
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
		DataOutputStream dos = null;
		DataInputStream in = null;
		// download
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		// upload
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		int bytesRead;
		int currentBytes = 0;
		Scanner input = null;
		try {
			// Création d'un socket client vers le serveur. Ici 127.0.0.1 est indicateur que
			// le serveur s'exécute sur la machine locale. Il faut changer 127.0.0.1 pour
			// l'adresse IP du serveur si celui-ci ne s'exécute pas sur la même machine. Le port est 5000.
			String serverAddress = null;
			int portNumber = 0;
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
				if (isPortValid){
					portNumber = Integer.parseInt(inputString);
				}
				else
				{
					System.out.println("The port you entered is not valid!");
				}
			} while (!isPortValid);
			clientSocket = new Socket(serverAddress, portNumber);

			String value = "abc";
			dos = new DataOutputStream(clientSocket.getOutputStream());
			in = new DataInputStream(clientSocket.getInputStream());
			System.out.println(">>");
			while (!value.equals("exit")) 
			{
				value = input.nextLine();
				dos.writeUTF(value); // faire un check
				String response = in.readUTF();
				// download. if size is received
				if (response.startsWith("size"))
				{
					int size = (int) Double.parseDouble(response.split(" ")[1]);
					byte [] byteArray = new byte[size];
					String fileName = value.split(" ")[1];
					fos = new FileOutputStream("downloads" + File.separator + fileName);
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
					System.out.println("Le fichier " + fileName + " a bien ete telecharge.");
				}
				
				// upload	
				else if (response.startsWith("filename"))
				{
					String fileName = response.split(" ")[1];
					File newFile = new File(System.getProperty("user.dir") + File.separator + fileName);
					if (newFile.exists())
					{
						byte [] byteArray = new byte[(int)newFile.length()];
						fis = new FileInputStream(newFile);
						bis = new BufferedInputStream(fis);
						bis.read(byteArray, 0, byteArray.length);
						
						dos.writeUTF("size " + newFile.length());
						dos.flush();
						dos.write(byteArray, 0, byteArray.length);
						System.out.println("Le fichier " + fileName + " a bien ete televerse.");
					}
					else System.out.println("Le fichier " + fileName + " n'existe pas.");
				}
				else 
				{
					System.out.println(response);
				}
				dos.flush();
				System.out.println(">>");
			}
		} finally {
			// Fermeture of stuff
			System.out.println("Vous avez ete deconnecte avec succes.");
			if (fis!=null) fis.close();
			if (bis!=null) bis.close();
			if (dos!=null) dos.close();
			if (in!= null) in.close();
			if (input!=null) input.close();
			if (clientSocket!=null) clientSocket.close();
			
		}
	}
}
