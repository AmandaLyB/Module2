package cen3024c;

import javax.swing.SwingUtilities;

public class TextAnalyzer {

	public static void main(String[] args) {
		
		InputHandler.inputHandler();
		InputHandler.wordCount();
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUI.constructGUI();
			}
		});
	}
}
