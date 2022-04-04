import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JTextArea;

public class consumerLeader {

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
