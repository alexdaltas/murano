package com.alexdaltas.db;

import org.apache.log4j.Logger;

import com.alexdaltas.beans.Gift;
import com.mongodb.BasicDBObject;

public class ItemDBO extends BasicDBObject {

	private static final long serialVersionUID = 6480229532510360754L;
	static Logger logger = Logger.getLogger(ItemDBO.class);

	public ItemDBO(){ }
	
	public ItemDBO(Long id, String name, Double price, Boolean sold) {
		put("id", id);
        put("name", name);
        put("price", price);
        put("sold", sold);
    }
	
	public Gift toGift(){	
		return new Gift(getLong("id"),  getString("name"), getDouble("price"), getBoolean("sold"));
	}
}
