package model;


import java.util.ArrayList;

public interface GestionnaireModel {

	public ArrayList<AnnonceModel> getAnnonces();

	public ArrayList<UtilisateurModel> getUtilisateurs();

	public String diffuserAnnonces(ArrayList<AnnonceModel> _annonces);

	public String diffuserUtilisateursEnLigne(UtilisateurModel user, ArrayList<UtilisateurModel> utilConnect);

	public ArrayList<UtilisateurModel> getUserEnligneAyanAnnonce();

	public void addAnnonce(AnnonceModel annonce);

	public void retirerAnnonce(AnnonceModel annonce);

	public void addUtilisateur(UtilisateurModel utilisateur);

	public ArrayList<AnnonceModel> afficherAnnonce(UtilisateurModel um);
}
