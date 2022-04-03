import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class producer extends JFrame {
	public static String hostname = "localhost";
	public static int port = 6006;
	private DataInputStream fromServer;
	private static DataOutputStream toServer;// = new DataOutputStream(socket.getOutputStream());

	

	public static void main(String[] args) throws Exception {
		try {
			Socket socket = new Socket(hostname, port);
			// create an output stream to send data to the leader
			toServer = new DataOutputStream(socket.getOutputStream());
			Gui p =new Gui(toServer);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
