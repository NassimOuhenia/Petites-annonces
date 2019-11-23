package application;

import java.net.*;
import model.*;

public class Utilisateur implements UtilisateurModel {

	private Socket socket;
	private String pseudo;

	public Utilisateur() {

	}

	public Utilisateur(Socket _socket, String reponse) {
		socket = _socket;
		pseudo = reponse;
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

	@Override
	public int hashCode() {
		return pseudo.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof UtilisateurModel) ) {
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


}
