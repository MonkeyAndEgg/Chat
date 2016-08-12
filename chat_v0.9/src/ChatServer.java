import java.io.*;
import java.net.*;



public class ChatServer {

	public static void main(String[] args) {
		boolean started = false; 
		ServerSocket ss = null;
		Socket s = null;
		DataInputStream dis = null;
		
		try {
			ss = new ServerSocket(8889);
		}catch(BindException e){
			System.out.println("The port is currently occupied.");
			System.out.println("Please close the relating programs and then restart the server.");
			System.exit(0);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		try{
			started = true;
			while(started) {
				boolean bConnected = false;
				s = ss.accept();
				System.out.println("A client connected.");
				bConnected = true;
				dis = new DataInputStream(s.getInputStream());
				while(bConnected){ // keep accepting msg
					String str = dis.readUTF();   // get the message from a client
					System.out.println(str);
					
				}
				//dis.close();
				
			}
		} catch (EOFException e) {	
			System.out.println("A client closed.");		
		} catch (IOException e) {		
			e.printStackTrace();	
		}finally {
			
			try {
				if(dis != null) dis.close();
				if(s != null) s.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

}
