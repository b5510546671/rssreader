package view;

import java.awt.*;

import javax.swing.*;

public class Gui{
	
	private JFrame frame;
	
	public Gui(  ){
		
		frame = new JFrame("RSS Feed");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
	}
	
	public void initComponents(){
		JPanel panel = new JPanel();
		
		JLabel label = new JLabel();
		label.setText("Enter the RSS URL: ");
		
		JTextField urlField = new JTextField(50);
		
		JButton submitbtn = new JButton("Submit");
		
		panel.add(label);
		panel.add(urlField);
		panel.add(submitbtn);
		
		frame.add(panel);
	}
	
	public void run(){
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args){
		Gui ui = new Gui();
		ui.run();
	}
}
