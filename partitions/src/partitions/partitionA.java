package partitions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class partitionA {
	public static String hostname = "localhost";
	public static int port = 8008;
	public static DataInputStream fromServer;
	public static DataOutputStream toServer;// = new DataOutputStream(socket.getOutputStream());
	public static ArrayList<String> messages = new ArrayList<String>();

	public static void main(String[] args) {
		try {
			Socket socket = new Socket(hostname, port);
			while (true)
			{
			toServer = new DataOutputStream(socket.getOutputStream());
			fromServer = new DataInputStream(socket.getInputStream());
			recieveDataFromLeader();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void recieveDataFromLeader() throws IOException {
		FileWriter writer = new FileWriter("partitionA.txt",true);  
		String msg;
		while (true) {
			msg = fromServer.readUTF();
			if(msg.equals("exit") || msg.equals("EXIT"))
			{
				break;
			}
			messages.add(msg);
	}
		for (int i = 0; i < messages.size(); i++) {
			writer.write(messages.get(i)+" ");
			
		}
		writer.close();
		

	}
}
