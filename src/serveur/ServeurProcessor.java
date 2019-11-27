package serveur;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import application.*;
import model.*;
import writer.WriterReader;

public class ServeurProcessor implements Runnable {

	private Socket sock;
	private PrintWriter writer;
	private BufferedReader reader;
	private Utilisateur user;
	private GestionnaireModel gstionnaire;
	boolean demandeConnection = false;

	public ServeurProcessor(Socket pSock, GestionnaireModel gs) {

		sock = pSock;
		gstionnaire = gs;

		try {
			writer = new PrintWriter(sock.getOutputStream());

			reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
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
		user.setHaveAnnonce(true);
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

				// faire savoir que cet utilisateur na pas dannonce apres supresion
				if (mesAnonnces.isEmpty()) {
					user.setHaveAnnonce(false);
				}

			} catch (Exception e) {
				// TODO: handle exception
				return WriterReader.NUMBER_FORMAT;
			}
		}
		return WriterReader.DELETE_ANNONCE_SUCCES;

	}
	// -----------------------------------------------------------------------------------------------------------------------------

	public String connecterClient() {

		ArrayList<UtilisateurModel> utilConnec = gstionnaire.getUserEnligneAyanAnnonce();
		String reponse = null;

		if (!utilConnec.isEmpty()) {
			String reponseUserConnect = gstionnaire.diffuserUtilisateursEnLigne(user, utilConnec);
			if (reponseUserConnect == null) {
				return WriterReader.PA_CLIENT_EN_LIGNE_Annonceur;

			} else {

				WriterReader.ecrire(reponseUserConnect + " " + WriterReader.CHOI_Client, writer);
				reponse = WriterReader.lire(reader);
				System.out.println("voila" + reponse);
				while (reponse.equals("")) {
					WriterReader.ecrire(WriterReader.CHOI_Client, writer);
					reponse = WriterReader.lire(reader);

				}
				// ------------------------lutilisateur a taper le correspondant a joindre

				// -----tester si c vrai
				boolean testLogin = false;
				UtilisateurModel userToConnect = null;
				for (int i = 0; i < utilConnec.size(); i++) {
					System.out.println(utilConnec.get(i).getPseudo());
					if (utilConnec.get(i).getPseudo().equals(reponse)) {
						testLogin = true;
						userToConnect = utilConnec.get(i);
					}
				}

				if (testLogin) {

					handshakeClientServer(userToConnect);

				} else {
					return WriterReader.CHOI_Client_incorrect;
				}

			}

		}

		return WriterReader.END_COM_CLIENT;

	}

	// ----------------------------------------------------------------------------------------------------------------------------

	public void handshakeClientServer(UtilisateurModel correspendant) {
		PrintWriter writerServer;

		try {
			writerServer = new PrintWriter(correspendant.getSocket().getOutputStream());
			WriterReader.ecrire(WriterReader.SERVER_SIDE + correspendant.getPseudo(), writer);
			WriterReader.ecrire(WriterReader.CLIENT_SIDE + sock.getInetAddress() + user.getPseudo(), writerServer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// --------------------------------------..................................................................................
	public void bye() {
		WriterReader.ecrire(WriterReader.LOGOUT, writer);
		try {

			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setEnLigne(false);
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
					user = new Utilisateur(sock, reponse, true);
					if (existe()) {
						pseudoajou = true;
					} else {
						requet += WriterReader.ERROR_NAME;
					}
					break;
				case "2":
					WriterReader.ecrire(WriterReader.NAME, writer);

					reponse = WriterReader.lire(reader);
					user = new Utilisateur(sock, reponse, true);
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
				System.out.println("je suis laaaa aussiiiiii" + reponse);

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
					requet = connecterClient();
					break;

				case "5":

					bye();
					break;
				case "6":
					// System.out.println("t entrun de repomdre");
					String rep = WriterReader.lire(reader);

					System.out.println("t entrun de repomdre");
					WriterReader.ecrire(rep, writer);

					rep = WriterReader.lire(reader);
					if (rep.equals("5")) {
						System.out.println("je ferme");
						bye();

					}

				default:
					requet = WriterReader.ERROR;
					break;
				}

			}
		}
	}

}
