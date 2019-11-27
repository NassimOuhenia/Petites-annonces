package model;

public interface AnnonceModel {

	public UtilisateurModel getUtilisateur();

	public void setUtilisateur(UtilisateurModel new_utilisateur);

	public String getDomaine();

	public String getDescriptif();

	public int getPrix();

	public void setDomaine(String new_domaine);

	public void setDescriptif(String new_descriptif);

	public void setPrix(int new_prix);

}
