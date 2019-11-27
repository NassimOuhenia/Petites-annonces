package client;

import java.net.*;
import java.io.*;
import java.util.*;

import serveur.ServerPeer;
import writer.WriterReader;

public class Client {

	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private final Scanner sc = new Scanner(System.in);
	String response = null;
	String adresseCOrrespandant = "";
	String pseudoCorrespant = "";
	String beContacted = "";

	public Client(Socket _socket) {
		socket = _socket;

		try {
			writer = new PrintWriter(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void printMsg(String response) {
		String str[] = response.split(WriterReader.SEPARATOR);

		for (String s : str) {
			System.out.println(s);
		}
	}

	void connect() throws Exception {
		response = WriterReader.lire(reader);
		printMsg(response);

		Thread envoyer = new Thread(new Runnable() {
			String envoi = null;

			@Override
			public void run() {

				while (!socket.isClosed()) {
					envoi = WriterReader.lireClavier(sc);
					WriterReader.ecrire(envoi, writer);
					// System.out.println("jenvoiiii"+envoi);
				}
				// System.out.println("je suis sortiiii");
			}
		});
		envoyer.start();

		Thread recevoir = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					String response = WriterReader.lire(reader);
					boolean serveurDemnde = false;
					boolean ClientDemande = false;
					while (!socket.isClosed() && !response.equals(WriterReader.LOGOUT)) {
						if (response.contains(WriterReader.CLIENT_SIDE)) {
							if (!ClientDemande) {
								System.out.println(WriterReader.SERVER_SIDE1 + " " + WriterReader.SERVER_SIDE_Q);

								ClientDemande = true;
								adresseCOrrespandant = response.substring(7, 16);
								pseudoCorrespant = response.substring(16);

								// System.out.println("ladsresse"+adresseCOrrespandant+pseudoCorrespant);
								WriterReader.ecrire("6", writer);

							}
						} else if (ClientDemande == true && response.equals("1") && !adresseCOrrespandant.equals("")) {

							ClientPeer clientl = new ClientPeer();

							String connection = clientl.lancerPeerClient(adresseCOrrespandant);
							System.out.println("---------vous etes connecter avec" + pseudoCorrespant);
							// Socket clientSocket = new Socket("localhost",5000);
							while (connection.equals("no")) {
								connection = clientl.lancerPeerClient(response.substring(3));
							}

							WriterReader.ecrire("5", writer);
							envoyer.stop();
							// socket.close();
							// writer.close();
							// reader.close();

							// this.wait();

						} else

						if (response.contains(WriterReader.SERVER_SIDE) || serveurDemnde) {
							if (!serveurDemnde) {

								serveurDemnde = true;
								ServerPeer serverdesig = new ServerPeer();
								serverdesig.lancerPeerServeur();
								beContacted = response.substring(6);
								System.out.println("---------vous etes connecter avec" + beContacted);
								// socket.close();
								WriterReader.ecrire("5", writer);
								envoyer.stop();

							} else {
								printMsg(response);
							}

						} else {

							printMsg(response);
						}
						if (!socket.isClosed()) {
							response = WriterReader.lire(reader);
						}

					}
					// System.out.println("Serveur déconecté");
					// System.out.println(WriterReader.LOGOUT);
					envoyer.stop();
					socket.close();
					writer.close();
					reader.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		recevoir.start();

	}
}
