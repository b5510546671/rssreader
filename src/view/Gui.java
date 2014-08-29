package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.*;
import javax.xml.bind.JAXBException;

import model.Item;
import model.Rss;
import controller.RssController;

public class Gui extends JFrame{
	
	private final static String newline = "\n";
	
	private RssController controller;
	
	private JLabel label;
	private JTextField urlField;
	private JButton submitbtn;
	private JButton clearbtn;
	private JTextArea itemArea;
	private JEditorPane feedPane;
	
	private JLabel currentChannelLabel;
	private JLabel currentChannel;
	
	private JPanel itemPanel;
	private JScrollPane itemScroll;
	
	private JPanel feedPanel;
	private JScrollPane feedScroll;
	
	private JPanel panel;
	
	public Gui(){
		setTitle("RSS Feed");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(900,200));
		initComponents();
	}
	
	public void initComponents(){
		panel = new JPanel();
		
		label = new JLabel();
		label.setText("Enter the RSS URL: ");
		
		urlField = new JTextField(50);
		
		submitbtn = new JButton("Submit");
		submitbtn.addActionListener(new SubmitBtn());
		submitbtn.addKeyListener(new SubmitBtn());
		getRootPane().setDefaultButton(submitbtn);
		
		clearbtn = new JButton("Clear");
		clearbtn.addActionListener(new ClearBtn());
		
		currentChannelLabel = new JLabel("Current Channel: ");
		currentChannel = new JLabel(" ");
		
		itemPanel = new JPanel();
		itemPanel.setBorder(new TitledBorder(new EtchedBorder(), "Item Area"));
		itemArea = new JTextArea(10,50);
		itemArea.setEditable(false);
		itemScroll = new JScrollPane(itemArea);
		itemScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		itemPanel.add(itemScroll);
		
		feedPanel = new JPanel();
		feedPanel.setBorder(new TitledBorder(new EtchedBorder(), "Feed Area"));
		feedPane = new JEditorPane();
		feedPane.setEditable(false);
		feedScroll = new JScrollPane(feedPane);
		feedScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		feedPanel.add(feedScroll);
		
		panel.add(label);
		panel.add(urlField);
		panel.add(submitbtn);
		panel.add(clearbtn);
		panel.add(currentChannelLabel);
		panel.add(currentChannel);
		panel.add(itemPanel);
		panel.add(feedPanel);

		add(panel);
	}
	
	public void startFeedRss(String link){
		RssController controller = new RssController();
		Rss rss = null;
		try {
			//rss = controller.start("http://feeds.reuters.com/reuters/Election2012?format=xml");
			//rss = controller.start("http://feeds.bbci.co.uk/news/rss.xml");
			//http://rssfeeds.sanook.com/rss/feeds/sanook/hot.variety.xml
			rss = controller.start(link);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		for(Item item : rss.getChannel().getItemlist()){
			itemArea.append(item.getTitle() + newline);
		}
		
		currentChannel.setText(rss.getChannel().getTitle());
	}
	
	public void run(){
		pack();
		setVisible(true);
		
	}
	
	class SubmitBtn implements ActionListener, KeyListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			startFeedRss(urlField.getText());
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				startFeedRss(urlField.getText());
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class ClearBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			urlField.setText("");
			
		}
		
	}
	

}
