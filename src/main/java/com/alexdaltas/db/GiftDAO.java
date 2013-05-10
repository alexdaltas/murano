package com.alexdaltas.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alexdaltas.beans.Gift;

public class GiftDAO {
	
	private static Map<Long, Gift> allGifts;
	static {
		allGifts = new HashMap<Long, Gift>();
		Gift g1 = new Gift(1L, "Cat", 2000);
		Gift g2 = new Gift(2L, "Bird", 1500);
		Gift g3 = new Gift(2L, "Left eye", 1000);
		allGifts.put(g1.getId(), g1);
		allGifts.put(g2.getId(), g2);
		allGifts.put(g3.getId(), g3);
	}
	
	public void add(Gift g) {
		allGifts.put(g.getId(), g);
	}

	public Gift get(long id) {
		return allGifts.get(id);
	}

	public List<Gift> getAll() {
		List<Gift> gifts = new ArrayList<Gift>();

		for(Gift g : allGifts.values()) {
			gifts.add(g);
		}
		return gifts;
	}

	public void remove(long id) {
		allGifts.remove(id);
	}

	public void update(Gift e) {
		allGifts.put(e.getId(), e);
	}
}
