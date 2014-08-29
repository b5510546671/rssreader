package view;

import javax.xml.bind.JAXBException;

import model.*;
import controller.*;

public class Main {
	
	public static void main(String[] args) {
		
		//for creating UI and let it run
		Gui ui = new Gui();
		ui.run();
		
		
//		RssController controller = new RssController();
//		Rss rss= null;
//		try {
//			rss = controller.start("http://feeds.reuters.com/reuters/Election2012?format=xml");
//			//rss = controller.start("http://feeds.bbci.co.uk/news/rss.xml");
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}
		
//		for(Item item : rss.getChannel().getItemlist()){
//			System.out.println(item.getTitle());
//		}
		
	}
}
