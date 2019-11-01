package model;

import java.net.Socket;

public interface UtilisateurModel {

	public Socket getSocket();

	public String getPseudo();

	public boolean equals(Object o);

	public String toString();

}
