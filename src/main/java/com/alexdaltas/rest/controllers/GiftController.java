package com.alexdaltas.rest.controllers;

import java.io.StringReader;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import org.apache.log4j.Logger;

import com.alexdaltas.beans.Gift;
import com.alexdaltas.beans.GiftList;
import com.alexdaltas.db.GiftDAO;

@Controller
public class GiftController {
	
	static Logger logger = Logger.getLogger(GiftController.class);

	private GiftDAO giftDAO;
	private Jaxb2Marshaller jaxb2Mashaller;
	
	private static final String XML_VIEW_NAME = "gifts";
	
	public void setGiftDAO(GiftDAO dao) {
		this.giftDAO = dao;
	}
	
	public void setJaxb2Mashaller(Jaxb2Marshaller jaxb2Mashaller) {
		this.jaxb2Mashaller = jaxb2Mashaller;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/gift/{id}")
	public ModelAndView getGift(@PathVariable String id) {
		
		logger.info("getGift() controller");
		
		Gift g = giftDAO.get(Long.parseLong(id));
		
		logger.info("Got Gift: " + g.toString());
		
		return new ModelAndView(XML_VIEW_NAME, "object", g);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/gift/{id}")
	public ModelAndView updateGift(@RequestBody String body) {
		Source source = new StreamSource(new StringReader(body));
		Gift e = (Gift) jaxb2Mashaller.unmarshal(source);
		giftDAO.update(e);
		return new ModelAndView(XML_VIEW_NAME, "object", e);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/gift")
	public ModelAndView addGift(@RequestBody String body) {
		Source source = new StreamSource(new StringReader(body));
		Gift e = (Gift) jaxb2Mashaller.unmarshal(source);
		giftDAO.add(e);
		return new ModelAndView(XML_VIEW_NAME, "object", e);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/gift/{id}")
	public ModelAndView removeGift(@PathVariable String id) {
		giftDAO.remove(Long.parseLong(id));
		List<Gift> gifts = giftDAO.getAll();
		GiftList list = new GiftList(gifts);
		return new ModelAndView(XML_VIEW_NAME, "gifts", list);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/gifts")
	public ModelAndView getGifts() {
		List<Gift> gifts = giftDAO.getAll();
		GiftList list = new GiftList(gifts);
		return new ModelAndView(XML_VIEW_NAME, "gifts", list);
	}
}
