package model;

import javax.xml.bind.annotation.*;

/**
 * The RSS class.
 * @author Supavit 5510546671
 * @version 2014.09.02
 *
 */
@XmlRootElement(name = "rss")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rss {
	
	/**
	 * The RSS channel.
	 */
	@XmlElement(name = "channel")
	private Channel channel;
	
	
	public Rss(){
		
	}
	
	/**
	 * Get RSS channel.
	 * @return RSS channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * Set RSS channel.
	 * @param channel RSS channel to be set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	
}
