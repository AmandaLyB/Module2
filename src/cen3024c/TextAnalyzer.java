package cen3024c;
import java.sql.*;


import javax.swing.SwingUtilities;

public class TextAnalyzer {

	public static void main(String[] args) {
	
			SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GUI.constructGUI();
			}
		});
	}
}
