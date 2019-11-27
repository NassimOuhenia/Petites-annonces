package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import writer.WriterReader;

public class ClientPeer {

	Socket clientSocket;
	BufferedReader in;
	PrintWriter out;
	Scanner sc = new Scanner(System.in);// pour lire à partir du clavier

	public String lancerPeerClient(String t) {

		try {
			/*
			 * les informations du serveur ( port et adresse IP ou nom d'hote 127.0.0.1 est
			 * l'adresse local de la machine
			 */
			// System.out.println("lDRESSE RECU ETS"+t);
			clientSocket = new Socket("localhost", 5000);

			// flux pour envoyer
			out = new PrintWriter(clientSocket.getOutputStream());
			// flux pour recevoir
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			Thread envoyer = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						WriterReader.ecrire(WriterReader.lireClavier(sc), out);
					}
				}
			});
			envoyer.start();

			Thread recevoir = new Thread(new Runnable() {
				String msg;

				@Override
				public void run() {
					try {
						while ((msg = in.readLine()) != null) {
							System.out.println("Serveur : " + msg);
							msg = in.readLine();
						}
						System.out.println("Serveur déconecté");
						out.close();
						clientSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			recevoir.start();

		} catch (IOException e) {
			e.printStackTrace();
			return "no";
		}
		return "ok";
	}
}
