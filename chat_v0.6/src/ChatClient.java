//version 0.6



import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;


public class ChatClient extends Frame{
	
	TextField tfText = new TextField();
	TextArea taContent = new TextArea();
	
	
	public static void main(String[] args) {
		new ChatClient().launchFrame();
	}
	
	public void launchFrame() {
		setLocation(400, 300);
		this.setSize(300, 300);
		add(tfText, BorderLayout.SOUTH);
		add(taContent, BorderLayout.NORTH);
		pack();
		
		// able to click red cross now
		this.addWindowListener(new WindowAdapter () {
			/* (non-Javadoc)
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		tfText.addActionListener(new TfListener());
		setVisible(true);
		connect();
		
	}
	
	public void connect(){
		try {
			Socket s = new Socket("127.0.0.1", 8889);
			System.out.println("Connected to the server :)");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private class TfListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			String s = tfText.getText().trim(); // get rid of blank spaces
			taContent.setText(s);
			tfText.setText("");   // remove user input in text field
			
		}
		
	}

}
