package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
	private JList<Item> itemList;
	private JTextArea itemArea;
	private JTextArea feedArea;
	
	private JLabel currentChannelTitleLabel;
//	private JLabel currentChannelLinkLabel;
	private JLabel currentChannelDescriptionLabel;
	private JLabel currentChannelLanguageLabel;
//	private JLabel currentChannelLastBuildDateLabel;
	private JLabel currentChannelCopyrightLabel;
	
	
	private JPanel itemPanel;
	private JScrollPane itemScroll;
	
	private JPanel feedPanel;
	private JScrollPane feedScroll;
	
	private JEditorPane feedEditorPane;
	
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
		urlField.grabFocus();
		
		submitbtn = new JButton("Submit");
		submitbtn.addActionListener(new SubmitBtn());
		submitbtn.addKeyListener(new SubmitBtn());
		getRootPane().setDefaultButton(submitbtn);
		
		clearbtn = new JButton("Clear");
		clearbtn.addActionListener(new ClearBtn());
		
		currentChannelTitleLabel = new JLabel("Current Channel");
		currentChannelDescriptionLabel = new JLabel("");
		currentChannelLanguageLabel = new JLabel("");
		currentChannelCopyrightLabel = new JLabel("");
		
		
		
		itemPanel = new JPanel();
		itemPanel.setBorder(new TitledBorder(new EtchedBorder(), "Item Area"));
		itemArea = new JTextArea(10,50);
		itemArea.setEditable(false);
		itemScroll = new JScrollPane(itemArea);
		itemScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//itemPanel.add(itemScroll);
		
		feedPanel = new JPanel();
		feedPanel.setBorder(new TitledBorder(new EtchedBorder(), "Feed Area"));
		feedArea = new JTextArea(10,50);
		feedArea.setEditable(false);
		feedArea.setLineWrap(true);
		feedArea.setWrapStyleWord(true);
		feedScroll = new JScrollPane(feedArea);
		feedScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		feedPanel.add(feedScroll);
		
		feedEditorPane = new JEditorPane();
		feedEditorPane.setEditable(false);
		feedEditorPane.setContentType("text/html");
		
		feedPanel.add(feedEditorPane);
		
		panel.add(label);
		panel.add(urlField);
		panel.add(submitbtn);
		panel.add(clearbtn);
		panel.add(currentChannelTitleLabel);
		panel.add(currentChannelDescriptionLabel);
		panel.add(currentChannelLanguageLabel);
		panel.add(currentChannelCopyrightLabel);
		panel.add(itemPanel);
		panel.add(feedPanel);

		add(panel);
	}
	
	public void createItemList(ArrayList<String> list, ArrayList listItem){
		if(itemScroll != null){
			itemPanel.remove(itemScroll);
		}
		itemList = new JList(list.toArray());
		
		
		itemList.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                	Item tmpItem = (Item) listItem.get(itemList.getSelectedIndex());
                	feedArea.setText("Description: " + tmpItem.getDescription().toString() + newline);
                	feedArea.append("Published Date: " + tmpItem.getPubDate().toString() + newline);
                	feedEditorPane.setText("<html> Read more at " + "<a href=\""+tmpItem.getLink()+"\">Link</a>" + "</html>" );
                	
                }
            }
        });
		itemScroll = new JScrollPane(itemList);
		itemScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		itemPanel.add(itemScroll);
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
		
		ArrayList<String> list = new ArrayList<String>();
		ArrayList listItem = new ArrayList();
		
		for(Item item : rss.getChannel().getItemlist()){
			list.add(item.getTitle());
			listItem.add(item);
//			itemArea.append(item.getTitle() + newline);
		}
		
		createItemList(list, listItem);
		
		setLabel(rss);
		
	}
	
	public void setLabel(Rss rss){
		currentChannelTitleLabel.setText("Current Channel: " + rss.getChannel().getTitle());
		currentChannelDescriptionLabel.setText("Description: " + rss.getChannel().getDescription());
		currentChannelLanguageLabel.setText("Language: " + rss.getChannel().getLanguage());
		currentChannelCopyrightLabel.setText(rss.getChannel().getCopyright());
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
			urlField.grabFocus();
			
		}
		
	}
	

}