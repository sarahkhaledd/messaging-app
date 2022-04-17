import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JTextArea;

public class consumerLeader {

	public ServerSocket serverSocket;
	public Socket consumerSocket;
	boolean flag = true;
	producerLeader p = new producerLeader();

	public void sendData() throws IOException {
		DataOutputStream toServer = new DataOutputStream(consumerSocket.getOutputStream());
		try {
			File myObj = new File("C:\\Users\\DELL\\eclipse-workspace\\partitions\\partitionA.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] info = data.split(" ");
				for(int i=0;i<info.length;i++)
				{
					toServer.writeUTF(info[i]);
				}
				
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public consumerLeader() {

		try {
			

			serverSocket = new ServerSocket(5005);
			while (true) {
				consumerSocket = serverSocket.accept();

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

	public static void main(String[] args) {
		consumerLeader consumerleader = new consumerLeader();
	}

}
