package cen3024c;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/** 
 * The GUI class handles file selection and displays the top 20 words returned by InputHandler
 */
public class GUI {
		
	public static File file;
	public static void constructGUI() {
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridLayout layout1 = new GridLayout(4,1);
		GridLayout layout2 = new GridLayout(1,1);
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		JPanel p1 = new JPanel();
		p1.setLayout(layout1);

		JPanel p2 = new JPanel();
		p2.setLayout(layout2);
		

		// Displays top 20 words in a table
		String column[] = {"Word","Count"};
		JTable jt = new JTable(InputHandler.wordList,column);
		
		
		JScrollPane sp = new JScrollPane(jt);
		
		
		
		// Input file chooser
		JButton selectButton = new JButton("Choose File");
		JLabel label = new JLabel();
		selectButton.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            JFileChooser fileChooser = new JFileChooser();
	            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	            int option = fileChooser.showOpenDialog(f);
	            if(option == JFileChooser.APPROVE_OPTION){
	               file = fileChooser.getSelectedFile();
	               label.setText("Selected: " + file.getAbsoluteFile().getParent()+"\\"+file.getName());
	               InputHandler.inputHandler();
	               InputHandler.wordCount();
	               jt.repaint();
	            }else{
	               label.setText("Open command canceled");
	            }
	         }
		});
		
		
		JLabel dirText = new JLabel("Input Directory of Story File:");
		JLabel topWords = new JLabel ("Top 20 Words");
		topWords.setHorizontalAlignment(JLabel.CENTER);

		// add to panels
		p1.add(dirText);
		p1.add(label);
		p1.add(selectButton);
		p1.add(topWords);
		p2.add(sp);

		// set panels in container panel
		container.add(p1);
		container.add(p2);
		
		// pack frame with contents
		f.add(container);
		f.setSize(600,600);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
