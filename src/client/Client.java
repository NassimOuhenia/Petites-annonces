package client;

import java.net.*;
import java.io.*;
import java.util.*;

import writer.WriterReader;

public class Client {

	private Socket socket;
	private DataOutputStream writer;
	private DataInputStream reader;
	private final Scanner sc = new Scanner(System.in);

	public Client(Socket _socket) {
		socket = _socket;

		try {
			writer = new DataOutputStream(socket.getOutputStream());
			reader = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void connect() throws Exception {
		String response = reader.readUTF();
		while (!socket.isClosed() && !response.equals(WriterReader.LOGOUT)) {
			
			System.out.println(response);
			WriterReader.ecrire(WriterReader.lireClavier(sc), writer);
			response = reader.readUTF();
		}
		
		System.out.println(WriterReader.LOGOUT);
		socket.close();
		writer.close();
		reader.close();
	}
}
