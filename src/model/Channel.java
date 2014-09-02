package model;

import java.util.*;
import javax.xml.bind.annotation.*;

/**
 * The channel class.
 * @author Supavit 5510546671
 * @version 2014.09.02
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {
	
	/** The title of the channel. */
	private String title;
	
	/** The link of the channel. */
	private String link;
	
	/** The description of the channel. */
	private String description;
	
	/** The language of the channel. */
	private String language;
	
	/** The last build date of the channel. */
	private String lastBuildDate;
	
	/** The copyright of the channel. */
	private String copyright;
	
	/** The list of the Items in the channel. */
	@XmlElement(name = "item")
	private List<Item> itemlist = new ArrayList<Item>();
	
	public Channel(){
		
	}
	
	/**
	 * Get title of the channel.
	 * @return title of the channel
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * Set title of the channel.
	 * @param title the title of the channel
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * Get link of the channel.
	 * @return link of the channel
	 */
	public String getLink(){
		return link;
	}
	
	/**
	 * Set link of the channel
	 * @param link the link of the channel
	 */
	public void setLink(String link){
		this.link = link;
	}
	
	/**
	 * Get description of the channel.
	 * @return description of the channel
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * Set description of the channel.
	 * @param description the description of the channel
	 */
	public void setDescription(String description){
		this.description = description;
	}
	
	/**
	 * Get language of the channel.
	 * @return language of the channel
	 */
	public String getLanguage(){
		return language;
	}
	
	/**
	 * Set language of the channel.
	 * @param language the language of the channel
	 */
	public void setLanguage(String language){
		this.language = language;
	}

	/**
	 * Get last build date of the channel.
	 * @return last build date of the channel
	 */
	public String getLastBuildDate() {
		return lastBuildDate;
	}

	/**
	 * Set last build date of the channel.
	 * @param lastBuildDate the last build date of the channel
	 */
	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	/**
	 * Get copyright of the channel.
	 * @return copyright of the channel
	 */
	public String getCopyright() {
		return copyright;
	}

	/**
	 * Set copyright of the channel.
	 * @param copyright the copyright of the channel
	 */
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	/**
	 * Get list of items in the channel
	 * @return list of items in the channel
	 */
	public List<Item> getItemlist() {
		return itemlist;
	}
	
	/**
	 * Set list of items in the channel
	 * @param itemlist the list of items in the channel
	 */
	public void setItemlist(List<Item> itemlist) {
		this.itemlist = itemlist;
	}
	
}
