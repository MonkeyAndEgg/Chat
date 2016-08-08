import java.io.*;
import java.net.*;



public class ChatServer {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8889);
			while(true) {
				Socket s = ss.accept();
				System.out.println("A client connected.");
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String str = dis.readUTF();   // get the message from a client
				System.out.println(str);
				dis.close();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
