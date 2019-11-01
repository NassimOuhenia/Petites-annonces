package application;

import model.*;

public class Annonce implements AnnonceModel {

	private UtilisateurModel utilisateur;
	private String domaine;
	private String descriptif;
	private int prix;

	@Override
	public UtilisateurModel getUtilisateur() {

		return utilisateur;
	}

	@Override
	public String getDomaine() {
		return domaine;
	}

	@Override
	public String getDescriptif() {
		return descriptif;
	}

	@Override
	public int getPrix() {
		return prix;
	}

	@Override
	public void setDomaine(String new_domaine) {
		domaine = new_domaine;
	}

	@Override
	public void setDescriptif(String new_descriptif) {
		descriptif = new_descriptif;
	}

	@Override
	public void setPrix(int new_prix) {
		prix = new_prix;
	}

	@Override
	public String toString() {
		return utilisateur.toString() + "\tdomaine: " + domaine + "\tdescrption: " + descriptif + "\tprix: " + prix;
	}

	@Override
	public void setUtilisateur(UtilisateurModel new_utilisateur) {
		utilisateur=new_utilisateur;
		
	}

}
