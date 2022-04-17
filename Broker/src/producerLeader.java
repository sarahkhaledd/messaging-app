
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class producerLeader extends JFrame {
	public ServerSocket serverSocket;
	public DataOutputStream toServer;
	public DataInputStream fromServer;

	public ServerSocket serverSocket1;

	public Socket PartitionA;
	public Socket producerSocket;
	boolean flag = true;
	String msg;
	public ArrayList<String> messages = new ArrayList<String>();

	public JTextArea jta = new JTextArea();

	public void recieveData() throws IOException {
		messages.clear();
		DataInputStream inputFromClient = new DataInputStream(producerSocket.getInputStream());

		jta.append("\n" + "if you want to save and exit press 'exit' " + "\n");
		// PartitionLeader leader =new PartitionLeader();
		while (true) {
			msg = inputFromClient.readUTF();

			if (msg.equals("exit") || msg.equals("EXIT")) {
				messages.add(msg);

				jta.append("\n" + "okay thank you  " + "\n");
				// producerSocket.shutdownInput();
				break;
			}

			else {
				messages.add(msg);
				// PartitionLeader

				jta.append("\n" + "msg recieved successfully" + "\n");

			}

		}

	}

	public void producerLeader() {
		try {
			Gui gui = new Gui(jta);
			serverSocket = new ServerSocket(6006);
			serverSocket1 = new ServerSocket(8008);

			while (true) {
				producerSocket = serverSocket.accept();
				jta.append("Successfully connected for producer started at : " + new Date() + "\n");
				Thread th1 = new Thread(new Runnable() {
					@Override
					public void run() {

						try {

							recieveData();
							PartitionA = serverSocket1.accept();
							toServer = new DataOutputStream(PartitionA.getOutputStream());
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

							// p.connection();

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				th1.start();
			}
		} catch (IOException e) {
			System.out.println("Problem with the socket server.");
		}

	}
	public static void main(String[] args) 
	{
		producerLeader producerleader =new producerLeader();
		producerleader.producerLeader();

	}

}
