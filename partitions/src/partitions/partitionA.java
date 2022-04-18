package partitions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class partitionA {
	public static String hostname = "localhost";
	public static int port = 8008;
	public static int leaderPort = 4005;
	public static int counter = 1, count = 0;
	public static DataInputStream fromServer;
	public static DataOutputStream toServer;// = new DataOutputStream(socket.getOutputStream());
	public static ArrayList<String> messages = new ArrayList<String>();

	public static void main(String[] args) {
		try {
			Socket socket = new Socket(hostname, port);
			// Socket socket1 = new Socket(hostname, leaderPort);
			while (true) {
				// toServer = new DataOutputStream(socket1.getOutputStream());
				fromServer = new DataInputStream(socket.getInputStream());
				recieveDataFromLeader();
				// sendDataToLeader();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sizeOfFile() throws IOException {
		count = 0;
		BufferedReader reader = new BufferedReader(new FileReader("partition" + counter + ".txt"));
		String line;
		while ((line = reader.readLine()) != null) {
			count++;
		}

	}

	public static void recieveDataFromLeader() throws IOException {
		String msg;
		int size = 1;
		FileWriter writer = new FileWriter("partition" + counter + ".txt", true);

		while (true) {

			msg = fromServer.readUTF();
			if (msg.equals("exit") || msg.equals("EXIT")) {
				break;
			} else
				messages.add(msg);
		}

		for (int i = 0; i < messages.size(); i++) {
			sizeOfFile();

			if (size % 10 == 0 || count >= 10) {

				writer.close();
				counter++;
				writer = new FileWriter("partition" + counter + ".txt", true);
				sizeOfFile();
				if(count<10)
				{
					writer.write(messages.get(i) + "\n");
					count++;

				}
			} else {
				sizeOfFile();
				writer.write(messages.get(i) + "\n");
				count++;

			}
			size++;
		}
		writer.close();
	}

	public static void sendDataToLeader() throws IOException {
		while (true) {
			BufferedReader reader;
			try {
				File file = new File("partition" + counter + ".txt");
				if (file.exists()) {
					reader = new BufferedReader(new FileReader("partition" + counter + ".txt"));

					String line;
					while ((line = reader.readLine()) != null) {

						toServer.writeUTF(line);
					}
					reader.close();
				} else
					break;
				counter++;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		toServer.writeUTF("exit");

	}

}
