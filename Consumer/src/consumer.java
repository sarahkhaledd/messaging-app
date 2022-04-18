import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextArea;

public class consumer {
	public static String hostname = "localhost";
	public static int port = 5005;
	public static String msg;
	public static DataInputStream fromServer;
	public ArrayList<String> messages = new ArrayList<String>();
	public static JTextArea jta = new JTextArea();

	public static void main(String[] args) throws Exception {

		try {
			Gui gui = new Gui(jta);
			Socket socket = new Socket(hostname, port);
			fromServer = new DataInputStream(socket.getInputStream());

			// get the result from the server

			while (true) {
				msg = fromServer.readUTF();
				System.out.println(msg);
				jta.append("Successfully recieved message from leader: " + msg +" "+ "\n");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
