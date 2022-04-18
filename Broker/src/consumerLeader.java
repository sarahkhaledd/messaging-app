import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JTextArea;

public class consumerLeader {

	public ServerSocket serverSocket;
	public ServerSocket serverSocket1;

	public Socket consumerSocket;
	public Socket partition;

	boolean flag = true;
	public static String msg;
	public static DataInputStream fromServer;
	public static DataOutputStream toServer;// = new DataOutputStream(socket.getOutputStream());

	producerLeader p = new producerLeader();
	public static ArrayList<String> messages = new ArrayList<String>();

	public void recieveDataFromPartitions() throws IOException {
		while (true) {
			fromServer = new DataInputStream(partition.getInputStream());
			msg = fromServer.readUTF();
			if (msg.equals("exit")) {
				break;
			} else
				messages.add(msg);
		}

	}

	public consumerLeader() {

		try {

			serverSocket = new ServerSocket(5005);
			serverSocket1 = new ServerSocket(4005);

			while (true) {
				partition = serverSocket1.accept();
				recieveDataFromPartitions();
				
				  consumerSocket = serverSocket.accept(); toServer = new
				  DataOutputStream(consumerSocket.getOutputStream());
				  Thread th1 = new Thread(new Runnable() {
							@Override
							public void run() {
								for (int i = 0; i < messages.size(); i++) {
									try {
										toServer.writeUTF(messages.get(i));
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						});
				  th1.start();
				 
			}

		} catch (IOException e) {
			System.out.println("Problem with the socket server.");
		}

	}

	public static void main(String[] args) {
		consumerLeader consumerleader = new consumerLeader();
	}

}
