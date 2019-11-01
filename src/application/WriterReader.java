package application;

import java.io.*;
import java.util.Scanner;

public class WriterReader {
	
	public final static int PORT = 2121;
	public final static String IP_ADDERRESS = "localhost";
	
	public final static String DASHBORD = "\t***Bienvenue sur le serveur d'annonces gestioncoin***\ntapez:\t 1- pour se connecter ** 2- pour s'inscrire";
	public final static String NAME = "Veuillez tapez votre nom";
	public final static String BIENVENUE = "Bienvenue ";
	public final static String MENU = "\ntapez: \n1-pour poster une annonce \n2-pour voir les annonce \n3-pour effacer votre annonce \n4-pour quitter: "; 
	public final static String ERROR = "j'ai pas compris !\n";
	public final static String ERROR_NAME = "veuillez recommencer!\n";
	public final static String NUMBER_FORMAT = "Veuillez saisir un chiffre corrcet !!\n";
	public final static String ANNONCE_EMPTY = "il n'y a aucune annonce!\n";
	public final static String ADD_ANNONCE = "Entrer le domaine:";
	public final static String ADD_DESCRIPTION = "Entrer la description:";
	public final static String ADD_PRICE = "Entrer le prix:";
	public final static String ADD_ANNONCE_SUCCESS = "Annonce ajoutée avec succes\n";
	public final static String ADD_ANNONCE_FAILURE = "Annonce non ajoutée veuillez bien saisir les champs!\n";
	public final static String DELETE_ANNONCE = "Veuillez saisir le numero de l'annonce a supprimer\n";
	public final static String DELETE_ANNONCE_SUCCES = "Annonce supprimée avec succes\n";
	public final static String LOGOUT = "BYE-BYE";
	
	public static String lire(DataInputStream reader) {
		try {
			return reader.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "erreur lecture!";
	}

	public static void ecrire(String reponse, DataOutputStream writer) {
		try {
			writer.writeUTF(reponse);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String lireClavier(Scanner sc) {
		return sc.nextLine();
	}
}
