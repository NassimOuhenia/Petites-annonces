package model;

import java.net.Socket;

public interface UtilisateurModel {

	public Socket getSocket();

	public String getPseudo();
	
	public boolean getEnLigne();

	public void setEnLigne(boolean enLigne);

	public boolean getHaveAnnonce();

	public void setHaveAnnonce(boolean haveAnnonce);
}
