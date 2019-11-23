package client;

import java.net.*;
import java.io.*;
import java.util.*;

import writer.WriterReader;

public class Client {

	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private final Scanner sc = new Scanner(System.in);

	public Client(Socket _socket) {
		socket = _socket;
		
		try {
			writer = new PrintWriter(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void connect() throws Exception {
		String response = WriterReader.lire(reader);

		while (!socket.isClosed() && !response.equals(WriterReader.LOGOUT)) {
			String[] reponse = response.split(WriterReader.SEPARATOR);
			for (String s : reponse)
				System.out.println(s);
			WriterReader.ecrire(WriterReader.lireClavier(sc), writer);
			response = WriterReader.lire(reader);
		}

		System.out.println(WriterReader.LOGOUT);
		socket.close();
		writer.close();
		reader.close();
	}
}
