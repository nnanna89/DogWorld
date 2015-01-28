package com.dwn.dogworld.components;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.dwn.dogworld.dal.CrudServiceDAO;
import com.dwn.dogworld.data.Breed;
import com.dwn.dogworld.data.Gender;
import com.dwn.dogworld.data.Location;
import com.dwn.dogworld.data.RequestStatus;
import com.dwn.dogworld.entities.DogRequest;
import com.dwn.dogworld.utils.EmailNotifications;
import com.dwn.dogworld.utils.SendEmail;

/**
 * Reusable Dog Requests Custom Component
 * @author Nnanna Madu
 * @since Dec 30th, 2014
 */


public class DogRequestsForm
{
	@Component
	private Form dogRequestForm;

	@Property
	private String email;
	
	@Property
	private String telephone;
	
	@Property
	private String description;
	
	@Property
	private SelectModel breeds = new Breed();
	
	@Property
	private String selectedBreed; //Breed is an entity, so we either use
								  //raw string or a value encoder
	
	@Property
	private Gender genderSelect; //Gender is an enum
	
	@Property
	private SelectModel locations = new Location();
	
	@Property
	private String selectedLocation;
	
	@Property
	private String dogColor;
	
	private DogRequest newRequest;
	
	private DogRequest dogRequest;
	
	@Inject
	private CrudServiceDAO crudDao;
	
	@CommitAfter
	Object onSuccess(){
		newRequest = new DogRequest(selectedBreed, genderSelect.name(), dogColor, 1, description, 
				email, telephone, RequestStatus.NOT_PROCESSED.name(), selectedLocation);
		dogRequest = crudDao.create(newRequest);
		
		//send email notifications (to dwn admin, customer, and breeders)
		EmailNotifications emailNotification = new SendEmail();
		emailNotification.sendDogRequestNotification(dogRequest);
		return null;
	}
	
}
