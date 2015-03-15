package com.dwn.dogworld.components;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.Field;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.dwn.dogworld.dal.CrudServiceDAO;
import com.dwn.dogworld.dal.QueryParameters;
import com.dwn.dogworld.data.Breed;
import com.dwn.dogworld.data.Gender;
import com.dwn.dogworld.data.Location;
import com.dwn.dogworld.data.RequestStatus;
import com.dwn.dogworld.entities.Breeder;
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
	Logger logger = Logger.getLogger(DogRequestsForm.class);
	
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
	
	@Inject
	private AlertManager alertManager;
	
	@CommitAfter
	Object onSuccess(){
		newRequest = new DogRequest(selectedBreed, genderSelect.name(), dogColor, 1, description, 
				email, telephone, RequestStatus.NOT_PROCESSED.name(), selectedLocation);
		dogRequest = crudDao.create(newRequest);
		
		//send email notifications (to dwn admin, customer, and breeders)
		SendEmail emailNotification = new SendEmail();
		emailNotification.sendDogRequestNotification(dogRequest);
		
		//show alert
		alertManager.alert(Duration.TRANSIENT, Severity.SUCCESS, "Your request has been received. Please see your email for more details.");
		
		//send email to all breeders who deal in the selected breed
		List<Breeder> breeders = crudDao.findWithNamedQuery(
				Breeder.BY_BREEDS,
				QueryParameters.with("breed", "%" + selectedBreed + "%").parameters());
		
		if(breeders != null && breeders.size() > 0){
			for(Breeder breeder : breeders){
				logger.info("-----------------Breeder Email Address: " + breeder.getEmail());
				emailNotification.sendDogRequestAlertToBreeders(breeder.getEmail(), dogRequest);
			}
		}
		
		return null;
	}
	
}
