package application;

import java.util.ArrayList;

import model.*;
import writer.WriterReader;

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

	@Override
	public String diffuserAnnonces(ArrayList<AnnonceModel> _annonces) {

		String response = "";
		for (int i = 0; i < _annonces.size(); i++) {
			response += "Annonce n°" + String.valueOf(i) + " : " + _annonces.get(i).toString() + WriterReader.SEPARATOR;
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

}
