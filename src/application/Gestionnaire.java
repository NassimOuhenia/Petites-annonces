package application;

import java.util.ArrayList;

import model.*;

public class Gestionnaire implements GestionnaireModel {

	private ArrayList<AnnonceModel> annonces;
	private ArrayList<UtilisateurModel> utilisateurs;

	public Gestionnaire() {
		annonces = new ArrayList<>();
		utilisateurs = new ArrayList<>();
	}

	@Override
	public ArrayList<AnnonceModel> getAnnonces() {
		return annonces;
	}

	@Override
	public ArrayList<UtilisateurModel> getUtilisateurs() {
		return utilisateurs;
	}

	public ArrayList<UtilisateurModel> getUserEnligneAyanAnnonce() {

		ArrayList<UtilisateurModel> utilisateurs_en_ligne = new ArrayList<UtilisateurModel>();

		for (int i = 0; i < utilisateurs.size(); i++) {
			if (utilisateurs.get(i).getEnLigne() && utilisateurs.get(i).getHaveAnnonce()) {
				utilisateurs_en_ligne.add(utilisateurs.get(i));
			}
		}

		return utilisateurs_en_ligne;
	}

	@Override
	public String diffuserAnnonces(ArrayList<AnnonceModel> _annonces) {

		String response = "";
		for (int i = 0; i < _annonces.size(); i++) {
			response += "Annonce n°" + i + " : " + _annonces.get(i).toString() + "\n";
		}

		return response;

	}

	@Override
	public ArrayList<AnnonceModel> afficherAnnonce(UtilisateurModel um) {
		ArrayList<AnnonceModel> result = new ArrayList<AnnonceModel>();

		for (AnnonceModel am : annonces) {
			if (am.getUtilisateur().equals(um))
				result.add(am);
		}

		return result;
	}

	@Override
	public void addAnnonce(AnnonceModel annonce) {
		annonces.add(annonce);
	}

	@Override
	public void retirerAnnonce(AnnonceModel annonce) {
		annonces.remove(annonce);
	}

	@Override
	public void addUtilisateur(UtilisateurModel utilisateur) {
		utilisateurs.add(utilisateur);
	}

	@Override
	public String toString() {
		return annonces.toString();
	}

	@Override
	public String diffuserUtilisateursEnLigne(UtilisateurModel user, ArrayList<UtilisateurModel> utilConnect) {
		// TODO Auto-generated method stub
		String response = "";
		for (int i = 0; i < utilConnect.size(); i++) {
			if (user.getSocket().equals(utilConnect.get(i).getSocket())
					&& user.getPseudo().equals(utilConnect.get(i).getPseudo())
					&& user.getEnLigne() == utilConnect.get(i).getEnLigne()) {

			} else {
				return response += "Utilisateur n°" + i + " : " + utilConnect.get(i).toString() + "\n";
			}
		}
		return null;
	}
}
