package partitions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
			// create an output stream to send data to the leader
			toServer = new DataOutputStream(socket.getOutputStream());
			fromServer = new DataInputStream(socket.getInputStream());
			//sizeOfArray();
			recieveDataFromLeader();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sizeOfArray() throws IOException {
		toServer.write(3);
	}

	public void sendDataToLeader() {
		// open fileA
		// store arraylist
		// toServer to leader WriteUTF
	}

	public static void recieveDataFromLeader() throws IOException {
		FileWriter myWriter = new FileWriter("partitionA.txt");

		for (int i = 0;;i++) {
			String msg;
			msg = fromServer.readUTF();
			myWriter.write(msg);
			if (msg != null) {
				messages.add(msg);
				System.out.println(messages.get(i));

			} else
				break;
		}
		// fromServer readUTF
		// store arraylist
		// store it in file
	}
}
