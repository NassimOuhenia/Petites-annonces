package writer;

import java.io.*;
import java.util.Scanner;

public class WriterReader {

	public final static String SEPARATOR = ",";

	public final static int PORT = 2121;
	public final static String IP_ADDERRESS = "localhost";

	public final static String DASHBORD = "\t***Bienvenue sur le serveur d'annonces gestioncoin***" + SEPARATOR
			+ "tapez:\t 1- pour se connecter ** 2- pour s'inscrire";
	public final static String NAME = "Veuillez tapez votre nom";
	public final static String BIENVENUE = "Bienvenue ";
	public final static String MENU = "" + SEPARATOR + "tapez: " + SEPARATOR + "1-pour poster une annonce " + SEPARATOR
			+ "2-pour voir les annonce " + SEPARATOR + "3-pour effacer votre annonce " + SEPARATOR
			+ "4-Pour vous connecter a un utilisateur en ligne "+SEPARATOR+"5-pour quitter:  ";
	public final static String ERROR = "j'ai pas compris !" + SEPARATOR;
	public final static String ERROR_NAME = "veuillez recommencer!" + SEPARATOR;
	public final static String NUMBER_FORMAT = "Veuillez saisir un chiffre corrcet !!" + SEPARATOR;
	public final static String ANNONCE_EMPTY = "il n'y a aucune annonce!" + SEPARATOR;
	public final static String ADD_ANNONCE = "Entrer le domaine:";
	public final static String ADD_DESCRIPTION = "Entrer la description:";
	public final static String ADD_PRICE = "Entrer le prix:";
	public final static String ADD_ANNONCE_SUCCESS = "Annonce ajoutée avec succes" + SEPARATOR;
	public final static String ADD_ANNONCE_FAILURE = "Annonce non ajoutée veuillez bien saisir les champs!" + SEPARATOR;
	public final static String DELETE_ANNONCE = "Veuillez saisir le numero de l'annonce a supprimer" + SEPARATOR;
	public final static String DELETE_ANNONCE_SUCCES = "Annonce supprimée avec succes" + SEPARATOR;

	public final static String LOGOUT = "BYE-BYE";

/////////////////////////////////////////////////////
	public final static String END_COM_CLIENT = "Merci";
	public final static String PA_CLIENT_EN_LIGNE_Annonceur = "Il n y'a aucun utilisateur en ligne ayant poster une annonce"
			+ SEPARATOR + "veuillez reeseyer plus tard";
	public final static String CHOI_Client = "Veuillez taper le pseudo de l'utilisateur que vous voulez contacter"
			+ SEPARATOR;
	public final static String CHOI_Client_incorrect = "Le nom d'utilisateur est incorrect" + SEPARATOR;
	public final static String CLIENT_SIDE = "client";
	public final static String SERVER_SIDE = "server";
	public final static String SERVER_SIDE1 = "Un utilisateur cherche a vous joindre";
	public final static String SERVER_SIDE_Q = "Taper 1-Pour accepter 2-Pour decliner";

	public static String lire(BufferedReader reader) {
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "erreur lecture!";
	}

	public static void ecrire(String reponse, PrintWriter writer) {
		writer.println(reponse);
		writer.flush();
	}

	public static String lireClavier(Scanner sc) {
		return sc.nextLine();
	}
}
