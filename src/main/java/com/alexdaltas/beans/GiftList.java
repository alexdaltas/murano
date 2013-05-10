package com.alexdaltas.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="gifts")
public class GiftList {
		
	private int count;
	private List<Gift> gifts;
		
	public GiftList() {}
		
	public GiftList(List<Gift> employees) {
		this.gifts = employees;
		this.count = employees.size();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
		
	@XmlElement(name="gift")
	public List<Gift> getGifts() {
		return gifts;
	}
	
	public void setGifts(List<Gift> gifts) {
		this.gifts = gifts;
	}
		
}
