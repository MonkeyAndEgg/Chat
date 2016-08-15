//version 1.1



import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import java.io.*;


public class ChatClient extends Frame{
	
	TextField tfText = new TextField();
	TextArea taContent = new TextArea();
	Socket s = null;
	DataOutputStream dos = null;
	
	
	public static void main(String[] args) {
		new ChatClient().launchFrame();
	}
	
	public void launchFrame() {
		setLocation(400, 300);
		this.setSize(300, 200);
		add(tfText, BorderLayout.SOUTH);
		add(taContent, BorderLayout.NORTH);
		//pack();
		
		// able to click red cross now
		this.addWindowListener(new WindowAdapter () {
			/* (non-Javadoc)
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
			
		});
		tfText.addActionListener(new TfListener());
		setVisible(true);
		connect();
		
	}
	
	public void connect(){
		try {
			s = new Socket("127.0.0.1", 8889);
			dos = new DataOutputStream(s.getOutputStream());
			System.out.println("Connected to the server :)");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void disconnect(){
		try {
			dos.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private class TfListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			String str = tfText.getText().trim(); // get rid of blank spaces
			taContent.setText(str);
			tfText.setText("");   // remove user input in text field
			
			try {
				
				dos.writeUTF(str); // send the message
				dos.flush();
				// dos.close();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
		}
		
	}

}
