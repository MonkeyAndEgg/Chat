import java.io.*;
import java.net.*;



public class ChatServer {

	public static void main(String[] args) {
		boolean started = false; 
		
		try {
			ServerSocket ss = new ServerSocket(8889);
			started = true;
			while(started) {
				boolean bConnected = false;
				Socket s = ss.accept();
				System.out.println("A client connected.");
				bConnected = true;
				DataInputStream dis = new DataInputStream(s.getInputStream());
				while(bConnected){ // keep accepting msg
					String str = dis.readUTF();   // get the message from a client
					System.out.println(str);
					
				}
				dis.close();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
