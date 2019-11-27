package application;

import java.net.*;
import model.*;

public class Utilisateur implements UtilisateurModel {

	private Socket socket;
	private String pseudo;
	private boolean enLigne;
	private boolean haveAnnonce;

	public Utilisateur() {
		haveAnnonce = false;
	}

	public Utilisateur(Socket _socket, String reponse, boolean connect) {
		socket = _socket;
		pseudo = reponse;
		enLigne = connect;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	@Override
	public Socket getSocket() {
		return socket;
	}

	public boolean getEnLigne() {
		return enLigne;
	}

	public void setEnLigne(boolean enLigne) {
		this.enLigne = enLigne;
	}

	@Override
	public int hashCode() {
		return pseudo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof UtilisateurModel)) {
			return false;
		} else {
			UtilisateurModel um = (UtilisateurModel) o;
			return pseudo.equals(um.getPseudo());
		}

	}

	@Override
	public String toString() {
		return "Utilisateur: " + pseudo;

	}

	public boolean getHaveAnnonce() {
		return haveAnnonce;
	}

	public void setHaveAnnonce(boolean haveAnnonce) {
		this.haveAnnonce = haveAnnonce;
	}

}
