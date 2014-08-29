package view;

import javax.xml.bind.JAXBException;

import model.*;
import controller.RssController;

public class Main {
	public static void main(String[] args) {
		
		
		RssController controller = new RssController();
		Rss rss= null;
		try {
			rss = controller.start("http://feeds.bbci.co.uk/news/rss.xml");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		for(Item item : rss.getChannel().getItemlist()){
		//for(int i = 0; i < rss.getChannel().getItemlist().size(); i++ ){
			System.out.println(item.getTitle());
		}
		
	}
}
