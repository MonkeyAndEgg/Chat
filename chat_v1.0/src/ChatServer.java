import java.io.*;
import java.net.*;



public class ChatServer {
	boolean started = false; 
	ServerSocket ss = null;   
	
	public static void main(String[] args) { 
		new ChatServer().start();
	}
	
	public void start(){
		try {
			ss = new ServerSocket(8889);
			started = true;
		}catch(BindException e){
			System.out.println("The port is currently occupied.");
			System.out.println("Please close the relating programs and then restart the server.");
			System.exit(0);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		try{
			while(started) {
				Socket s = ss.accept();
				Client c = new Client(s);
				System.out.println("A client connected.");
				new Thread(c).start();
			}
				
		}catch (IOException e) {		
			e.printStackTrace();	
		}finally {	
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	// thread 
	class Client implements Runnable{
		private Socket s;
		private DataInputStream dis = null;
		private boolean bConnected = false;
		
		public Client(Socket s){
			this.s = s;
			try {
				this.dis = new DataInputStream(s.getInputStream());
				bConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			try{
				while(bConnected){ // keep accepting msg
					String str = dis.readUTF();   // get the message from a client
					System.out.println(str);
					
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
}
