package model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "rss")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rss {
	
	@XmlElement(name = "channel")
	private Channel channel;
	
	public Rss(){
		
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	
}
