package application;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import model.*;

public class ServeurProcessor implements Runnable {

	private Socket sock;
	private DataOutputStream writer;
	private DataInputStream reader;
	private Utilisateur user;
	private GestionnaireModel gstionnaire;

	public ServeurProcessor(Socket pSock, GestionnaireModel gs) {

		sock = pSock;
		gstionnaire = gs;

		try {
			writer = new DataOutputStream(sock.getOutputStream());
			reader = new DataInputStream(sock.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String affiche_annonce() {
		if (!gstionnaire.getAnnonces().isEmpty()) {
			return gstionnaire.diffuserAnnonces(gstionnaire.getAnnonces());
		}

		else
			return WriterReader.ANNONCE_EMPTY;
	}

	public String saisi_annonce() {

		String reponse = "";
		Annonce annonce = new Annonce();

		WriterReader.ecrire(WriterReader.ADD_ANNONCE, writer);
		reponse = WriterReader.lire(reader);
		annonce.setDomaine(reponse);

		WriterReader.ecrire(WriterReader.ADD_DESCRIPTION, writer);

		reponse = WriterReader.lire(reader);
		annonce.setDescriptif(reponse);

		WriterReader.ecrire(WriterReader.ADD_PRICE, writer);

		try {
			reponse = WriterReader.lire(reader);
			annonce.setPrix(Integer.valueOf(reponse));
		} catch (NumberFormatException e) {

			return WriterReader.ADD_ANNONCE_FAILURE;

		}

		annonce.setUtilisateur(user);
		gstionnaire.addAnnonce(annonce);
		return WriterReader.ADD_ANNONCE_SUCCESS;

	}

	public String supprimmer_annonce() {

		ArrayList<AnnonceModel> mesAnonnces = gstionnaire.afficherAnnonce(user);

		if (mesAnonnces.isEmpty()) {
			return WriterReader.ANNONCE_EMPTY;
		}
		String requet = "";
		String reponse = "";

		requet = gstionnaire.diffuserAnnonces(mesAnonnces);
		WriterReader.ecrire(requet + WriterReader.DELETE_ANNONCE, writer);

		boolean saisi = true;

		while (saisi) {

			try {

				reponse = WriterReader.lire(reader);
				int index = Integer.valueOf(reponse);
				gstionnaire.retirerAnnonce(mesAnonnces.get(index));
				saisi = false;

			} catch (Exception e) {
				// TODO: handle exception
				return WriterReader.NUMBER_FORMAT;
			}
		}
		return WriterReader.DELETE_ANNONCE_SUCCES;

	}

	public void bye() {
		WriterReader.ecrire(WriterReader.LOGOUT, writer);
		try {

			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean existe() {

		for (UtilisateurModel u : gstionnaire.getUtilisateurs()) {
			if (u.equals(user))
				return true;
		}
		return false;
	}

	public void run() {
		boolean pseudoajou = false;
		String reponse = "";
		String requet = "";
		String inscrire;
		// tant que la connexion est active, on traite les demandes
		while (!sock.isClosed()) {

			if (!pseudoajou) {

				requet += WriterReader.DASHBORD;

				WriterReader.ecrire(requet, writer);
				inscrire = WriterReader.lire(reader);
				requet = "";
				switch (inscrire) {

				case "1":
					WriterReader.ecrire(WriterReader.NAME, writer);

					reponse = WriterReader.lire(reader);
					user = new Utilisateur(sock, reponse);
					if (existe()) {
						pseudoajou = true;
					} else {
						requet += WriterReader.ERROR_NAME;
					}
					break;
				case "2":
					WriterReader.ecrire(WriterReader.NAME, writer);

					reponse = WriterReader.lire(reader);
					user = new Utilisateur(sock, reponse);
					if (!existe()) {
						gstionnaire.addUtilisateur(user);
						pseudoajou = true;
					} else {
						requet += WriterReader.ERROR_NAME;
					}
					break;

				default:
					requet += WriterReader.ERROR;
					continue;
				}

			} else {

				requet += WriterReader.BIENVENUE + user.getPseudo() + WriterReader.MENU;

				WriterReader.ecrire(requet, writer);

				reponse = WriterReader.lire(reader);
				requet = "";
				switch (reponse) {
				case "1":
					requet = saisi_annonce();
					break;
				case "2":
					requet = affiche_annonce();
					break;
				case "3":
					requet = supprimmer_annonce();
					break;
				case "4":

					bye();
					break;
				default:
					requet = WriterReader.ERROR;
					break;
				}
			}
		}
	}
}
