import java.io.*;
import java.net.*;
import java.util.*;


public class ChatServer {
	boolean started = false; 
	ServerSocket ss = null; 
	
	List<Client> clients = new ArrayList<Client>();
	
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
				clients.add(c);
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
		private DataOutputStream dos = null;
		private boolean bConnected = false;
		
		public Client(Socket s){
			this.s = s;
			try {
				this.dis = new DataInputStream(s.getInputStream());
				this.dos = new DataOutputStream(s.getOutputStream());
				bConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void send(String str) {
				try {
					dos.writeUTF(str);
				} catch (IOException e) {
					clients.remove(this);
					System.out.println("One client has been removed from the list.");
				}
		}
		
		public void run() {
			Client c = null;
			try{
				while(bConnected){ // keep accepting msg
					String str = dis.readUTF();   // get the message from a client
					System.out.println(str);
					for(int i = 0; i < clients.size(); i++){
						c= clients.get(i);
						c.send(str);
					}
					
				}
			} catch (SocketException e){	
				clients.remove(this);
				System.out.println("A client is disconnected from the server.");
			} catch (EOFException e) {	
				System.out.println("A client closed.");		
			} catch (IOException e) {		
				e.printStackTrace();	
			}finally {	
				try {
					if(dis != null) dis.close();
					if(dos != null) dos.close();
					if(s != null) {
						s.close();
						s = null;   // collect waste
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
			}
			
		}
		
	}
}
