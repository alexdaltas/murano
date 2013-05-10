package com.alexdaltas.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="gift")
public final class Gift {
	
	private long id;
	private String name;
	private int price;
	
	public Gift() {}
	
	public Gift(long id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Gift [id=" + id + ", name=" + name + ", price=" + price + "]";
	}	
	
}
