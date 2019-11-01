package application;

import java.net.*;

public class MainClient {
	public static void main(String[] args) throws Exception {
		Client client = new Client(new Socket(WriterReader.IP_ADDERRESS, WriterReader.PORT));
		client.connect();
	}
}
