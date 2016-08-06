//version 0.3
/* Added the function of closing this window
 * 
 */

import java.awt.*;
import java.awt.event.*;


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
		setVisible(true);
		
	}

}