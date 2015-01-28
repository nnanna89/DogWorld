package com.dwn.dogworld.pages;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import com.dwn.dogworld.entities.DogRequest;

/**
 * Home page
 * @author Nnanna Madu
 * @since 13 Oct, 2014
 */
public class Index
{
	@Property
	private DogRequest dogRequest;
	
	@Inject
	private Session session;
	
	@InjectPage
	private Index index;
	
	/*@CommitAfter
	Object onSuccess(){
		session.persist(dogRequest);
		return index;
	}*/
	
	Object onContactUs(){
    	return "ContactUs";
    }
	
}
