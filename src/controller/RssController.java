package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.Rss;

public class RssController {
	
	
	
	public RssController(){
		
	}
	
	public Rss start(String url) throws JAXBException{
		JAXBContext ctx = JAXBContext.newInstance( Rss.class);
		Unmarshaller unmarshaller = ctx.createUnmarshaller();
		URL file = null;
		try {
			file = new URL(url);
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		Object obj = unmarshaller.unmarshal(file);
		Rss rss = (Rss)obj;
		return rss;
	}
}
