package view;

import java.awt.*;

import javax.swing.*;
import javax.xml.bind.JAXBException;

import model.Rss;
import controller.RssController;

public class Gui extends JFrame{
	
	private RssController controller;
	
	private JLabel label;
	private JTextField urlField;
	private JButton submitbtn;
	private JButton clearbtn;
	private JTextArea itemArea;
	private JTextArea feedArea;
	
	public Gui(){
		
		RssController controller = new RssController();
		Rss rss = null;
		try {
			rss = controller.start("http://feeds.reuters.com/reuters/Election2012?format=xml");
			//rss = controller.start("http://feeds.bbci.co.uk/news/rss.xml");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		setTitle("RSS Feed");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(900,200));
		initComponents();
	}
	
	public void initComponents(){
		JPanel panel = new JPanel();
		
		label = new JLabel();
		label.setText("Enter the RSS URL: ");
		
		urlField = new JTextField(50);
		
		submitbtn = new JButton("Submit");
		
		clearbtn = new JButton("Clear");
		
		itemArea = new JTextArea(10, 50);
		itemArea.setEditable(false);
		
		feedArea = new JTextArea(10, 80);
		feedArea.setEditable(false);
		
		panel.add(label);
		panel.add(urlField);
		panel.add(submitbtn);
		panel.add(clearbtn);
		panel.add(itemArea);
		panel.add(feedArea);
		
		add(panel);
	}
	
	public void run(){
		pack();
		setVisible(true);
		
	}
	

}
