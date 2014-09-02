package model;

import javax.xml.bind.annotation.*;

/**
 * The Item class.
 * @author Supavit 5510546671
 * @version 2014.09.02
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
	
	/** Title of the item. */
	private String title;
	
	/** Description of the item. */
	private String description;
	
	/** Link of the item. */
	private String link;
	
	/** Guid of the item. */
	private String guid;
	
	/** Published date of the item. */
	private String pubDate;
	
	
	public Item(){
		
	}

	/**
	 * Get title of the item.
	 * @return title of the item
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set title of the item.
	 * @param title the title of the item
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get description of the item.
	 * @return description of the item
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set description of the item.
	 * @param description the description of the item
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get link of the item.
	 * @return link of the item
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Set link of the item.
	 * @param link link of the item
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Get guid of the item.
	 * @return guid of the item
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * Set guid of the item.
	 * @param guid the guid of the item
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * Get published date of the item.
	 * @return published date of the item
	 */
	public String getPubDate() {
		return pubDate;
	}

	/**
	 * Set published date of the item.
	 * @param pubDate the published date of the item
	 */
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	
}
