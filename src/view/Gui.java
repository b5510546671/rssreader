package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.xml.bind.JAXBException;

import model.Item;
import model.Rss;
import controller.RssController;
/**
 * A class for creating user interface.
 * @author Supavit 5510546671
 * @version 2014.09.02
 *
 */
public class Gui extends JFrame{
	
	/** Add new line */
	private final static String NEWLINE = "\n";
	
	/** The Enter RSS URL label */
	private JLabel label;
	
	/** Text field for input URL */
	private JTextField urlField;
	
	/** Submit Button */
	private JButton submitbtn;
	
	/** Clear URL Field Button */
	private JButton clearbtn;
	
	/** List of RSS Items */
	private JList<Item> itemList;
	
	/** Text area for showing news feed */
	private JTextArea feedArea;
	
	/** Label for showing Title of current channel */
	private JLabel currentChannelTitleLabel;
	
	/** Label for showing Description of current channel */
	private JLabel currentChannelDescriptionLabel;
	
	/** Label for showing Language of current channel */
	private JLabel currentChannelLanguageLabel;
	
	/** Label for showing Copyright of current channel */
	private JLabel currentChannelCopyrightLabel;
	
	/** Panel for keeping RSS Items */
	private JPanel itemPanel;
	
	/** Scroll pane for RSS Items */
	private JScrollPane itemScroll;
	
	/** Panel for keeping RSS Feeds */
	private JPanel feedPanel;
	
	/** Scroll pane for RSS Feeds */
	private JScrollPane feedScroll;
	
	/** Editor pane for showing Read more link */
	private JEditorPane feedEditorPane;
	
	/** Panel for keeping all user interface components */
	private JPanel panel;
	
	/**
	 * Initialize a new user interface.
	 */
	public Gui(){
		setTitle("RSS Feed");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1135,500));
		initComponents();
	}
	
	/**
	 * Initialize all components on the screen.
	 */
	public void initComponents(){
		panel = new JPanel();
		
		label = new JLabel();
		label.setText("Enter the RSS URL: ");
		
		urlField = new JTextField(50);
		urlField.grabFocus();
		urlField.setText("http://feeds.bbci.co.uk/news/rss.xml");
		
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
	
	/**
	 * Create list for keeping RSS Items.
	 * @param list a list of RSS Items'titles.
	 * @param listItem a list of RSS Items
	 */
	public void createItemList(ArrayList<String> list, ArrayList<Item> listItem){
		if(itemScroll != null){
			itemPanel.remove(itemScroll);
		}
		itemList = new JList(list.toArray());
		
		
		itemList.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                	Item tmpItem = (Item) listItem.get(itemList.getSelectedIndex());
                	feedArea.setText("Description: " + tmpItem.getDescription().toString() + NEWLINE);
                	feedArea.append("Published Date: " + tmpItem.getPubDate().toString() + NEWLINE);
                	feedEditorPane.setText("<html> Read more at " + "<a href=\""+tmpItem.getLink()+"\">Link</a>" + "</html>" );
                	feedEditorPane.addMouseListener(new MouseListener() {
						
						@Override
						public void mouseReleased(MouseEvent e) {
							
							
						}
						
						@Override
						public void mousePressed(MouseEvent e) {
							
						}
						
						@Override
						public void mouseExited(MouseEvent e) {
							
						}
						
						@Override
						public void mouseEntered(MouseEvent e) {
						
						}
						
						@Override
						public void mouseClicked(MouseEvent e) {
							if (Desktop.isDesktopSupported()){
	                			try
	                            {
	                                Desktop.getDesktop().browse(new URI(tmpItem.getLink()));
	                            }
	                            catch (Exception exp)
	                            {
	                                exp.printStackTrace();
	                            }
                			}
                			else
                            {
                               System.out.println("Could not open link.");
                            }
							
						}
					});
                	
                	pack();
                }
            }
        });
		itemScroll = new JScrollPane(itemList);
		itemScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		itemPanel.add(itemScroll);
	}
	
	/**
	 * Start to get RSS Items from URL.
	 * @param link an input URL link
	 */
	public void startFeedRss(String link){
		RssController controller = new RssController();
		Rss rss = null;
		try {
			rss = controller.start(link);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Item> listItem = new ArrayList<Item>();
		
		for(Item item : rss.getChannel().getItemlist()){
			list.add(item.getTitle());
			listItem.add(item);
		}
		
		createItemList(list, listItem);
		
		setLabel(rss);
		
	}
	
	/**
	 * Set all the current channel labels.
	 * @param rss the RSS channel
	 */
	public void setLabel(Rss rss){
		currentChannelTitleLabel.setText("Current Channel: " + rss.getChannel().getTitle());
		currentChannelDescriptionLabel.setText("Description: " + rss.getChannel().getDescription());
		currentChannelLanguageLabel.setText("Language: " + rss.getChannel().getLanguage());
		currentChannelCopyrightLabel.setText(rss.getChannel().getCopyright());
	}
	
	/**
	 * Run the user interface.
	 */
	public void run(){
		pack();
		setVisible(true);
		
	}
	
	/**
	 * Submit Button action.
	 * @author Supavit 5510546671
	 *
	 */
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
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
	}
	
	/**
	 * Clear Button action.
	 * @author Supavit 5510546671
	 *
	 */
	class ClearBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			urlField.setText("");
			urlField.grabFocus();
			
		}
		
	}
	

}