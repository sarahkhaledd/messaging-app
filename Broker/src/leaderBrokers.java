
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;



class producerLeader extends JFrame {
	public ServerSocket serverSocket;
	public Socket producerSocket;
	boolean flag = true;
	String msg;
	public ArrayList<String> messages = new ArrayList<String>();

	public JTextArea jta = new JTextArea();

	public void recieveData() throws IOException {
		DataInputStream inputFromClient = new DataInputStream(producerSocket.getInputStream());

		jta.append("\n" + "if you want to exit press 'exit' " + "\n");

		while (true) {
			msg = inputFromClient.readUTF();

			if (msg.equals("exit") || msg.equals("EXIT")) {
				jta.append("\n" + "okay thank you  " + "\n");
				break;
			}

			else {
				messages.add(msg);
				//partition
				//consumer partition 1 
				jta.append("\n" + "msg recieved successfully" + "\n");
			}

		}

	}

	public void producerLeader() {
		try {
			Gui gui = new Gui(jta);
			serverSocket = new ServerSocket(6006);
			while (true) {
				producerSocket = serverSocket.accept();
				jta.append("Successfully connected for producer started at : " + new Date() + "\n");
				Thread th1 = new Thread(new Runnable() {
					@Override
					public void run() {

						try {

							recieveData();

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

}
class consumerLeader {
	public ServerSocket serverSocket;
	public Socket consumerSocket;
	boolean flag = true;
	public JTextArea jta = new JTextArea();
	producerLeader p =new producerLeader() ;
	public void sendData() throws IOException {
		 DataOutputStream toServer = new DataOutputStream(consumerSocket.getOutputStream());
		 p.messages.add("ok");
		 toServer.writeUTF(p.messages.get(0));
		 
			toServer.flush();


	}

	public consumerLeader() {

		try {
			Gui gui = new Gui(jta);

			serverSocket = new ServerSocket(5005);
			while (true) {
				consumerSocket = serverSocket.accept();
				jta.append("Successfully connected for consumer started at : " + new Date() + "\n");

				Thread th1 = new Thread(new Runnable() {
					@Override
					public void run() {

						try {
							sendData();

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

}
