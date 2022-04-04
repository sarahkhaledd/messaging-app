import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Partitions {
	public DataOutputStream toServer;
	public DataInputStream fromServer;

	public ServerSocket serverSocket;
	public ServerSocket serverSocket1;
	public ServerSocket serverSocket2;
	public Socket PartitionA;
	public Socket PartitionB;
	public Socket PartitionC;

	public int sizeOfA=3;
	public int sizeOfB;
	public int sizeOfC;
	producerLeader leader = new producerLeader();

	public Partitions() {
		try {

			serverSocket = new ServerSocket(8008);
			PartitionA = serverSocket.accept();
			System.out.println("halloo A");
			fromServer = new DataInputStream(PartitionA.getInputStream());

			//sizeOfA = fromServer.read();
			/*
			 * fromServer = new DataInputStream(PartitionB.getInputStream()); fromServer =
			 * new DataInputStream(PartitionC.getInputStream());
			 */
			if (sizeOfA > 5) {
				serverSocket1 = new ServerSocket(8007);
				PartitionB = serverSocket.accept();
				sizeOfB = fromServer.read();
				if (sizeOfB > 5) {
					serverSocket2 = new ServerSocket(8006);
					PartitionC = serverSocket.accept();
					sizeOfC = fromServer.read();
					if (sizeOfC > 5) {
						System.out.println("Full Partitions");
					} else {
						toServer = new DataOutputStream(PartitionC.getOutputStream());

						for (int i = 0; i < leader.messages.size(); i++) {
							toServer.writeUTF(leader.messages.get(i));
						}
					}

				} else {
					toServer = new DataOutputStream(PartitionB.getOutputStream());

					for (int i = 0; i < leader.messages.size(); i++) {
						toServer.writeUTF(leader.messages.get(i));
					}
				}

			} else {
				toServer = new DataOutputStream(PartitionA.getOutputStream());
				System.out.println("i'm A");
				for (int i = 0; i < leader.messages.size(); i++) {
					toServer.writeUTF("ok");
				}
			}
		} catch (IOException e) {
			System.out.println("Problem with the socket server.");
		}

	}

	public static void main(String[] args) {
		Partitions p = new Partitions();

	}
}
