package writer;

import java.io.*;
import java.util.Scanner;

public class WriterReader {

	public final static String SEPARATOR = ",";

	public final static int PORT = 2121;
	public final static String IP_ADDERRESS = "localhost";

	public final static String DASHBORD = "\t***Bienvenue sur le serveur d'annonces gestioncoin***"+SEPARATOR+"tapez:\t 1- pour se connecter ** 2- pour s'inscrire";
	public final static String NAME = "Veuillez tapez votre nom";
	public final static String BIENVENUE = "Bienvenue ";
	public final static String MENU = ""+SEPARATOR+"tapez: "+SEPARATOR+"1-pour poster une annonce "+SEPARATOR+"2-pour voir les annonce "+SEPARATOR+"3-pour effacer votre annonce "+SEPARATOR+"4-pour quitter: ";
	public final static String ERROR = "j'ai pas compris !"+SEPARATOR;
	public final static String ERROR_NAME = "veuillez recommencer!"+SEPARATOR;
	public final static String NUMBER_FORMAT = "Veuillez saisir un chiffre corrcet !!"+SEPARATOR;
	public final static String ANNONCE_EMPTY = "il n'y a aucune annonce!"+SEPARATOR;
	public final static String ADD_ANNONCE = "Entrer le domaine:";
	public final static String ADD_DESCRIPTION = "Entrer la description:";
	public final static String ADD_PRICE = "Entrer le prix:";
	public final static String ADD_ANNONCE_SUCCESS = "Annonce ajoutée avec succes"+SEPARATOR;
	public final static String ADD_ANNONCE_FAILURE = "Annonce non ajoutée veuillez bien saisir les champs!"+SEPARATOR;
	public final static String DELETE_ANNONCE = "Veuillez saisir le numero de l'annonce a supprimer"+SEPARATOR;
	public final static String DELETE_ANNONCE_SUCCES = "Annonce supprimée avec succes"+SEPARATOR;

	public final static String LOGOUT = "BYE-BYE";

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
