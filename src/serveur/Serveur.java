package serveur;

import java.io.*;
import java.net.*;

import application.*;
import model.*;
import writer.WriterReader;

public class Serveur {

	private ServerSocket server = null;
	private static GestionnaireModel gs;

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
				
				// Une fois reçue, on la traite dans un thread séparé
				Thread t = new Thread(new ServeurProcessor(client, gs));
				t.start();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
