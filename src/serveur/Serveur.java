package serveur;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import application.*;
import model.*;
import writer.WriterReader;

public class Serveur {

	private ServerSocket server = null;
	private static GestionnaireModel gs;
	static ArrayList<Thread> listeThread = new ArrayList<Thread>();

	public Serveur() throws IOException {
		server = new ServerSocket(WriterReader.PORT);
		gs = new Gestionnaire();

	}

	// On lance notre serveur
	public void open() {

		while (true) {

			try {
				// On attend une connexion d'un client
				Socket client = server.accept();
				
				System.out.println("client" + client.getPort() + client.getRemoteSocketAddress());

				// Une fois reçue, on la traite dans un thread séparé
				Thread t = new Thread(new ServeurProcessor(client, gs));
				t.start();
				listeThread.add(t);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
