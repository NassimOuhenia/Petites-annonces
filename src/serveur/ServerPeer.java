package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerPeer {

	ServerSocket serveurSocket;
	Socket clientSocket;
	BufferedReader in;
	PrintWriter out;
	Scanner sc = new Scanner(System.in);

	public boolean lancerPeerServeur() {
		
		System.out.println("---------------------Veuillez patientez--------------");

		try {
			serveurSocket = new ServerSocket(5000);
			clientSocket = serveurSocket.accept();
			out = new PrintWriter(clientSocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			out.println(" Vous etes sur le chat");
			out.flush();

			Thread envoi = new Thread(new Runnable() {
				String msg;

				@Override
				public void run() {
					while (true) {
						msg = sc.nextLine();
						out.println(msg);
						out.flush();
					}
				}
			});
			envoi.start();

			Thread recevoir = new Thread(new Runnable() {
				String msg;

				@Override
				public void run() {
					try {
						msg = in.readLine();
						// tant que le client est connecté
						while (msg != null) {
							System.out.println("Client : " + msg);
							msg = in.readLine();
						}
						// sortir de la boucle si le client a déconecté
						System.out.println("Client déconecté");
						// fermer le flux et la session socket
						out.close();
						clientSocket.close();
						serveurSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			recevoir.start();
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
