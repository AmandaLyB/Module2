package cen3024c;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class GUI {
	
	public static void constructGUI() {
		
		
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridLayout layout1 = new GridLayout(2,1);
		GridLayout layout2 = new GridLayout(1,1);
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		JPanel p1 = new JPanel();
		p1.setLayout(layout1);

		JPanel p2 = new JPanel();
		p2.setLayout(layout2);
		
		JLabel dirText = new JLabel("<html><body>Input Directory of Story File:<br>D:\\Valencia\\CEN 3024C SoftDev 1\\story.txt</body></html>");
		JLabel topWords = new JLabel ("Top 20 Words");
		
		// Displays top 20 words in a table
		String column[] = {"Word","Count"};
		JTable jt = new JTable(InputHandler.wordList,column);
		JScrollPane sp=new JScrollPane(jt);

		p1.add(dirText);
=
		p2.add(sp);

		container.add(p1);
		container.add(p2);
		
		f.add(container);
		f.setSize(800,800);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
