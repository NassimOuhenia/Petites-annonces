package model;


import java.util.ArrayList;

public interface GestionnaireModel {

	public ArrayList<AnnonceModel> getAnnonces();

	public ArrayList<UtilisateurModel> getUtilisateurs();

	public String diffuserAnnonces(ArrayList<AnnonceModel> _annonces);

	public void addAnnonce(AnnonceModel annonce);

	public void retirerAnnonce(AnnonceModel annonce);

	public void addUtilisateur(UtilisateurModel utilisateur);

	public ArrayList<AnnonceModel> afficherAnnonce(UtilisateurModel um);

	public String toString();
}
