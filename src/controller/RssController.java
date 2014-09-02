package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.Rss;

/**
 * Controller that links between view and model.
 * @author Supavit 5510546671
 * @version 2014.09.02
 *
 */
public class RssController {
	
	public RssController(){
		
	}
	
	/**
	 * Start unmarshalling.
	 * @param url the input url from user to create RSS
	 * @return the RSS resulted from unmarshalling
	 * @throws JAXBException an exception
	 */
	public Rss start(String url) throws JAXBException{
		JAXBContext ctx = JAXBContext.newInstance( Rss.class);
		Unmarshaller unmarshaller = ctx.createUnmarshaller();
		URL file = null;
		try {
			file = new URL(url);
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		Object fileobj = unmarshaller.unmarshal(file);
		Rss rss = (Rss)fileobj;
		return rss;
	}
}
