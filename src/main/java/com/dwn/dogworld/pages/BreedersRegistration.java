package com.dwn.dogworld.pages;

import java.util.List;

import org.apache.log4j.Logger;
//import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Component;
//import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.runtime.ComponentEventException;
import org.hibernate.exception.ConstraintViolationException;

import com.dwn.dogworld.dal.CrudServiceDAO;
import com.dwn.dogworld.dal.QueryParameters;
import com.dwn.dogworld.entities.Breeder;
import com.dwn.dogworld.utils.SendEmail;

/**
 * Registration page for breeders
 * @author Nnanna Madu
 * @since 23 Jan, 2014
 */
public class BreedersRegistration
{
	Logger logger = Logger.getLogger(BreedersRegistration.class);
	
	@Component
	private Form registrationForm;
	
	@Property
	private String contactName;
	
	@Property
	private String contactEmail;
	
	@Property
	private String contactNumber;
	
	@Property
	private String address;
	
	@Property
	private String breedingStartYear;
	
	private Breeder breeder;
	
	@Inject
	private CrudServiceDAO crudDao;
	
//	@Persist
	@Property
	private List<String> selectedBreeds;
	
//	@Inject
//	private ComponentResources resources;
	
	@Inject
	AlertManager alertManager;
	
	@CommitAfter
	Object onSuccess(){
		StringBuilder breedString = new StringBuilder();
		if(selectedBreeds.size() > 0){
			for(String breed : selectedBreeds){
//				logger.info("-----------breed: " + breed);
				breedString.append(breed);
				breedString.append(",");
			}
		}else{
			return null; //don't go ahead
		}
		//check for all unique constraints
		Breeder testBreeder = null;
		//name
		testBreeder = crudDao.findUniqueWithNamedQuery(Breeder.BY_NAME, QueryParameters.with("breederName", contactName).parameters());
		if(testBreeder != null){
			logger.info("a breeder already exists with entered name");
			registrationForm.recordError("A breeder with the entered name already exists.");
//			alertManager.alert(Duration.TRANSIENT, Severity.ERROR, "A breeder with the entered name already exists.");
			return null;
		}
		//telephone
		testBreeder = crudDao.findUniqueWithNamedQuery(Breeder.BY_TELEPHONE, QueryParameters.with("breederTelephone", contactNumber).parameters());
		if(testBreeder != null){
			logger.info("a breeder already exists with entered telephone number");
			registrationForm.recordError("A breeder with the entered telephone number already exists.");
//			alertManager.alert(Duration.TRANSIENT, Severity.ERROR, "A breeder with the entered telephone number already exists.");
			return null;
		}
		//email
		testBreeder = crudDao.findUniqueWithNamedQuery(Breeder.BY_EMAIL, QueryParameters.with("breederEmail", contactEmail).parameters());
		if(testBreeder != null){
			logger.info("a breeder already exists with entered email address");
			registrationForm.recordError("A breeder with the entered email address already exists.");
//			alertManager.alert(Duration.TRANSIENT, Severity.ERROR, "A breeder with the entered email address already exists.");
			return null;
		}
		
		//create breeder entry
		try{
			breeder = new Breeder(contactName, address, contactEmail, contactNumber, breedString.toString(), null, breedingStartYear);
			crudDao.create(breeder);
			alertManager.alert(Duration.TRANSIENT, Severity.SUCCESS, "Thank you for registering with us. Please see your email for more details.");
		}catch(ConstraintViolationException e){
			e.printStackTrace();
			return null;
		}catch(ComponentEventException c){
			c.printStackTrace();
			return null;
		}
		
		//send email notification to the breeder and admin
		SendEmail emailNotification = new SendEmail();
		emailNotification.sendBreederRegistrationNotification(breeder);
		emailNotification.sendBreederRegistrationNotificationToAdmin(breeder);
		
		//discard the values persisted in session
//		resources.discardPersistentFieldChanges();
		
		return null;
	}
	
	@Property
	private String[] commonBreedLines = {"Bull Dog", "Basenji", "Chow Chow", "German Shepherd (Alsatian)",  "Lhasa Apso", "Mastiff", "Pitbull", "Poodle", "Retriever", "Rottweiler", "Others"};
	
	public ValueEncoder<String> getValueEncoder(){
		return new ValueEncoder<String>(){

			@Override
			public String toClient(String value) {
				// TODO Auto-generated method stub
				return value;
			}

			@Override
			public String toValue(String clientValue) {
				// TODO Auto-generated method stub
				return clientValue;
			}
			
		};
	}
	
}
